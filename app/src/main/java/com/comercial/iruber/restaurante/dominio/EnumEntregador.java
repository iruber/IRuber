package com.comercial.iruber.restaurante.dominio;

public enum EnumEntregador {
    DISPONIVEL("disponivel"), ENTREGANDO("entregando"),
    INDISPONIVEL("indisponivel"), DESABILITADO("desabilitado");

    private String descricao;

    EnumEntregador(String descricao) { this.descricao = descricao; }

    public String getDescricao() { return this.descricao; }

    @Override
    public String toString() { return this.descricao; }
}
