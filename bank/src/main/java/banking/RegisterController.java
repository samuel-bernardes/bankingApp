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

    @FXML
    void clickedLogin(ActionEvent event) throws Exception {
        App.setRoot("login", 360, 540);
    }

    @FXML
    void clickedPessoaFisica(ActionEvent event) {
        buttonPessoaJuridica.setSelected(false);
        fieldNomeFantasia.setVisible(false);
        fieldCNPJ.setVisible(false);
        fieldCPF.setVisible(true);
    }

    @FXML
    void clickedPessoaJuridica(ActionEvent event) {
        buttonPessoaFisica.setSelected(false);
        fieldCPF.setVisible(false);
        fieldCNPJ.setVisible(true);
        fieldNomeFantasia.setVisible(true);
    }

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

        System.out.println("teste " + fieldPasswordRegister.getText());
    }
}
