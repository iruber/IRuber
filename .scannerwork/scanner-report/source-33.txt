package com.comercial.iruber.restaurante.dominio;

public class Ingrediente {
    private long idIngrediente;
    private String nome;
    private long idPrato;

    public long getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(long idPrato) {
        this.idPrato = idPrato;
    }

    private boolean disponivel;

    public long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
