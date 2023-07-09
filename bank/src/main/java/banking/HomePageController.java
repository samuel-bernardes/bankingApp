package banking;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
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

/**
 * Classe controladora para a Página Inicial.
 */
public class HomePageController implements Initializable {

    @FXML
    private Button buttonSacarDepositar;

    @FXML
    private Button buttonTransferir;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelSaldoAtual;

    private Client client;

    /**
     * Manipulador de eventos para o botão "Sacar/Depositar".
     * Redireciona para a página "take&deposit".
     *
     * @param event O evento de clique do botão.
     * @throws Exception Se ocorrer um erro durante a transição de página.
     */
    @FXML
    void clickedSacarDepositar(ActionEvent event) throws Exception {
        App.setRoot("take&deposit", 420, 500);
    }

    /**
     * Manipulador de eventos para o botão "Transferir".
     * Redireciona para a página "transfer".
     *
     * @param event O evento de clique do botão.
     * @throws Exception Se ocorrer um erro durante a transição de página.
     */
    @FXML
    void clickedTransferir(ActionEvent event) throws Exception {
        App.setRoot("transfer", 420, 500);
    }

    /**
     * Gera um número aleatório para a criação da conta.
     *
     * @return Um número aleatório formatado como uma string.
     */
    private String generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(99999999);
        return String.format("%08d", randomNumber);
    }

    /**
     * Formata um valor como moeda no formato brasileiro.
     *
     * @param valor O valor a ser formatado.
     * @return O valor formatado como uma string.
     */
    private String formatarMoeda(double valor) {
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(locale);
        return formatadorMoeda.format(valor);
    }

    /**
     * Exibe um diálogo para a criação de uma nova conta bancaria.
     */
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

            String randomNumber = generateRandomNumber();

            if (accountType.equals("Conta Corrente")) {
                client.setAccount(new CheckingAccount("0001", randomNumber));
            } else {
                client.setAccount(new SavingAccount("0001", randomNumber));
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
            labelSaldoAtual.setText(formatarMoeda(client.getAccount().getBalance()));
        } else {
            labelSaldoAtual.setText("Zerado!");
            showAccountCreationDialog();
            LoginService.registerAccount(client);
        }
    }

}
