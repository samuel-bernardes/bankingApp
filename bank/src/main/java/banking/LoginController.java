package banking;

import classes.services.Encryption;
import classes.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField fieldLogin;

    @FXML
    private Label fieldPassword;

    @FXML
    void clickedLogin(ActionEvent event) {
        System.out.println("testeLogin: " + LoginService.logUserIn(fieldLogin.getText(), Encryption.encrypt(fieldPassword.getText())));
    }

}
