package com.comercial.iruber.restaurante.dominio;

import com.comercial.iruber.usuario.dominio.Usuario;

public class Entregador {
    private long idEntregador;
    private long idRestaurante;
    private String nome;
    private String telefone;
    private Usuario usuario;
    private EnumEntregador estado;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(long idEntregador) {
        this.idEntregador = idEntregador;
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

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public EnumEntregador getEstado() { return estado; }

    public void setEstado(EnumEntregador estado) { this.estado = estado; }
}
