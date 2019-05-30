package com.comercial.iruber.usuario.dominio;
import com.comercial.iruber.infra.EnumTipo;

public class Usuario {
    private long id;
    private String email;
    private String senha;
    private EnumTipo tipo;
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

