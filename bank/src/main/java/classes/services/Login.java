package classes.services;

import classes.Exceptions.ClientNotFound;
import classes.Exceptions.FieldIsEmpty;
import classes.Exceptions.PasswordNotTheSame;
import classes.abstractClass.Client;

public class Login {

    private static FileService fileService = new FileService();

    public static void registerUser(Client client) throws FieldIsEmpty{
        if(checkIfAnFieldIsEmpty(client)){
            throw new FieldIsEmpty();
        }
        fileService.openFileForWriting();
        fileService.registerAccount(client);
        fileService.closeFileWriter();
    }

    public static Client logUserIn(String email, String password){
        try{
            fileService.openFileForReading();
            Client client = fileService.loginAccountThroughFile(email, password);
            fileService.closeFileRead();
            return client;
        }
        catch(ClientNotFound c){
            //TODO: implementation
        }
        catch(PasswordNotTheSame p){
            //TODO: implementation
        }

        // Nao ta dos melhores de fato.

        return null;
    }


    private static boolean checkIfAnFieldIsEmpty(Client client){
        // -----------------------------------------
        // TODO: Tem que verificar todos os campos |
        // -----------------------------------------
        if(client.getFullName().isBlank() || client.getEmail().isBlank()){
            return true;
        }
        return false;
    }
}
