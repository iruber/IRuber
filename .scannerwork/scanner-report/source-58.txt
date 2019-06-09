package com.comercial.iruber.restaurante.persistencia;

public class ContratoIngredientePrato {

    public static final String NOME_TABELA = "ingredientePrato";
    public static final String INGREDIENTE_PRATO_ID = "id";
    public static final String INGREDIENTE_PRATO_ID_INGREDIENTE = "idIngrediente";
    public static final String INGREDIENTE_PRATO_ID_PRATO = "idPrato";
    public static final String SQL_CREATE_TABLE_INGREDIENTE_PRATO =
            "CREATE TABLE " + NOME_TABELA + "(" +
                    INGREDIENTE_PRATO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    INGREDIENTE_PRATO_ID_INGREDIENTE + " TEXT NOT NULL, " +
                    INGREDIENTE_PRATO_ID_PRATO + " TEXT NOT NULL)";
    public static final String SQL_DELETE_INGREDIENTE_PRATO =
            "DROP TABLE IF EXISTS " + ContratoIngredientePrato.NOME_TABELA;

    private ContratoIngredientePrato() {
    }

}
