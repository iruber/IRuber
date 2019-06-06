package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.Ingrediente;
import com.comercial.iruber.restaurante.dominio.Prato;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PratoDAO {
    private DbHelper bancoDados;
    private IngredienteDAO ingrediente;

    public PratoDAO(Context context) {
        bancoDados = new DbHelper(context);
        ingrediente = new IngredienteDAO(context);
    }

    public long inserirPrato(Prato prato) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        String nome = prato.getNome();
        values.put(ContratoPrato.PRATO_NOME, nome);
        BigDecimal valor = prato.getValor();
        values.put(ContratoPrato.PRATO_VALOR, valor.toString());
        String descricao = prato.getDescricao();
        values.put(ContratoPrato.PRATO_DESCRICAO, descricao);
        boolean verDisponivel = prato.isDisponivel();
        String disponivel = this.checkDisponivelBolean(verDisponivel);
        values.put(ContratoPrato.PRATO_DISPONIVEL, disponivel);
        long idRestaurante = prato.getIdRestaurante();
        values.put(ContratoPrato.PRATO_RESTAURANTE_ID, idRestaurante);
        long id = bancoEscreve.insert(ContratoPrato.NOME_TABELA, null, values);
        return id;
    }

    public Prato criarPrato(Cursor cursor) {
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
        boolean disponivel = this.checkDisponivelString(disponivelString);
        String colunaIdRestaurante = ContratoPrato.PRATO_RESTAURANTE_ID;
        int indexColunaIdRestaurante = cursor.getColumnIndex(colunaIdRestaurante);
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
            return "true";
        } else {
            return "false";
        }
    }

    public Boolean checkDisponivelString(String dispoivel) {
        if (dispoivel.equals("1")) {
            return true;
        } else {
            return false;
        }
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
        String query = "SELECT ingrediente.nome" +
                "FROM prato_ingrediente" +
                "INNER JOIN ingrediente" +
                "ON ingrediente.idIngrediente = prato_ingrediente.idIngrediente" +
                "WHERE prato_ingrediente.idPrato = idprato;";
        String[] args = {String.valueOf(id)};
        return this.ingrediente.criar(query, args);
    }

    public void desabilitarPrato(Prato prato) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoPrato.PRATO_DISPONIVEL, "0");
        String[] args = {String.valueOf(prato.getIdProduto())};
        escritorBanco.update(ContratoPrato.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }
    public void updatePrato(Prato prato) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "idPrato = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoPrato.PRATO_NOME, prato.getNome());
        values.put(ContratoPrato.PRATO_DESCRICAO, prato.getDescricao());
        values.put(ContratoPrato.PRATO_VALOR, prato.getValor().toString());
        String[] args = {String.valueOf(prato.getIdProduto())};
        escritorBanco.update(ContratoPrato.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }


    public Prato getPratoPorIdRestaurante(long id) {
        String query = "SELECT * FROM prato " +
                "WHERE  idRestaurante = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }

    public Prato getById(long id) {
        String query = "SELECT * FROM prato " +
                "WHERE  id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }


    public ArrayList<Prato> getPratosAtivosPorIdRestaurante(long id) {
        String query = "SELECT * FROM prato " +
                "WHERE idRestaurante = ? " +
                "AND disponivel = 'true'";

        String[] args = {String.valueOf(id)};
        return this.criarMuitosPratos(query, args);
    }

    public Prato getByNome(String nome) {
        String query = "SELECT * FROM prato " +
                "WHERE nome = ?";
        String[] args = {nome};
        return this.criar(query, args);
    }

    public ArrayList<Prato> criarMuitosPratos(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        ArrayList<Prato> listaPratos= new ArrayList();

        Prato prato = null;
        if (cursor.moveToFirst()) {
            do{
                prato = criarPrato(cursor);
                listaPratos.add(prato);

            }while(cursor.moveToNext());
        }
        cursor.close();
        leitorBanco.close();
        return listaPratos;
    }
}