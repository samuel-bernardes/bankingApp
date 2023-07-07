package banking;

import classes.abstractClass.Account;
import classes.abstractClass.Client;
import classes.services.Encryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class TakeAndDepositController {

    @FXML
    private Button buttonConcluir;

    @FXML
    private RadioButton buttonDeposit;

    @FXML
    private RadioButton buttonSacar;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldValue;

    private boolean saqueEnabled = false;

    @FXML
    void clickedConcluir(ActionEvent event) {

        Client loggedClient = App.getLoggedInClient();

        Account loggedClientAccount = loggedClient.getAccount();

        if (Encryption.encrypt(fieldPassword.getText()).equals(loggedClient.getPassword())) {
            if (saqueEnabled) {

                loggedClientAccount.setBalance(loggedClientAccount.getBalance() - Double.valueOf(fieldValue.getText()));
            } else {
                loggedClientAccount.setBalance(loggedClientAccount.getBalance() + Double.valueOf(fieldValue.getText()));
            }
        } else {
            App.showAlert("Senha incorreta!", "Digite sua senha corretamente para realizar a operação!");
        }

        loggedClient.setAccount(loggedClientAccount);

        App.setLoggedInClient(loggedClient);
    }

    @FXML
    void clickedDeposit(ActionEvent event) {
        saqueEnabled = false;
    }

    @FXML
    void clickedSacar(ActionEvent event) {
        saqueEnabled = true;
    }

}
