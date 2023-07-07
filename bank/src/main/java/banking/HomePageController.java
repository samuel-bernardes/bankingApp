package banking;

import java.net.URL;
import java.util.ResourceBundle;

import classes.abstractClass.Client;
import classes.accounts.CheckingAccount;
import classes.accounts.SavingAccount;
import classes.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class HomePageController implements Initializable {

    @FXML
    private Button buttonSacarDepositar;

    @FXML
    private Button buttonTranferir;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelSaldoAtual;

    private Client client;

    @FXML
    void clickedSacarDepositar(ActionEvent event) throws Exception {
        App.setRoot("take&deposit", 420, 500);
    }

    @FXML
    void clickedTransferir(ActionEvent event) throws Exception {
        App.setRoot("transfer", 420, 500);
    }

    private void showAccountCreationDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Cadastro de Conta");
        dialog.setHeaderText("Cadastro de Nova Conta");

        VBox content = new VBox();
        content.setSpacing(10);

        RadioButton radioButtonCorrente = new RadioButton("Conta Corrente");
        RadioButton radioButtonPoupanca = new RadioButton("Conta Poupança");

        ToggleGroup toggleGroup = new ToggleGroup();
        radioButtonCorrente.setToggleGroup(toggleGroup);
        radioButtonPoupanca.setToggleGroup(toggleGroup);

        Button createButton = new Button("Criar Conta Bancária");
        createButton.setOnAction(event -> {
            String accountType = "";
            if (radioButtonCorrente.isSelected()) {
                accountType = "Conta Corrente";
            } else if (radioButtonPoupanca.isSelected()) {
                accountType = "Conta Poupança";
            }

            if (accountType.equals("Conta Corrente")) {
                client.setAccount(new CheckingAccount("0001", "00000001"));
            } else {
                client.setAccount(new SavingAccount("0001", "00000001"));
            }

            dialog.setResult(accountType);
            dialog.close();
        });

        dialog.setOnCloseRequest(event -> {
            dialog.close();
        });

        content.getChildren().addAll(radioButtonCorrente, radioButtonPoupanca, createButton);

        dialog.getDialogPane().setContent(content);
        dialog.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        client = App.getLoggedInClient();
        labelNome.setText(client.getFullName());
        if (client.getAccount() != null) {
            labelSaldoAtual.setText("R$: " + Double.toString(client.getAccount().getBalance()));
        } else {
            labelSaldoAtual.setText("Zerado!");
            showAccountCreationDialog();
            LoginService.registerAccount(client);
        }

    }

}
