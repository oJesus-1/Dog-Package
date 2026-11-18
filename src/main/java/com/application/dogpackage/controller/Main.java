package com.application.dogpackage.controller;

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


        Parent root = FXMLLoader.load(getClass().getResource("tela-inicial.fxml"));//novo nó
        Scene scene = new Scene(root); //nova cena

        stage.setTitle("DogPack");
        stage.setScene(scene);//seta a scene no stage

        stage.show(); //mostra o stage
    }
}
