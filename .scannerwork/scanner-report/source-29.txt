package com.comercial.iruber.pedido.dominio.persistencia;

public class ContratoPedido {

    private static final String TEXT_NOT_NULL = " TEXT NOT NULL,";
    public static final String NOME_TABELA = "pedido";
    public static final String PEDIDO_ID = "id";
    public static final String PEDIDO_RESTAURANTE_ID = "idRestaurante";
    public static final String PEDIDO_CLIENTE_ID = "idCliente";
    public static final String PEDIDO_ENTREGADOR_ID = "idEntregador";
    public static final String PEDIDO_STATUS = "status";
    public static final String PEDIDO_DATA = "datapedido";
    public static final String PEDIDO_VALORTOTAL = "valorTotal";
    public static final String PEDIDO_ID_ITEMPEDIDO = "idItemPedido";
    public static final String SQL_CREATE_TABLE_PEDIDO =
            "CREATE TABLE " + NOME_TABELA + "(" +
                    PEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PEDIDO_CLIENTE_ID + TEXT_NOT_NULL +
                    PEDIDO_RESTAURANTE_ID + TEXT_NOT_NULL +
                    PEDIDO_ENTREGADOR_ID + TEXT_NOT_NULL +
                    PEDIDO_ID_ITEMPEDIDO + TEXT_NOT_NULL +
                    PEDIDO_STATUS + " TEXT, " +
                    PEDIDO_VALORTOTAL + TEXT_NOT_NULL +
                    PEDIDO_DATA + " TEXT NOT NULL);";
    public static final String SQL_DELETE_PEDIDO =
            "DROP TABLE IF EXISTS " + ContratoPedido.NOME_TABELA;

    private ContratoPedido(){}

}

