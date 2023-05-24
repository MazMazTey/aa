module aa {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports view;
//    exports model.User;
    opens view to javafx.fxml;
    opens model.database to com.google.gson;
}