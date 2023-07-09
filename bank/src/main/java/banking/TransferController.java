package banking;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.UnaryOperator;

import classes.abstractClass.Client;
import classes.services.AccountService;
import classes.services.Encryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;

/**
 * Classe controladora para a tela de transferência.
 */
public class TransferController {

    @FXML
    private Button buttonTransfer;

    @FXML
    private Button buttonVoltar;

    @FXML
    private TextField fieldAgency;

    @FXML
    private TextField fieldNumber;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldValue;

    private NumberFormat currencyFormat;

    /**
     * Limpa os campos da tela.
     */
    private void cleanFields() {
        fieldAgency.setText("");
        fieldNumber.setText("");
        fieldPassword.setText("");
        fieldValue.setText("");
    }

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
     * Manipulador de eventos para o botão "Transferir".
     * Realiza a transferência entre contas com base nas informações fornecidas.
     *
     * @param event O evento de clique do botão.
     */
    @FXML
    void clickedTransfer(ActionEvent event) {

        Client transferClient;
        try {
            transferClient = AccountService.findClient(fieldNumber.getText(), fieldAgency.getText());
            if (Encryption.encrypt(fieldPassword.getText()).equals(App.getLoggedInClient().getPassword())) {
                if (Double.valueOf(fieldValue.getText()) > App.getLoggedInClient().getAccount().getBalance()) {
                    App.showAlert("Você não tem saldo suficiente!", "Aviso!", "warning");
                    cleanFields();
                    return;
                }
                AccountService.transferMoney(App.getLoggedInClient(), transferClient,
                        Double.valueOf(fieldValue.getText()));
                App.showAlert("Transferência efetuada com sucesso!", "Parabéns!", "success");
            } else {
                App.showAlert("Senha incorreta!", "Digite sua senha corretamente para realizar a operação!", "error");
            }
            cleanFields();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * Inicializa a tela de transferência.
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
