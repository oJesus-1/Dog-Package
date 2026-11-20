package com.application.dogpackage.model;

import java.time.LocalDate;

public class Encomenda {
    private String nome;
    private String descricao;
    private double preco;
    private LocalDate dataEntrega;
    private LocalDate dataPedido;
    private Status status;
    private Cliente cliente;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public Status getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
