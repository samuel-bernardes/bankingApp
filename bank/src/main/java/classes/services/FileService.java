package classes.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import classes.Exceptions.ClientNotFound;
import classes.Exceptions.PasswordNotTheSame;
import classes.abstractClass.Account;
import classes.abstractClass.Client;
import classes.accounts.CheckingAccount;
import classes.accounts.SavingAccount;
import classes.clients.LegalPerson;
import classes.clients.NaturalPerson;

public class FileService {
    private File file;
    private FileWriter fileWriter;
    private Formatter writer;
    private Scanner reader;

    public void openFileForWriting() {
        try {
            file = new File("arquivoTexto.txt");
            if (!file.exists()) {
                file.createNewFile();
                writer = new Formatter(file);
            }
            else{
                fileWriter = new FileWriter("arquivoTexto.txt", true);
                writer = new Formatter(fileWriter);
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void registerClient(Client client) {
        String type;

        try {
            client.setPassword(Encryption.encrypt(client.getPassword()));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        if (client instanceof LegalPerson) {
            type = "LegalPerson";
            LegalPerson legalP = (LegalPerson) client;
            writer.format("%s;%s;%s;%s;%s;%s;%s;%n", type, legalP.getEmail(), legalP.getPassword(),
                                legalP.getFullName(), legalP.getAdress(), legalP.getCnpj(), legalP.getFantasyName());
        } else {
            type = "NaturalPerson";
            NaturalPerson natP = (NaturalPerson) client;
            writer.format("%s;%s;%s;%s;%s;%s;%n", type, natP.getEmail(), natP.getPassword(), 
                            natP.getFullName(), natP.getAdress(), natP.getCpf());
        }
    }

    private void deleteAccount(Client client){
        File inputFile = new File("arquivoTexto.txt");
        File tempFile = new File("tempFile.txt");

        try{
            BufferedReader rd = new BufferedReader(new FileReader(inputFile));
            BufferedWriter wr = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = client.getEmail();
            String currentLine;

            while((currentLine = rd.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                String[] clientArray = trimmedLine.split(";");
                if(clientArray[1].equals(lineToRemove)) continue;
                wr.write(currentLine + System.getProperty("line.separator"));
            }
            wr.close(); 
            rd.close(); 
            inputFile.delete();
            tempFile.renameTo(inputFile);
        }
        catch(IOException e){

        }
    }

    public void saveAccount(Client client){
        deleteAccount(client);

        openFileForWriting();
        String type, accType;

        if(client.getAccount() instanceof CheckingAccount){
            accType = "CheckingAccount";
        }
        else{
            accType = "SavingAccount";
        }

        if (client instanceof LegalPerson) {
            type = "LegalPerson";
            LegalPerson legalP = (LegalPerson) client;

            writer.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%n", type, legalP.getEmail(), legalP.getPassword(),
                                legalP.getFullName(), legalP.getAdress(), legalP.getCnpj(), legalP.getFantasyName(),
                                accType, legalP.getAccount().getAccountId(), legalP.getAccount().getAgencyId(),
                                legalP.getAccount().getBalance());
        } else {
            type = "NaturalPerson";
            NaturalPerson natP = (NaturalPerson) client;
            writer.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%n", type, natP.getEmail(), natP.getPassword(), 
                            natP.getFullName(), natP.getAdress(), natP.getCpf(), accType,
                            natP.getAccount().getAccountId(), natP.getAccount().getAgencyId(),
                            natP.getAccount().getBalance());
        }
        closeFileWriter();
    }

    public void closeFileWriter() {
        writer.close();
    }

    public void openFileForReading() {
        try {
            file = new File("arquivoTexto.txt");
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.print("O arquivo não foi encontrado!");
            System.exit(1);
        }
    }

    public Client loginAccountThroughFile(String email, String password) throws ClientNotFound, PasswordNotTheSame {
        try {
            while (reader.hasNextLine()) {
                String clientString = reader.nextLine();
                String[] clientArray = clientString.split(";");
                if (clientArray[1].equals(email)) {
                    if (!(clientArray[2].equals(password))) {
                        throw new PasswordNotTheSame();
                    }
                    Client client;
                    Account acc;
                    if (clientArray[0].equals("LegalPerson")) {
                        client = new LegalPerson(clientArray[3], clientArray[4], clientArray[1], clientArray[2],
                                clientArray[5], clientArray[6]);
                        
                        if(clientArray.length > 7){
                            if(clientArray[7].equals("CheckingAccount")){
                                acc = new CheckingAccount(clientArray[8], clientArray[9]);
                                acc.setBalance(Double.parseDouble(clientArray[10])); 
                            }
                            else{
                                acc = new SavingAccount(clientArray[8], clientArray[9]);
                                acc.setBalance(Double.parseDouble(clientArray[10]));
                            }
                        }
                        else{
                            acc = null;
                        }    
                    } else {
                        client = new NaturalPerson(clientArray[3], clientArray[4], clientArray[1], clientArray[2],
                                clientArray[5]);

                        if(clientArray.length > 6){
                            if(clientArray[6].equals("CheckingAccount")){
                                acc = new CheckingAccount(clientArray[7], clientArray[8]);
                                acc.setBalance(Double.parseDouble(clientArray[9])); 
                            }
                            else{
                                acc = new SavingAccount(clientArray[7], clientArray[8]);
                                acc.setBalance(Double.parseDouble(clientArray[9]));
                            }
                        }
                        else{
                            acc = null;
                        }                 
                    }
                    client.setAccount(acc);
                    return client;
                }
            }
        } catch (NoSuchElementException e) {
            // TODO: Implementar tratamento adequado
        } catch (IllegalStateException e) {
            // TODO: Implementar tratamento adequado
        }

        throw new ClientNotFound();
    }

    public Client findClient(String accNumber) throws ClientNotFound{
        while (reader.hasNextLine()) {
            String clientString = reader.nextLine();
            String[] clientArray = clientString.split(";");
            if (clientArray[9].equals(accNumber) || clientArray[8].equals(accNumber)) {
                Client client;
                Account acc;

                if (clientArray[0].equals("LegalPerson")) {
                    client = new LegalPerson(clientArray[3], clientArray[4], clientArray[1], clientArray[2],
                            clientArray[5], clientArray[6]);

                    if(clientArray[7].equals("CheckingAccount")){
                        acc = new CheckingAccount(clientArray[8], clientArray[9]);
                        acc.setBalance(Double.parseDouble(clientArray[10])); 
                    }
                    else{
                        acc = new SavingAccount(clientArray[8], clientArray[9]);
                        acc.setBalance(Double.parseDouble(clientArray[10]));
                    }
                } else {
                    client = new NaturalPerson(clientArray[3], clientArray[4], clientArray[1], clientArray[2],
                            clientArray[5]);

                    if(clientArray[6].equals("CheckingAccount")){
                        acc = new CheckingAccount(clientArray[7], clientArray[8]);
                        acc.setBalance(Double.parseDouble(clientArray[9])); 
                    }
                    else{
                        acc = new SavingAccount(clientArray[7], clientArray[8]);
                        acc.setBalance(Double.parseDouble(clientArray[9]));
                    }
                }
                client.setAccount(acc);
                return client;
            }
        }

        throw new ClientNotFound();
    }

    public void closeFileRead() {
        reader.close();
    }
}
