package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.Nota;

import java.math.BigDecimal;

public class IngredientePratoDAO {
    private DbHelper bancoDados;
    private Context contexto;


    public IngredientePratoDAO(Context context){
        bancoDados = new DbHelper(context);
        contexto = context;
    }

    public long inserirPratoIngrediente(long idPrato,long idIngrediente){
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ContratoIngredientePrato.INGREDIENTE_PRATO_ID_INGREDIENTE,idIngrediente);
        values.put(ContratoIngredientePrato.INGREDIENTE_PRATO_ID_PRATO,idPrato);
        return bancoEscreve.insert(ContratoIngredientePrato.NOME_TABELA, null, values);
    }

}
