package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.EnumNota;
import com.comercial.iruber.restaurante.dominio.Nota;
import com.comercial.iruber.restaurante.dominio.Prato;

import java.util.ArrayList;
import java.util.List;


public class NotaDAO {
    private static final String SELECT_FROM_NOTA = "SELECT * FROM nota ";
    private DbHelper bancoDados;


    public  NotaDAO(Context context){
        bancoDados = new DbHelper(context);
    }


    public long inserirNota(Nota nota){
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        String valor = nota.getValor().getDescricao();
        values.put(ContratoNota.NOTA_VALOR,valor);
        long idCliente = nota.getIdCliente();
        values.put(ContratoNota.NOTA_ID_CLIENTE,idCliente);
        long idRestaurante = nota.getIdRestaurante();
        values.put(ContratoNota.NOTA_ID_RESTAURANTE,idRestaurante);
        return bancoEscreve.insert(ContratoNota.NOME_TABELA, null, values);
    }

    public Nota criarNota(Cursor cursor){
         String colunaId = ContratoNota.NOTA_ID;
         int indexColunaId=cursor.getColumnIndex(colunaId);
         long id = cursor.getLong(indexColunaId);
         String valorColuna = ContratoNota.NOTA_VALOR;
         int indexColunaValor=cursor.getColumnIndex(valorColuna);
         String valor=cursor.getString(indexColunaValor);
         String colunaIdCliente=ContratoNota.NOTA_ID_CLIENTE;
         int indexColunaIdCliente =cursor.getColumnIndex(colunaIdCliente);
         long idCliente= cursor.getLong(indexColunaIdCliente);
         String ColunaIdRestaurante=ContratoNota.NOTA_ID_RESTAURANTE;
         int indexColunaRestaurante=cursor.getColumnIndex(ColunaIdRestaurante);
         long idRestaurante =cursor.getLong(indexColunaRestaurante);

         Nota nota = new Nota();
         nota.setId(id);
         nota.setIdCliente(idCliente);
         nota.setIdRestaurante(idRestaurante);
         nota.setValor(EnumNota.valueOf(valor));
         return nota;


    }
    private Nota criar(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Nota nota = null;
        if (cursor.moveToNext()) {
            nota = criarNota(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return nota;
    }

    public Nota getNotaPorId(long id) {
        String query = SELECT_FROM_NOTA +
                " WHERE  id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }

    public List<Nota> getNotasPorIdRestaurante(long idRestaurante) {
        String query = SELECT_FROM_NOTA +
                " WHERE  idRestaurante = ?";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criarNotas(query, args);
    }

    public List<Nota> getTodasNotas() {
        String query = SELECT_FROM_NOTA;
        String[] args = {};
        return this.criarNotas(query, args);
    }


    public Nota getNotaPorIdCliente(long idCliente) {
        String query = SELECT_FROM_NOTA +
                " WHERE  idCliente = ?";
        String[] args = {String.valueOf(idCliente)};
        return this.criar(query, args);
    }



    public List<Nota> criarNotas(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        ArrayList<Nota> listaNotas = new ArrayList();

        Nota nota= null;
        if (cursor.moveToFirst()) {
            do {
                nota = criarNota(cursor);
                listaNotas.add(nota);

            } while (cursor.moveToNext());
        }
        cursor.close();
        leitorBanco.close();
        return listaNotas;
    }


}
