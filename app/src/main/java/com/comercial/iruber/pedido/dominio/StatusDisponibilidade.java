package com.comercial.iruber.pedido.dominio;

public enum StatusDisponibilidade {
    ATIVO("ativo"), DESATIVADO("desativado"), EM_FALTA ("emFalta");
    private final String descricao;

    StatusDisponibilidade(String descricao) {
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
