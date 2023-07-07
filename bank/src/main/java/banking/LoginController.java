package banking;

import classes.abstractClass.Client;
import classes.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button buttonCadastro;

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField fieldLogin;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    void clickedLogin(ActionEvent event) throws Exception {
        Client client = LoginService.logUserIn(fieldLogin.getText(), fieldPassword.getText());
        if (client != null) {
            App.setLoggedInClient(client);
            App.setRoot("homePage", 420, 700);
        }
    }

    @FXML
    void clickedCadastro(ActionEvent event) throws Exception {
        App.setRoot("register", 360, 540);
    }
}
