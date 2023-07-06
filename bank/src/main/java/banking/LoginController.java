package banking;

import classes.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button buttonCadastro;

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField fieldLogin;

    @FXML
    private TextField fieldPassword;

    @FXML
    void clickedLogin(ActionEvent event) {
        System.out.println("testeLogin: " + LoginService.logUserIn(fieldLogin.getText(), fieldPassword.getText()));
    }

    @FXML
    void clickedCadastro(ActionEvent event) {

    }

}
