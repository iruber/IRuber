package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.Ingrediente;
import com.comercial.iruber.restaurante.dominio.Prato;

import java.math.BigDecimal;

public class PratoDAO {
    private DbHelper bancoDados;
    private IngredienteDAO ingrediente;

    String tabela= DbHelper.TABELA_PRATO;
    String colunaNome=DbHelper.PRATO_NOME;
    String colunaValor=DbHelper.PRATO_VALOR;
    String colunaDescricao=DbHelper.PRATO_DESCRICAO;
    String colunaDisponivel=DbHelper.PRATO_DISPONIVEL;
    String colunaIdRestaurante=DbHelper.PRATO_RESTAURANTE_ID;



    public PratoDAO (Context context){

        bancoDados = new DbHelper(context);
        ingrediente= new IngredienteDAO(context);
    }

    public long inserirPrato(Prato prato) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();

        String nome = prato.getNome();
        values.put(colunaNome, nome);
        BigDecimal valor = prato.getValor();
        values.put(colunaValor, valor.toString());
        String descricao = prato.getDescricao();
        values.put(colunaDescricao, descricao);
        boolean verDisponivel = prato.isDisponivel();
        String disponivel = this.checkDisponivelBolean(verDisponivel);
        values.put(colunaDisponivel, disponivel);
        long idRestaurante= prato.getIdRestaurante();
        values.put(colunaIdRestaurante,idRestaurante);
        long id = bancoEscreve.insert(tabela, null, values);
        return id;
    }
    public Prato criarPrato(Cursor cursor){

        String colunaId = DbHelper.PRATO_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        String colunaNome=DbHelper.PRATO_NOME;
        int indexColunaNome=cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);
        String colunaValor=DbHelper.PRATO_VALOR;
        int indexColunaValor=cursor.getColumnIndex(colunaValor);
        String valor = cursor.getString(indexColunaValor);
        BigDecimal valorDecimal = new BigDecimal(valor);
        String colunaDescricao=DbHelper.PRATO_DESCRICAO;
        int indexColunaDescricao=cursor.getColumnIndex(colunaDescricao);
        String descricao=cursor.getString(indexColunaDescricao);
        String colunaDisponivel=DbHelper.PRATO_DISPONIVEL;
        int indexColunaDisponivel=cursor.getColumnIndex(colunaDisponivel);
        String disponivelString= cursor.getString(indexColunaDisponivel);
        boolean disponivel = this.checkDisponivelString(disponivelString);
        String colunaIdRestaurante = DbHelper.PRATO_RESTAURANTE_ID;
        int indexColunaIdRestaurante= cursor.getColumnIndex(colunaIdRestaurante);
        long idRestaurante = cursor.getLong(indexColunaIdRestaurante);

        Prato prato = new Prato();
        prato.setIdProduto(id);
        prato.setNome(nome);
        prato.setIdRestaurante(idRestaurante);
        prato.setValor(valorDecimal);
        prato.setDescricao(descricao);
        prato.setDisponivel(disponivel);
        return prato;
    }
    public String checkDisponivelBolean(Boolean bolean) {
        if (bolean) {
            return "1";
        } else {
            return "0";
        }
    }
    public Boolean checkDisponivelString(String dispoivel) {
        if (dispoivel == "1") {
            return true;
        } else return false;
    }


    private Prato criar(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Prato prato = null;
        if (cursor.moveToNext()) {
            prato = criarPrato(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return prato;
    }





    public Ingrediente getAllIngredientes(long id) {
        String query =  "SELECT ingrediente.nome" +
                "FROM prato_ingrediente" +
                "INNER JOIN ingrediente" +
                "ON ingrediente.idIngrediente = prato_ingrediente.idIngrediente" +
                "WHERE prato_ingrediente.idPrato = idprato;";
        String[] args = {String.valueOf(id)};
        return this.ingrediente.criar(query, args);
    }




}
