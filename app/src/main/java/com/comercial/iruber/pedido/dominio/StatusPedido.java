package com.comercial.iruber.pedido.dominio;

public enum StatusPedido {
    EMESPERA(1),
    EM_PREPARO(2),
    SAIU_ENTREGA(3),
    ENTREGUE(4),
    RECUSADO(5);
    private final int i;

    StatusPedido(int i) {
        this.i = i;
    }
}