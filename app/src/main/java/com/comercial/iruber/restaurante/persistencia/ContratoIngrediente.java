package com.comercial.iruber.restaurante.persistencia;

public class ContratoIngrediente {
    public static final String NOME_TABELA = "ingrediente";
    public static final String INGREDIENTE_ID_PRATO="idPrato";
    public static final String INGREDIENTE_ID = "idIngrediente";
    public static final String INGREDIENTE_NOME = "nome";
    public static final String INGREDIENTE_DISPONIVEL="disponivel";
    public static final String SQL_CREATE_TABLE_INGREDIENTE =
            "CREATE TABLE "+NOME_TABELA+"(" +
                    INGREDIENTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    INGREDIENTE_NOME+ " TEXT NOT NULL, " +
                    INGREDIENTE_DISPONIVEL+" TEXT NOT NULL, " +
                    INGREDIENTE_ID_PRATO+" TEXT" + ")" ;
    public static final String SQL_DELETE_INGREDIENTE =
            "DROP TABLE IF EXISTS " + ContratoIngrediente.NOME_TABELA;
}
