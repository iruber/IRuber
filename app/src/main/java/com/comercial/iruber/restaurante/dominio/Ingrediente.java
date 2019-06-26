package com.comercial.iruber.restaurante.dominio;

import com.comercial.iruber.pedido.dominio.StatusDisponibilidade;

import java.io.Serializable;

public class Ingrediente implements Serializable {
    private long id;
    private long idPrato;
    private String nome;
    private StatusDisponibilidade disponivel;
    private boolean isChecked = false;


    public long getIdIngrediente() {
        return id;
    }

    public void setIdIngrediente(long idIngrediente) {
        this.id = idIngrediente;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(long idPrato) {
        this.idPrato = idPrato;
    }

    public void setDisponivel(StatusDisponibilidade disponivel) {
        this.disponivel = disponivel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusDisponibilidade getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String StatusDisponibilidade) {
        this.disponivel = disponivel;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
