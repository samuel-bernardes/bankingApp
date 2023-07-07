package classes.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import classes.Exceptions.ClientNotFound;
import classes.Exceptions.PasswordNotTheSame;
import classes.abstractClass.Client;
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

    public void registerAccount(Client client) {
        String type;

        try {
            client.setPassword(Encryption.encrypt(client.getPassword()));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        if (client instanceof LegalPerson) {
            type = "LegalPerson";
            LegalPerson legalP = (LegalPerson) client;
            writer.format("%s;%s;%s;%s;%s;%s;%s;%n", type, legalP.getFullName(), legalP.getAdress(),
                    legalP.getEmail(), legalP.getPassword(), legalP.getCnpj(), legalP.getFantasyName());
        } else {
            type = "NaturalPerson";
            NaturalPerson natP = (NaturalPerson) client;
            writer.format("%s;%s;%s;%s;%s;%s;%n", type, natP.getFullName(), natP.getAdress(), natP.getEmail(),
                    natP.getPassword(), natP.getCpf());
        }
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
                if (clientArray[3].equals(email)) {
                    if (!(clientArray[4].equals(password))) {
                        throw new PasswordNotTheSame();
                    }

                    Client client;
                    if (clientArray[0].equals("LegalPerson")) {
                        client = new LegalPerson(clientArray[1], clientArray[2], clientArray[3], clientArray[4],
                                clientArray[5], clientArray[6]);
                    } else {
                        client = new NaturalPerson(clientArray[1], clientArray[2], clientArray[3], clientArray[4],
                                clientArray[5]);
                    }
                    return client;
                } 
                
            }
        } catch (NoSuchElementException e) {
            // TODO: Implementar tratamento adequado
        } catch (IllegalStateException e) {
            // TODO: Implementar tratamento adequado
        } catch (Exception e) {
            // TODO: Implementar tratamento adequado
        }

        throw new ClientNotFound();
    }

    public void closeFileRead() {
        reader.close();
    }
}
