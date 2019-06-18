package com.comercial.iruber.restaurante.dominio;

import com.comercial.iruber.pedido.dominio.StatusDisponibilidade;

import java.io.Serializable;

public class Ingrediente implements Serializable {
    private long id;
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
