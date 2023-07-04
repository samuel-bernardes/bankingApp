package banking;

import classes.Exceptions.FieldIsEmpty;
import classes.abstractClass.Client;
import classes.clients.NaturalPerson;
import classes.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private Button buttonRegister;

    @FXML
    private TextField fieldAdress;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldFullName;

    @FXML
    private TextField fieldPasswordRegister;

    @FXML
    void clickedRegister(ActionEvent event) throws Exception {
        Client clientLogin = new NaturalPerson(fieldFullName.getText(), fieldAdress.getText(), fieldEmail.getText(),
                fieldPasswordRegister.getText(), "123");

        LoginService.registerUser(clientLogin);

        App.setRoot("login");

        System.out.println("teste " + fieldPasswordRegister.getText());
    }

}
