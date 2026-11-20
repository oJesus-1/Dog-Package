module com.dogpack.dogpack {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.application.dogpackage to javafx.fxml;
    exports com.application.dogpackage.controller;
    opens com.application.dogpackage.controller to javafx.fxml;
    exports com.application.dogpackage;
}