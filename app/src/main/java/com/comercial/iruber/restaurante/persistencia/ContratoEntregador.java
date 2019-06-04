package com.comercial.iruber.restaurante.persistencia;

public class ContratoEntregador {

    public static final String NOME_TABELA = "entregador";
    public static final String ENTREGADOR_ID = "id";
    public static final String ENTREGADOR_NOME = "nome";
    public static final String ENTREGADOR_ID_RESTAURANTE = "idRestaurante";
    public static final String ENTREGADOR_CPF = "cpf";
    public static final String ENTREGADOR_NUMERO_ENTREGAS = "numeroEntrega";
    public static final String SQL_CREATE_TABLE_ENTREGADOR =
            "CREATE TABLE "+ NOME_TABELA + " (" +
                    ENTREGADOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ENTREGADOR_NOME + "TEXT NOT NULL, " +
                    ENTREGADOR_NUMERO_ENTREGAS + "TEXT NOT NULL, " +
                    ENTREGADOR_CPF + "TEXT NOT NULL, " +
                    ENTREGADOR_ID_RESTAURANTE + " TEXT NOT NULL" + ")";
    public static final String SQL_DELETE_ENTREGADOR =
            "DROP TABLE IF EXISTS " + ContratoEntregador.NOME_TABELA;
}

