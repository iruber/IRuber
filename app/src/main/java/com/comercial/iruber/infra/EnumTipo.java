package com.comercial.iruber.infra;

import com.comercial.iruber.cliente.dominio.Cliente;
1
public enum EnumTipo {
    CLIENTE("cliente"),RESTAURANTE("restaurante");

    private final String descricao;

    EnumTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
