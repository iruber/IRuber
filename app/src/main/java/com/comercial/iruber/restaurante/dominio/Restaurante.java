package com.comercial.iruber.restaurante.dominio;

import java.util.ArrayList;

public class Restaurante {
    private long idRestaurante;
    private String cnpj;
    private String nome;
    private String email;
    private double nota;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<Prato> pratos;
    private ArrayList<Entregador> entregadores;

    public long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public ArrayList<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(ArrayList<Prato> pratos) {
        this.pratos = pratos;
    }

    public ArrayList<Entregador> getEntregadores() {
        return entregadores;
    }

    public void setEntregadores(ArrayList<Entregador> entregadores) {
        this.entregadores = entregadores;
    }
}
