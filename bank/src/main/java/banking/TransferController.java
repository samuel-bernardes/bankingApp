package banking;

import classes.abstractClass.Account;
import classes.abstractClass.Client;
import classes.services.Encryption;
import classes.services.FileService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TransferController {

    @FXML
    private Button buttonTransfer;

    @FXML
    private TextField fieldAgency;

    @FXML
    private TextField fieldNumber;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldValue;

    @FXML
    void clickedTransfer(ActionEvent event) {
        FileService fileService = new FileService();

        Client transferClient;
        try {
            transferClient = fileService.findClient("null");
            Account transferClientAccount = transferClient.getAccount();
            if (Encryption.encrypt(fieldPassword.getText()).equals(App.getLoggedInClient().getPassword())) {

                transferClientAccount
                        .setBalance(transferClientAccount.getBalance() + Double.valueOf(fieldValue.getText()));
            } else {
                App.showAlert("Senha incorreta!", "Digite sua senha corretamente para realizar a operação!");
            }
        } catch (Exception e) {

        }
    }

}
