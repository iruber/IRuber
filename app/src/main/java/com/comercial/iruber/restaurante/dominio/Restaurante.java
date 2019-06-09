package com.comercial.iruber.restaurante.dominio;
import com.comercial.iruber.usuario.dominio.Usuario;

import com.comercial.iruber.usuario.dominio.Endereco;
import com.comercial.iruber.usuario.dominio.Usuario;

import java.util.List;

public class Restaurante {
    private long idRestaurante;
    private double nota;
    private Usuario usuario;
    private List<Ingrediente> ingredientes;
    private List<Prato> pratos;
    private List<Entregador> entregadores;
    private Endereco endereco;
    private String nome;
    private String cnpj;

    public long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public List<Entregador> getEntregadores() {
        return entregadores;
    }

    public void setEntregadores(List<Entregador> entregadores) {
        this.entregadores = entregadores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
