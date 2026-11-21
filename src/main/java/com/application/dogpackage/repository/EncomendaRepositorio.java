package com.application.dogpackage.repository;

import com.application.dogpackage.model.Encomenda;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EncomendaRepositorio {
    private static final ObservableList<Encomenda> lista = FXCollections.observableArrayList();
    private static int id = 0;
    public static ObservableList<Encomenda> getList(){
        return lista;
    }

    public static void addLista(Encomenda e){
        e.setID(id++);
        lista.add(e);
    }
}
