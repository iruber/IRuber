package com.comercial.iruber.restaurante.dominio;

import com.comercial.iruber.usuario.dominio.Endereco;

import java.util.Date;

public class Entregador {
    private long idEntregador;
    private String email;
    private long idRestaurante;
    private String cpf;
    private String nome;

    public String getNumerotelefone() {
        return numerotelefone;
    }

    public void setNumerotelefone(String numerotelefone) {
        this.numerotelefone = numerotelefone;
    }

    private String numerotelefone;


    public long getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(long idEntregador) {
        this.idEntregador = idEntregador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
