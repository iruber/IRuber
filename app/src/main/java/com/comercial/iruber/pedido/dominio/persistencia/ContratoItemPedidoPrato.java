package com.comercial.iruber.pedido.dominio.persistencia;

public class ContratoItemPedidoPrato {

    public static final String NOME_TABELA = "itemPedidoPrato";
    public static final String ITEM_PEDIDO_PRATO_ITEMPEDIDO_ID = "id";
    public static final String ITEM_PEDIDO_PRATO_PRATO_ID = "idPrato";
    public static final String ITEM_PEDIDO_PRATO_ITEM_PEDIDO_ID = "idItemPedido";
    public static final String ITEM_PEDIDO_PRATO_QUANTIDADE="quantidade";

    public static final String SQL_CREATE_TABLE_ITEM_PEDIDO_PRATO =
            "CREATE TABLE " + NOME_TABELA + "(" +
                    ITEM_PEDIDO_PRATO_ITEMPEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ITEM_PEDIDO_PRATO_PRATO_ID + " TEXT NOT NULL, " +
                    ITEM_PEDIDO_PRATO_ITEM_PEDIDO_ID + " TEXT NOT NULL, " +
                    ITEM_PEDIDO_PRATO_QUANTIDADE + " TEXT NOT NULL);";
    public static final String SQL_DELETE_ITEM_PEDIDO_PRATO =
            "DROP TABLE IF EXISTS " + ContratoItemPedido.NOME_TABELA;

    private ContratoItemPedidoPrato(){}
}
