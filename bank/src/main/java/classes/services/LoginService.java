package classes.services;

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
            // TODO: Implementação para tratamento de Cliente não encontrado
            System.out.println("Cliente não encontrado.");
        } catch (PasswordNotTheSame p) {
            // TODO: Implementação para tratamento de senha incorreta
            System.out.println("Senha incorreta.");
        }

        return null;
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
