module com.dogpack.dogpack {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.application.dogpackage to javafx.fxml;
    exports com.application.dogpackage;
    exports com.application.dogpackage.controller;
    opens com.application.dogpackage.controller to javafx.fxml;
}