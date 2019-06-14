package com.comercial.iruber.restaurante.persistencia;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.pedido.dominio.StatusDisponibilidade;
import com.comercial.iruber.pedido.dominio.StatusPedido;
import com.comercial.iruber.restaurante.dominio.Ingrediente;


public class IngredienteDAO {
    private static final String SELECT_FROM_INGREDIENTE = "SELECT * FROM ingrediente ";
    private DbHelper bancoDados;
    private Context contexto;


    public IngredienteDAO(Context context) {
        bancoDados = new DbHelper(context);
        contexto = context;
    }

    public long inserirIngrediente(Ingrediente ingrediente) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        String nome = ingrediente.getNome();
        values.put(ContratoIngrediente.INGREDIENTE_NOME, nome);
        values.put(ContratoIngrediente.INGREDIENTE_DISPONIVEL, StatusDisponibilidade.ATIVO.getDescricao());
        values.put(ContratoIngrediente.INGREDIENTE_ID_RESTAURANTE, Sessao.getSessaoRestaurante(contexto).getIdRestaurante());
        long id = bancoEscreve.insert(ContratoIngrediente.NOME_TABELA, null, values);
        bancoEscreve.close();
        return id;
    }

    private Ingrediente criarIngrediente(Cursor cursor) {
        String colunaId = ContratoIngrediente.INGREDIENTE_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        String colunaNome = ContratoIngrediente.INGREDIENTE_NOME;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);
        String colunaDisponivel = ContratoIngrediente.INGREDIENTE_DISPONIVEL;
        int indexColunaDisponivel = cursor.getColumnIndex(colunaDisponivel);
        String disponivel = cursor.getString(indexColunaDisponivel);
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setDisponivel(disponivel);
        ingrediente.setNome(nome);
        ingrediente.setIdIngrediente(id);

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
        String query = SELECT_FROM_INGREDIENTE +
                "WHERE  id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }

    public Ingrediente getIngredientePorNome(String nome, long idRestaurante) {
        String query = SELECT_FROM_INGREDIENTE +
                "WHERE  nome = ?" +
                "AND idRestaurante = ?";
        String[] args = {nome, String.valueOf(idRestaurante)};
        return this.criar(query, args);
    }

    public Ingrediente getIngredientePorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_INGREDIENTE +
                "WHERE  idRestaurante = ?";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criar(query, args);
    }

    public ArrayList<Ingrediente> getIngredientesPorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_INGREDIENTE +
                "WHERE idRestaurante = ?";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criarListaIngredientes(query, args);
    }

    public ArrayList<Ingrediente> getIngredientesDisponiveisPorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_INGREDIENTE +
                "WHERE idRestaurante = ? " +
                "AND disponivel = " + StatusDisponibilidade.ATIVO.getDescricao() + ";";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criarListaIngredientes(query, args);
    }

    public ArrayList<Ingrediente> getIngredientesIndisponiveisPorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_INGREDIENTE +
                "WHERE idRestaurante = ? " +
                "AND disponivel = " + StatusDisponibilidade.DESATIVADO.getDescricao() + ";";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criarListaIngredientes(query, args);
    }

    public ArrayList<Ingrediente> getIngredientesEmFaltaPorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_INGREDIENTE +
                "WHERE idRestaurante = ? " +
                "AND disponivel = " + StatusDisponibilidade.EM_FALTA.getDescricao() + ";";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criarListaIngredientes(query, args);


    }

    public void updateIngrediente(Ingrediente ingrediente) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoIngrediente.INGREDIENTE_NOME, ingrediente.getNome());
        values.put(ContratoIngrediente.INGREDIENTE_DISPONIVEL,ingrediente.getDisponivel().getDescricao());
        String[] args = {String.valueOf(ingrediente.getIdIngrediente())};
        escritorBanco.update(ContratoIngrediente.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }



















    private ArrayList<Ingrediente> criarListaIngredientes(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        ArrayList<Ingrediente> listaIngredientes = new ArrayList<>();
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