package banking;

import classes.abstractClass.Client;
import classes.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Classe controladora para a tela de login.
 */
public class LoginController {

    @FXML
    private Button buttonCadastro;

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField fieldLogin;

    @FXML
    private PasswordField fieldPassword;

    /**
     * Manipulador de eventos para o botão "Login".
     * Realiza o login do usuário e redireciona para a página inicial (homePage) se
     * o login for bem-sucedido.
     *
     * @param event O evento de clique do botão.
     * @throws Exception Se ocorrer um erro durante a transição de página.
     */
    @FXML
    void clickedLogin(ActionEvent event) throws Exception {
        Client client = LoginService.logUserIn(fieldLogin.getText(), fieldPassword.getText());
        if (client != null) {
            App.setLoggedInClient(client);
            App.setRoot("homePage", 420, 700);
        }
    }

    /**
     * Manipulador de eventos para o botão "Cadastro".
     * Redireciona para a página de cadastro (register).
     *
     * @param event O evento de clique do botão.
     * @throws Exception Se ocorrer um erro durante a transição de página.
     */
    @FXML
    void clickedCadastro(ActionEvent event) throws Exception {
        App.setRoot("register", 360, 540);
    }
}
