package banking;

import classes.abstractClass.Client;
import classes.services.AccountService;
import classes.services.Encryption;
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

        Client transferClient;
        try {
            transferClient = AccountService.findClient("00000002");
            System.out.println("dd: "+transferClient);
            if (Encryption.encrypt(fieldPassword.getText()).equals(App.getLoggedInClient().getPassword())) {
                AccountService.transferMoney(App.getLoggedInClient(), transferClient, Double.valueOf(fieldValue.getText()));
            } else {
                App.showAlert("Senha incorreta!", "Digite sua senha corretamente para realizar a operação!");
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

}
