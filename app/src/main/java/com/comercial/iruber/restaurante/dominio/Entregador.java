package com.comercial.iruber.restaurante.dominio;

import com.comercial.iruber.usuario.dominio.Endereco;

import java.util.Date;

public class Entregador {
    private long idEntregador;
    private long numeroEntregas;
    private long idRestaurante;
    private String cpf;
    private String nome;


    public long getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(long idEntregador) {
        this.idEntregador = idEntregador;
    }

    public long getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(long numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
