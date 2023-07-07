package classes.services;

import classes.Exceptions.ClientNotFound;
import classes.abstractClass.Client;

public class AccountService {
    private static FileService fileService = new FileService();

    public static void transferMoney(Client giver, Client receiver, double amount){
        giver.getAccount().setBalance((giver.getAccount().getBalance() - amount));
        receiver.getAccount().setBalance((receiver.getAccount().getBalance() + amount));

        fileService.saveAccount(giver);
        fileService.saveAccount(receiver);
    }

    public static void take(Client client, double amount){
        client.getAccount().setBalance((client.getAccount().getBalance() - amount));
        fileService.saveAccount(client);
    }

    public static void deposit(Client client, double amount){
        client.getAccount().setBalance((client.getAccount().getBalance() + amount));
        fileService.saveAccount(client);
    }

    public static Client findClient(String accNumber){
        Client client = null;
        try{
            fileService.openFileForReading();
            client = fileService.findClient(accNumber);
            fileService.closeFileRead();
        }
        catch(ClientNotFound e){

        }

        return client;
    }
}
