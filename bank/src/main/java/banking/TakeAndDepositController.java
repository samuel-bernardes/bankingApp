package banking;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.UnaryOperator;

import classes.abstractClass.Account;
import classes.abstractClass.Client;
import classes.services.Encryption;
import classes.services.FileService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;

/**
 * Classe controladora para a tela de saque e depósito.
 */
public class TakeAndDepositController {

    @FXML
    private Button buttonConcluir;

    @FXML
    private Button buttonVoltar;

    @FXML
    private RadioButton buttonDeposit;

    @FXML
    private RadioButton buttonSacar;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldValue;

    private boolean saqueEnabled = false;

    /**
     * Limpa os campos da tela.
     */
    private void cleanFields() {
        fieldPassword.setText("");
        fieldValue.setText("");
        saqueEnabled = false;
        buttonDeposit.setSelected(false);
        buttonSacar.setSelected(false);
    }

    private NumberFormat currencyFormat;

    /**
     * Manipulador de eventos para o botão "Voltar".
     * Redireciona para a página inicial (homePage).
     *
     * @param event O evento de clique do botão.
     * @throws Exception Se ocorrer um erro durante a transição de tela.
     */
    @FXML
    void clickedVoltar(ActionEvent event) throws Exception {
        App.setRoot("homePage", 420, 700);
    }

    /**
     * Manipulador de eventos para o botão "Concluir".
     * Realiza a operação de saque ou depósito com base nas informações fornecidas.
     *
     * @param event O evento de clique do botão.
     */
    @FXML
    void clickedConcluir(ActionEvent event) {
        Client loggedClient = App.getLoggedInClient();
        Account loggedClientAccount = loggedClient.getAccount();
        FileService fileService = new FileService();

        if (Encryption.encrypt(fieldPassword.getText()).equals(loggedClient.getPassword())) {
            if (saqueEnabled) {
                if (Double.valueOf(fieldValue.getText()) > loggedClientAccount.getBalance()) {
                    App.showAlert("Você não tem saldo suficiente!", "Aviso!", "warning");
                    cleanFields();
                    return;
                }
                loggedClientAccount.setBalance(loggedClientAccount.getBalance() - Double.valueOf(fieldValue.getText()));
                App.showAlert("Saque efetuado com sucesso!", "Parabéns!", "success");
            } else {
                loggedClientAccount.setBalance(loggedClientAccount.getBalance() + Double.valueOf(fieldValue.getText()));
                App.showAlert("Depósito efetuado com sucesso!", "Parabéns!", "success");
            }
            cleanFields();
        } else {
            App.showAlert("Senha incorreta!", "Digite sua senha corretamente para realizar a operação!", "error");
        }

        loggedClient.setAccount(loggedClientAccount);
        App.setLoggedInClient(loggedClient);
        fileService.saveAccount(loggedClient);
    }

    /**
     * Manipulador de eventos para o botão "Depósito".
     * Habilita a opção de depósito e desabilita a opção de saque.
     *
     * @param event O evento de clique do botão.
     */
    @FXML
    void clickedDeposit(ActionEvent event) {
        saqueEnabled = false;
        buttonSacar.setSelected(false);
    }

    /**
     * Manipulador de eventos para o botão "Sacar".
     * Habilita a opção de saque e desabilita a opção de depósito.
     *
     * @param event O evento de clique do botão.
     */
    @FXML
    void clickedSacar(ActionEvent event) {
        saqueEnabled = true;
        buttonDeposit.setSelected(false);
    }

    /**
     * Inicializa a tela de saque e depósito.
     */
    public void initialize() {
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*\\.?\\d{0,2}")) {
                return change;
            }
            return null;
        };

        TextFormatter<Double> textFormatter = new TextFormatter<>(new DoubleStringConverter(), null, filter);

        fieldValue.setTextFormatter(textFormatter);
    }
}
