package com.application.dogpackage.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class PrincipalController  {
    @FXML
    private Button btnCadastro;
    @FXML
    private Button btnInicio;
    @FXML
    StackPane areaConteudo = new StackPane();

    @FXML
    public void inicio() throws IOException {
       carregar("tela-inicial.fxml");
    }
    @FXML
    public void cadastro() throws IOException{
        carregar("tela-cadastro.fxml");
    }

    public void carregar(String end) throws IOException {
        Node tela = FXMLLoader.load(getClass().getResource("/com/application/dogpackage/" + end));
        areaConteudo.getChildren().setAll(tela);;
    }
}
