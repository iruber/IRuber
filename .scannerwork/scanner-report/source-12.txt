package com.comercial.iruber.infra;

public enum EnumTipo {
    CLIENTE("cliente"), RESTAURANTE("restaurante"), ENTREGADOR ("entregador");
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
