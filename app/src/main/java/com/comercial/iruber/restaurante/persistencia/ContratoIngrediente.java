package com.comercial.iruber.restaurante.persistencia;

public class ContratoIngrediente {
    private static final String TEXT_NOT_NULL = " TEXT NOT NULL,";
    private static final String INTEGER_NOT_NULL = " TEXT NOT NULL,";
    public static final String NOME_TABELA = "ingrediente";
    public static final String INGREDIENTE_ID_PRATO = "idPrato";
    public static final String INGREDIENTE_ID = "id";
    public static final String INGREDIENTE_NOME = "nome";
    public static final String INGREDIENTE_DISPONIVEL = "disponivel";
    public static final String INGREDIENTE_ID_RESTAURANTE = "idRestaurante";
    public static final String SQL_CREATE_TABLE_INGREDIENTE =
            "CREATE TABLE " + NOME_TABELA + "(" +
                    INGREDIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    INGREDIENTE_NOME + TEXT_NOT_NULL +
                    INGREDIENTE_DISPONIVEL + "TEXT, " +
                    INGREDIENTE_ID_RESTAURANTE + INTEGER_NOT_NULL +
                    INGREDIENTE_ID_PRATO + " INTEGER" + ")";
    public static final String SQL_DELETE_INGREDIENTE =
            "DROP TABLE IF EXISTS " + ContratoIngrediente.NOME_TABELA;

    private ContratoIngrediente() {
    }
}
