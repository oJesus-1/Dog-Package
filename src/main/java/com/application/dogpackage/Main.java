package com.application.dogpackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/application/dogpackage/tela-principal.fxml"));
        Scene scene = new Scene(loader.load()); //nova cena
        stage.setTitle("DogPackage");
        stage.setScene(scene);//seta a scene no stage
        stage.show(); //mostra o stage
    }
}
