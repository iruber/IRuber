package com.comercial.iruber.restaurante.persistencia;

public class ContratoNota {
    private static final String TEXT_NOT_NULL = " TEXT NOT NULL,";
    public static final String NOME_TABELA = "nota";
    public static final String NOTA_ID = "id";
    public static final String NOTA_VALOR = "valor";
    public static final String NOTA_ID_RESTAURANTE = "idRestaurante";
    public static final String NOTA_ID_CLIENTE = "idCliente";
    public static final String SQL_CREATE_TABLE_NOTA =
            "CREATE TABLE " + NOME_TABELA + "(" +
                    NOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTA_ID_RESTAURANTE + TEXT_NOT_NULL +
                    NOTA_ID_CLIENTE + TEXT_NOT_NULL +
                    NOTA_VALOR + " REAL NOT NULL" + ")";
    public static final String SQL_DELETE_NOTA =
            "DROP TABLE IF EXISTS " + ContratoNota.NOME_TABELA;
}
