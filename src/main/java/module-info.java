module aa {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires annotations;

    exports view;
    opens view to javafx.fxml;
    opens model to com.google.gson;
    opens model.database to com.google.gson;
}