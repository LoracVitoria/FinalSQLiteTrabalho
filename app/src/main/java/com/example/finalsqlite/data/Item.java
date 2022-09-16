package com.example.finalsqlite.data;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String titulo;
    private Integer prioridade;
    private String descricao;
    private String cor;

    public Item(int id, String titulo, Integer prioridade, String descricao, String cor) {
        this.id = id;
        this.titulo = titulo;
        this.prioridade = prioridade;
        this.descricao = descricao;
        this.cor = cor;
    }

    public Item(String titulo, Integer prioridade, String descricao, String cor) {
        this.titulo = titulo;
        this.prioridade = prioridade;
        this.descricao = descricao;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
