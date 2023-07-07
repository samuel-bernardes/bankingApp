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
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static Client loggedInClient;

    public static void setLoggedInClient(Client client) {
        loggedInClient = client;
    }

    public static Client getLoggedInClient() {
        return loggedInClient;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 360, 540);
        stage.setScene(scene);
        stage.show();
    }

    public static void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    static void setRoot(String fxml, int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();

        scene.setRoot(root);

        Stage stage = (Stage) scene.getWindow();
        stage.setWidth(width);
        stage.setHeight(height);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}