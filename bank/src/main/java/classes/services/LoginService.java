package classes.services;

import classes.Exceptions.ClientNotFound;
import classes.Exceptions.FieldIsEmpty;
import classes.Exceptions.PasswordNotTheSame;
import classes.abstractClass.Client;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginService {

    private static FileService fileService = new FileService();

    public static void registerUser(Client client) throws FieldIsEmpty {
        if (checkIfAnyFieldIsEmpty(client)) {
            throw new FieldIsEmpty();
        }
        fileService.openFileForWriting();
        fileService.registerAccount(client);
        fileService.closeFileWriter();
    }

    public static Client logUserIn(String email, String password) {
        try {
            password = Encryption.encrypt(password);
            fileService.openFileForReading();
            Client client = fileService.loginAccountThroughFile(email, password);
            fileService.closeFileRead();
            return client;
        } catch (ClientNotFound c) {
            showAlert("Erro de Login", "Cliente não encontrado.");
        } catch (PasswordNotTheSame p) {
            showAlert("Erro de Login", "Senha incorreta.");
        }

        return null;
    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static boolean checkIfAnyFieldIsEmpty(Client client) {
        if (client.getFullName().isBlank() || client.getEmail().isBlank() || client.getPassword().isBlank()) {
            return true;
        }
        // Adicione mais verificações para outros campos obrigatórios, como CPF/CNPJ,
        // endereço, etc.
        return false;
    }
}
