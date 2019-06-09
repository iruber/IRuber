package com.comercial.iruber.restaurante.dominio;

public class Entregador {
    private long idEntregador;
    private long numeroEntregas;
    private String nome;
    private String login;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
