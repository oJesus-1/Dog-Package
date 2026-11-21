package com.application.dogpackage.model;

import java.time.LocalDate;

public class Encomenda {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private LocalDate dataEntrega;
    private LocalDate dataPedido;
    private Status status;
    private Cliente cliente;
    private String URLImagem;

    public Encomenda(String trim, String trim1, double preco, LocalDate value, LocalDate value1, Status value2) {
        
    }

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

    public int getId() {
        return id;
    }

    public String getURLImagem() {
        return URLImagem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setURLImagem(String URLImagem) {
        this.URLImagem = URLImagem;
    }

    public void setID(int i) {
        
    }
}
