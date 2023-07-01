package classes.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import classes.abstractClass.Client;
import classes.accounts.CurrentAccount;
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

            if(client instanceof LegalPerson){
                type = "LegalPerson";
            }
            else{
                type = "NaturalPerson";
            }
            writer.format("%s;%s;%s;%s;%n", type,
                client.getFullName(), client.getAdress(), client.getRegisterDate());
        }

        public void closeFileWriter(){
            writer.close();
        }

        public void abrirArquivoParaLeitura(){
            reader = new Scanner("arquivoTexto.txt");
        }

        public Client readClientFile(){
            try {
                while(reader.hasNextLine()){
                    String clientString = reader.nextLine();
                    String[] clientArray = clientString.split(";");
                    Client client;
                    if(clientArray[0].equals("LegalPerson")){
                        client = new LegalPerson(clientArray[1], clientArray[2], clientArray[3], clientArray[4]);
                    }
                    else{
                        client = new NaturalPerson(clientArray[1], clientArray[2], clientArray[3], clientArray[4]);
                    }
                    return client;
                }
            } catch (NoSuchElementException e) {

            }
            catch(IllegalStateException e){

            }

            return null;
        }

        public void closeFileRead(){
            reader.close();
        }
}
