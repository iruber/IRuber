package com.comercial.iruber.cliente.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.cliente.dominio.Cartao;
import com.comercial.iruber.infra.persistencia.DbHelper;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CartaoDAO {
    private DbHelper bancodeDados;


    public CartaoDAO(Context context){
        bancodeDados = new DbHelper(context);
    }


    public long inserirCartao(Cartao cartao){

        SQLiteDatabase bancoEscreve=bancodeDados.getWritableDatabase();
        ContentValues values = new ContentValues();

        String nome = cartao.getNome();
        String numero=cartao.getNumero();
        int codigo = cartao.getCodSeguranca();
        Date dataVencimento =cartao.getDataVencimento();

        values.put(ContratoCartao.CARTAO_CODIGO,codigo);
        values.put(ContratoCartao.CARTAO_DATA_VENCIMENTO,dataVencimento.toString());
        values.put(ContratoCartao.CARTAO_NOME,nome);
        values.put(ContratoCartao.CARTAO_NUMERO,numero);
        values.put(ContratoCartao.CARTAO_CODIGO,codigo);

       long id= bancoEscreve.insert(ContratoCartao.NOME_TABELA,null,values);
        return id;

    }

    public Cartao criarCartao(Cursor cursor){
        String colunaId = ContratoCartao.CARTAO_ID;
        int colunaIndexId= cursor.getColumnIndex(colunaId);
        long id= cursor.getLong(colunaIndexId);
        String colunaCodigo=ContratoCartao.CARTAO_CODIGO;
        int colunaIndexCodigo=cursor.getColumnIndex(colunaCodigo);
        int codigo =cursor.getInt(colunaIndexCodigo);
        String colunaNome = ContratoCartao.CARTAO_NOME;
        int colunaIndexNome=cursor.getColumnIndex(colunaNome);
        String nome =cursor.getString(colunaIndexNome);
        String colunaNumero=ContratoCartao.CARTAO_NUMERO;
        int colunaNumeroIndex=cursor.getColumnIndex(colunaNumero);
        String numero = cursor.getString(colunaNumeroIndex);
        String colunaData = ContratoCartao.CARTAO_DATA_VENCIMENTO;
        int colunaIndexData=cursor.getColumnIndex(colunaData);
        String vencimento =cursor.getString(colunaIndexData);
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA).parse(vencimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Cartao cartao = new Cartao();
        cartao.setIdCartao(id);
        cartao.setNome(nome);
        cartao.setCodSeguranca(codigo);
        cartao.setDataVencimento(date);
        cartao.setNumero(numero);
        return cartao;
    }

    private Cartao criar(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancodeDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Cartao cartao = null;
        if (cursor.moveToNext()) {
            cartao = criarCartao(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return cartao;
    }

    public List<Cartao> listadeCartoes(long idCliente) {
        String query = "SELECT C.codigo from cartao C INNER JOIN clienteCartao  CC ON " +
                " C.id = CC.idCartao " +
                " INNER JOIN cliente CLI ON CLI.id = CC.idCliente " +
                " WHERE CLI.id = ? ";

        String[] args = {String.valueOf(idCliente)};
        return this.criarListaDeCartoes(query, args);

    }
    public List<Cartao> criarListaDeCartoes(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancodeDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        List<Cartao> listaDeCartoes= new ArrayList<>();
        Cartao cartao=null;
        if (cursor.moveToFirst()) {
            do {
                cartao = criarCartao(cursor);
                listaDeCartoes.add(cartao);
            } while (cursor.moveToNext());
        }
        cursor.close();
        leitorBanco.close();
        return listaDeCartoes;
    }
}
