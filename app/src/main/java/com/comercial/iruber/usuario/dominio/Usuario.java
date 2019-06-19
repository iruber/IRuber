package com.comercial.iruber.usuario.dominio;

import android.graphics.Bitmap;

import com.comercial.iruber.infra.EnumTipo;

public class Usuario {
    private long id;
    private String email;
    private String senha;
    private EnumTipo tipo;
    private Bitmap imagem;

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumTipo getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipo tipo) {
        this.tipo = tipo;
    }
}

