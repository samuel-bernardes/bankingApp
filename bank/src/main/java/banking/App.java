package banking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import classes.abstractClass.Client;

/**
 * Classe principal da aplicação JavaFX.
 */
public class App extends Application {

    private static Scene scene;

    private static Client loggedInClient;

    /**
     * Define o cliente logado na aplicação.
     * 
     * @param client O cliente logado.
     */
    public static void setLoggedInClient(Client client) {
        loggedInClient = client;
    }

    /**
     * Retorna o cliente logado na aplicação.
     * 
     * @return O cliente logado.
     */
    public static Client getLoggedInClient() {
        return loggedInClient;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 360, 540);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exibe um diálogo de alerta na aplicação.
     * 
     * @param title   O título do alerta.
     * @param message A mensagem do alerta.
     * @param type    O tipo de alerta (error, warning ou information).
     */
    public static void showAlert(String title, String message, String type) {
        Alert alert = new Alert(AlertType.INFORMATION);

        if (type.equals("error")) {
            alert = new Alert(AlertType.ERROR);
        } else if (type.equals("warning")) {
            alert = new Alert(AlertType.WARNING);
        }

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Define a cena principal da aplicação.
     * 
     * @param fxml   O nome do arquivo FXML para carregar.
     * @param width  A largura da janela.
     * @param height A altura da janela.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML.
     */
    static void setRoot(String fxml, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();

        scene.setRoot(root);

        Stage stage = (Stage) scene.getWindow();
        stage.setWidth(width);
        stage.setHeight(height);
    }

    /**
     * Carrega um arquivo FXML.
     * 
     * @param fxml O nome do arquivo FXML para carregar.
     * @return O nó raiz do arquivo FXML carregado.
     * @throws IOException Se ocorrer um erro ao carregar o arquivo FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * O método principal da aplicação.
     * 
     * @param args Os argumentos de linha de comando.
     */
    public static void main(String[] args) {
        launch();
    }
}
