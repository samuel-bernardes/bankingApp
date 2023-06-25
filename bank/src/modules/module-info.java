module banking {
    requires javafx.controls;
    requires javafx.fxml;

    opens banking to javafx.fxml;
    exports banking;
}
