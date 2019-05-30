package com.comercial.iruber.restaurante.persistencia;

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
        long idPrato=ingrediente.getIdPrato();
        values.put(ContratoIngrediente.INGREDIENTE_ID,idPrato);
        long id = bancoEscreve.insert(ContratoIngrediente.NOME_TABELA, null, values);
        bancoEscreve.close();
        return id;
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
        int indexColunaIdPrato= cursor.getColumnIndex(colunaIdPrato);
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
    public Ingrediente getIngredientePorNome(long id) {
        String query = "SELECT * FROM ingrediente " +
                "WHERE  nome = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);

    }
    public Ingrediente getIngredientePorId(long id) {
        String query = "SELECT * FROM ingrediente " +
                "WHERE  idIngrediente = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }
    public Ingrediente getIngredientePorIdRestaurante(long id) {
        String query = "SELECT * FROM ingrediente " +
                "WHERE  idRestaurante = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }
    public Ingrediente getIngredientesAtivosPorIdRestaurante(long id) {
        String query = "SELECT * FROM ingrediente " +
                "WHERE idRestaurante = ?" +
                "AND disponivel = '1'";

        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }
    public void updateIngrediente(Ingrediente ingrediente) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "idIngrediente = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoIngrediente.INGREDIENTE_NOME, ingrediente.getNome());
        String[] args = {String.valueOf(ingrediente.getIdIngrediente())};
        escritorBanco.update(ContratoIngrediente.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }
    public void desabilitarIngrediente(Ingrediente ingrediente) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "idIngrediente = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoIngrediente.INGREDIENTE_DISPONIVEL, "0");
        String[] args = {String.valueOf(ingrediente.getIdIngrediente())};
        escritorBanco.update(ContratoIngrediente.NOME_TABELA, values, query, args);
        escritorBanco.close();

    }


}