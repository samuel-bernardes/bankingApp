package classes.services;

import banking.App;
import classes.Exceptions.ClientNotFound;
import classes.Exceptions.FieldIsEmpty;
import classes.Exceptions.PasswordNotTheSame;
import classes.abstractClass.Client;

public class LoginService {

    private static FileService fileService = new FileService();

    public static void registerUser(Client client) throws FieldIsEmpty {
        if (checkIfAnyFieldIsEmpty(client)) {
            throw new FieldIsEmpty();
        }
        fileService.openFileForWriting();
        fileService.registerClient(client);
        fileService.closeFileWriter();
    }

    public static void registerAccount(Client client) {
        fileService.saveAccount(client);
    }

    public static Client logUserIn(String email, String password) {
        try {
            password = Encryption.encrypt(password);
            fileService.openFileForReading();
            Client client = fileService.loginAccountThroughFile(email, password);
            fileService.closeFileRead();
            return client;
        } catch (ClientNotFound c) {
            App.showAlert("Erro de Login", "Cliente não encontrado.", "error");
        } catch (PasswordNotTheSame p) {
            App.showAlert("Erro de Login", "Senha incorreta.", "error");
        }

        return null;
    }

    private static boolean checkIfAnyFieldIsEmpty(Client client) {
        if (client.getFullName().isBlank() || client.getEmail().isBlank() || client.getPassword().isBlank()) {
            return true;
        }
        return false;
    }
}
