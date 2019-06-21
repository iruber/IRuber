package com.comercial.iruber.pedido.dominio;

public enum StatusPedido {
    EM_ESPERA("EM_ESPERA"),
    EM_PREPARO("EM_PREPARO"),
    SAIU_ENTREGA("SAIU_ENTREGA"),
    ENTREGUE("ENTREGUE"),
    RECUSADO("RECUSADO");
    private final String descricao;

    StatusPedido(String descricao) {
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