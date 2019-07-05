package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.pedido.dominio.StatusDisponibilidade;

import com.comercial.iruber.pedido.dominio.persistencia.ContratoItemPedido;
import com.comercial.iruber.restaurante.dominio.Prato;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PratoDAO {
    private static final String SELECT_FROM_PRATO = "SELECT * FROM prato ";
    private DbHelper bancoDados;

    public PratoDAO(Context context) {
        bancoDados = new DbHelper(context);
        IngredienteDAO ingrediente = new IngredienteDAO(context);
    }

    public long inserirPrato(Prato prato) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        String nome = prato.getNome();
        values.put(ContratoPrato.PRATO_NOME, nome);
        long idItemPedido = prato.getIdItemPedido();
        values.put(ContratoPrato.PRATO_ID_ITEM_PEDIDO,idItemPedido);
        BigDecimal valor = prato.getValor();
        values.put(ContratoPrato.PRATO_VALOR, valor.toString());
        String descricao = prato.getDescricao();
        values.put(ContratoPrato.PRATO_DESCRICAO, descricao);
        String disponivel = prato.isDisponivel();
        values.put(ContratoPrato.PRATO_DISPONIVEL, disponivel);
        long idRestaurante = prato.getIdRestaurante();
        values.put(ContratoPrato.PRATO_RESTAURANTE_ID, idRestaurante);
        return bancoEscreve.insert(ContratoPrato.NOME_TABELA, null, values);
    }

    private Prato criarPrato(Cursor cursor) {
        String colunaId = ContratoPrato.PRATO_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        String colunaNome = ContratoPrato.PRATO_NOME;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);
        String colunaValor = ContratoPrato.PRATO_VALOR;
        int indexColunaValor = cursor.getColumnIndex(colunaValor);
        String valor = cursor.getString(indexColunaValor);
        BigDecimal valorDecimal = new BigDecimal(valor);
        String colunaDescricao = ContratoPrato.PRATO_DESCRICAO;
        int indexColunaDescricao = cursor.getColumnIndex(colunaDescricao);
        String descricao = cursor.getString(indexColunaDescricao);
        String colunaDisponivel = ContratoPrato.PRATO_DISPONIVEL;
        int indexColunaDisponivel = cursor.getColumnIndex(colunaDisponivel);
        String disponivelString = cursor.getString(indexColunaDisponivel);
        String colunaIdRestaurante = ContratoPrato.PRATO_RESTAURANTE_ID;
        int indexColunaIdRestaurante = cursor.getColumnIndex(colunaIdRestaurante);
        long idRestaurante = cursor.getLong(indexColunaIdRestaurante);
        String colunaIdItemPedido= ContratoPrato.PRATO_ID_ITEM_PEDIDO;
        int indexColunaIdItemPedido=cursor.getColumnIndex(colunaIdItemPedido);
        long idItemPedido=cursor.getLong(indexColunaIdItemPedido);
        Prato prato = new Prato();
        prato.setId(id);
        prato.setNome(nome);
        prato.setIdRestaurante(idRestaurante);
        prato.setValor(valorDecimal);
        prato.setDescricao(descricao);
        prato.setDisponivel(disponivelString);
        prato.setIdItemPedido(idItemPedido);
        return prato;
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



    public void desabilitarPrato(Prato prato) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoPrato.PRATO_DISPONIVEL, prato.isDisponivel());
        String[] args = {String.valueOf(prato.getId())};
        escritorBanco.update(ContratoPrato.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }

    public void updatePrato(Prato prato) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoPrato.PRATO_NOME, prato.getNome());
        values.put(ContratoPrato.PRATO_DESCRICAO, prato.getDescricao());
        values.put(ContratoPrato.PRATO_VALOR, prato.getValor().toString());
        values.put(ContratoPrato.PRATO_DISPONIVEL, prato.isDisponivel());
        String[] args = {String.valueOf(prato.getId())};
        escritorBanco.update(ContratoPrato.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }


    public Prato getPratoPorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_PRATO +
                " WHERE  idRestaurante = ?";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criar(query, args);
    }

    public Prato getPorId(long id) {
        String query = SELECT_FROM_PRATO +
                " WHERE  id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }


    public ArrayList<Prato> getPratosDisponiveisPorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_PRATO +
                " WHERE idRestaurante = ? " +
                " AND disponivel = " + StatusDisponibilidade.ATIVO.getDescricao() + ";";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criarListaPratos(query, args);
    }

    public ArrayList<Prato> getPratosIndisponiveisPorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_PRATO +
                " WHERE idRestaurante = ? " +
                " AND disponivel = " + StatusDisponibilidade.DESATIVADO.getDescricao() + ";";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criarListaPratos(query, args);
    }

    public ArrayList<Prato> getPratosEmFaltaPorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_PRATO +
                " WHERE idRestaurante = ? " +
                " AND disponivel = " + StatusDisponibilidade.EM_FALTA.getDescricao() + ";";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criarListaPratos(query, args);
    }


    public ArrayList<Prato> getPratosPorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_PRATO +
                " WHERE idRestaurante = ?";

        String[] args = {String.valueOf(idRestaurante)};
        return this.criarListaPratos(query, args);
    }

    public Prato getPratoPorNome(String nome, long idRestaurante) {
        String query = SELECT_FROM_PRATO +
                " WHERE  nome = ?" +
                " AND idRestaurante = ?";
        String[] args = {nome, String.valueOf(idRestaurante)};
        return this.criar(query, args);
    }






    public ArrayList<Prato> getPratosPorIdItemPedido(long idItemPedido) {
        String query = SELECT_FROM_PRATO +
                " WHERE idItemPedido = ? " +
                " AND disponivel = " + StatusDisponibilidade.ATIVO.getDescricao() + ";";
        String[] args = {String.valueOf(idItemPedido)};
        return this.criarListaPratos(query, args);
    }

    public ArrayList<Prato> criarListaPratos(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        ArrayList<Prato> listaPratos = new ArrayList();

        Prato prato = null;
        if (cursor.moveToFirst()) {
            do {
                prato = criarPrato(cursor);
                listaPratos.add(prato);

            } while (cursor.moveToNext());
        }
        cursor.close();
        leitorBanco.close();
        return listaPratos;
    }
}