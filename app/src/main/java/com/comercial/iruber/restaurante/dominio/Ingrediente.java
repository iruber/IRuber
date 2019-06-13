package com.comercial.iruber.restaurante.dominio;

public class Ingrediente {
    private long id;
    private String nome;
    private String disponivel;


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

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }
}
