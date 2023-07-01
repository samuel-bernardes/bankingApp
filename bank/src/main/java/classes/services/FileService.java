package classes.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import classes.Exceptions.ClientNotFound;
import classes.Exceptions.PasswordNotTheSame;
import classes.abstractClass.Client;
import classes.clients.LegalPerson;
import classes.clients.NaturalPerson;

public class FileService {
        public File file;
        public Formatter writer;
        public Scanner reader;

        public void openFileForWriting(){
            try {
                writer = new Formatter("arquivoTexto.txt");    
            } catch (FileNotFoundException e) {
                System.err.print("O arquivo não foi encontrado!");
                System.exit(1);
            }
        }

        public void registerAccount(Client client){
            String type;

            try{
                client.setPassword(Encryption.encrypt(client.getPassword()));
            }
            catch(Exception e){
                System.out.println(e.getStackTrace());
                System.exit(157);
            }

            if(client instanceof LegalPerson){
                type = "LegalPerson";
                LegalPerson legalP = (LegalPerson)client;
                writer.format("%s;%s;%s;%s;%s;%s;%n", type,
                    legalP.getFullName(), legalP.getAdress(), legalP.getEmail(), legalP.getPassword(), 
                    legalP.getCnpj(), legalP.getFantasyName());
            }
            else{
                type = "NaturalPerson";
                NaturalPerson natP = (NaturalPerson)client;
                writer.format("%s;%s;%s;%s;%s;%n", type,
                    natP.getFullName(), natP.getAdress(), natP.getEmail(), natP.getPassword(), 
                    natP.getCpf());
            }
        }

        public void closeFileWriter(){
            writer.close();
        }

        public void openFileForReading(){
            reader = new Scanner("arquivoTexto.txt");
        }

        public Client loginAccountThroughFile(String email, String password) throws ClientNotFound, PasswordNotTheSame{
            try {
                while(reader.hasNextLine()){
                    String clientString = reader.nextLine();
                    String[] clientArray = clientString.split(";");
                    if(clientArray[3].equals(email)){
                        if(!(clientArray[4].equals(Encryption.decrypt(password)))){
                            throw new PasswordNotTheSame();
                        }

                        Client client;
                        if(clientArray[0].equals("LegalPerson")){
                            client = new LegalPerson(clientArray[1], clientArray[2], clientArray[3], clientArray[4], 
                                            clientArray[5], clientArray[6]);
                        }
                        else{
                            client = new NaturalPerson(clientArray[1], clientArray[2], clientArray[3], clientArray[4],
                                            clientArray[5]);
                        }
                        return client;
                    }
                }
            } 
            catch (NoSuchElementException e) {

            }
            catch(IllegalStateException e){

            }
            catch(Exception e){

            }

            throw new ClientNotFound();
        }

        public void closeFileRead(){
            reader.close();
        }
}
