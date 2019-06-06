package com.comercial.iruber.restaurante.persistencia;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.Ingrediente;




public class IngredienteDAO {
    private DbHelper bancoDados;


    public IngredienteDAO(Context context) {
        bancoDados = new DbHelper(context);

    }

    public long inserirIngrediente(Ingrediente ingrediente) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        String nome = ingrediente.getNome();
        values.put(ContratoIngrediente.INGREDIENTE_NOME, nome);
        Boolean disponivel = ingrediente.isDisponivel();
        String valueDisponivel = this.checkDisponivelBolean(disponivel);
        values.put(ContratoIngrediente.INGREDIENTE_DISPONIVEL, valueDisponivel);
        long idPrato = ingrediente.getIdPrato();
        values.put(ContratoIngrediente.INGREDIENTE_ID, idPrato);
        long id = bancoEscreve.insert(ContratoIngrediente.NOME_TABELA, null, values);
        bancoEscreve.close();
        return id;
    }

    public String checkDisponivelBolean(Boolean bolean) {
        if (bolean) {
            return "true";
        } else {
            return "false";
        }
    }

    public Boolean checkDisponivelString(String dispoivel) {
        if (dispoivel == "true") {
            return true;
        } else return false;
    }

    public Ingrediente criarIngrediente(Cursor cursor) {
        String colunaId = ContratoIngrediente.INGREDIENTE_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        String colunaNome = ContratoIngrediente.INGREDIENTE_NOME;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);
        String colunaDisponivel = ContratoIngrediente.INGREDIENTE_DISPONIVEL;
        int indexColunaDisponivel = cursor.getColumnIndex(colunaDisponivel);
        String disponivel = cursor.getString(indexColunaDisponivel);
        boolean isDisponivel = checkDisponivelString(disponivel);
        String colunaIdPrato = ContratoIngrediente.INGREDIENTE_ID_PRATO;
        int indexColunaIdPrato = cursor.getColumnIndex(colunaIdPrato);
        long idPrato = cursor.getLong(indexColunaIdPrato);
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setDisponivel(isDisponivel);
        ingrediente.setNome(nome);
        ingrediente.setIdIngrediente(id);
        ingrediente.setIdPrato(idPrato);
        return ingrediente;
    }

    public Ingrediente criar(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Ingrediente ingrediente = null;
        if (cursor.moveToNext()) {
            ingrediente = criarIngrediente(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return ingrediente;
    }


    public Ingrediente getIngredientePorId(long id) {
        String query = "SELECT * FROM ingrediente " +
                "WHERE  id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }

    public Ingrediente getIngredientePorNome(String nome,long idRestaurante) {
        String query = "SELECT * FROM ingrediente " +
                "WHERE  nome = ?" +
                "AND idRestaurante = ?";
        String[] args = {nome,String.valueOf(idRestaurante)};
        return this.criar(query, args);
    }

    public Ingrediente getIngredientePorIdRestaurante(long idRestaurante) {
        String query = "SELECT * FROM ingrediente " +
                "WHERE  idRestaurante = ?";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criar(query, args);
    }

    public ArrayList<Ingrediente> getIngredientesAtivosPorIdRestaurante(long idRestaurante) {
        String query = "SELECT * FROM ingrediente " +
                "WHERE idRestaurante = ? " +
                "AND disponivel = 'true'";

        String[] args = {String.valueOf(idRestaurante),"true"};
        return this.criarMuitosIngredientes(query, args);
    }

    public void updateIngrediente(Ingrediente ingrediente) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoIngrediente.INGREDIENTE_NOME, ingrediente.getNome());
        String[] args = {String.valueOf(ingrediente.getIdIngrediente())};
        escritorBanco.update(ContratoIngrediente.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }

    public void desabilitarIngrediente(Ingrediente ingrediente) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoIngrediente.INGREDIENTE_DISPONIVEL, "false");
        String[] args = {String.valueOf(ingrediente.getIdIngrediente())};
        escritorBanco.update(ContratoIngrediente.NOME_TABELA, values, query, args);
        escritorBanco.close();

    }

    public ArrayList<Ingrediente> criarMuitosIngredientes(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        ArrayList<Ingrediente> listaIngredientes = new ArrayList();

        Ingrediente ingrediente = null;
        if (cursor.moveToFirst()) {
            do {
                ingrediente = criarIngrediente(cursor);
                listaIngredientes.add(ingrediente);

            } while (cursor.moveToNext());
        }
        cursor.close();
        leitorBanco.close();
        return listaIngredientes;
    }

}