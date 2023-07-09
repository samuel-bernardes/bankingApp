package banking;

import classes.abstractClass.Client;
import classes.clients.LegalPerson;
import classes.clients.NaturalPerson;
import classes.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * Classe controladora para a tela de cadastro.
 */
public class RegisterController {

    @FXML
    private Button buttonLogin;

    @FXML
    private RadioButton buttonPessoaFisica;

    @FXML
    private RadioButton buttonPessoaJuridica;

    @FXML
    private Button buttonRegister;

    @FXML
    private TextField fieldAdress;

    @FXML
    private TextField fieldCNPJ;

    @FXML
    private TextField fieldCPF;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldFullName;

    @FXML
    private TextField fieldNomeFantasia;

    @FXML
    private PasswordField fieldPasswordRegister;

    /**
     * Manipulador de eventos para o botão "Login".
     * Redireciona para a tela de login (login).
     *
     * @param event O evento de clique do botão.
     * @throws Exception Se ocorrer um erro durante a transição de tela.
     */
    @FXML
    void clickedLogin(ActionEvent event) throws Exception {
        App.setRoot("login", 360, 540);
    }

    /**
     * Manipulador de eventos para o botão "Pessoa Física".
     * Seleciona a opção de pessoa física e atualiza a visibilidade dos campos
     * correspondentes.
     *
     * @param event O evento de clique do botão.
     */
    @FXML
    void clickedPessoaFisica(ActionEvent event) {
        buttonPessoaJuridica.setSelected(false);
        fieldNomeFantasia.setVisible(false);
        fieldCNPJ.setVisible(false);
        fieldCPF.setVisible(true);
    }

    /**
     * Manipulador de eventos para o botão "Pessoa Jurídica".
     * Seleciona a opção de pessoa jurídica e atualiza a visibilidade dos campos
     * correspondentes.
     *
     * @param event O evento de clique do botão.
     */
    @FXML
    void clickedPessoaJuridica(ActionEvent event) {
        buttonPessoaFisica.setSelected(false);
        fieldCPF.setVisible(false);
        fieldCNPJ.setVisible(true);
        fieldNomeFantasia.setVisible(true);
    }

    /**
     * Manipulador de eventos para o botão "Registrar".
     * Realiza o registro do usuário com base nas informações fornecidas e
     * redireciona para a tela de login.
     *
     * @param event O evento de clique do botão.
     * @throws Exception Se ocorrer um erro durante o registro do usuário ou a
     *                   transição de tela.
     */
    @FXML
    void clickedRegister(ActionEvent event) throws Exception {
        Client clientLogin;

        if (buttonPessoaJuridica.isSelected()) {
            clientLogin = new LegalPerson(fieldFullName.getText(), fieldAdress.getText(), fieldEmail.getText(),
                    fieldPasswordRegister.getText(), fieldCNPJ.getText(), fieldNomeFantasia.getText());
        } else {
            clientLogin = new NaturalPerson(fieldFullName.getText(), fieldAdress.getText(), fieldEmail.getText(),
                    fieldPasswordRegister.getText(), fieldCPF.getText());
        }

        LoginService.registerUser(clientLogin);

        App.setRoot("login", 360, 540);
    }
}
