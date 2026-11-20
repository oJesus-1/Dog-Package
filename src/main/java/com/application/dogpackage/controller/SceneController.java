package com.application.dogpackage.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class SceneController {
        @FXML
        private StackPane areaConteudo;

        @FXML
        private void abrirClientes() throws IOException {
            Node tela = FXMLLoader.load(getClass().getResource("/com/application/dogpackage/tela-incial.fxml"));
            areaConteudo.getChildren().setAll(tela);
        }

        @FXML
        private void abrirCadastro() throws IOException {
            Node tela = FXMLLoader.load(getClass().getResource("/com/application/dogpackage/tela-cadastro.fxml"));
            areaConteudo.getChildren().setAll(tela);
        }
    }

