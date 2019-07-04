package com.comercial.iruber.pedido.dominio.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.pedido.dominio.ItemPedido;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.restaurante.dominio.Ingrediente;
import com.comercial.iruber.restaurante.persistencia.PratoDAO;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ItemPedidoDAO {
    private static final String SELECT_FROM_ITEM_PEDIDO = "SELECT * FROM itemPedido ";
    private DbHelper bancoDados;
    private PratoDAO pratoDAO;

    public ItemPedidoDAO(Context context) {
        bancoDados = new DbHelper(context);
        pratoDAO =new PratoDAO(context);


    }

    public long inserirItemPedido(ItemPedido itemPedido) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();

        BigDecimal valor = new BigDecimal("123");
        int quantidade=10;
        long idPrato= itemPedido.getPrato().getId();

        values.put(ContratoItemPedido.ITEM_PEDIDO_ID_PRATO,idPrato);
        values.put(ContratoItemPedido.ITEM_PEDIDO_VALOR,valor.toString());
        values.put(ContratoItemPedido.ITEM_PEDIDO_QUANTIDADE,quantidade);



        long id=bancoEscreve.insert(ContratoItemPedido.NOME_TABELA, null, values);
        bancoEscreve.close();
        return id;
    }

    private ItemPedido criarItemPedido(Cursor cursor)  {
        String colunaId= ContratoItemPedido.ITEM_PEDIDO_ID;
        int colunaIndexId=cursor.getColumnIndex(colunaId);
        long id =cursor.getLong(colunaIndexId);
       String colunaValor =ContratoItemPedido.ITEM_PEDIDO_VALOR;
       int colunaindexValor= cursor.getColumnIndex(colunaValor);
       String valorString= cursor.getString(colunaindexValor);
       BigDecimal valor = new BigDecimal(valorString);
       String colunaQuantidade = ContratoItemPedido.ITEM_PEDIDO_QUANTIDADE;
       int colunaIndexQuantidade=cursor.getColumnIndex(colunaQuantidade);
       int quantidade = cursor.getInt(colunaIndexQuantidade);
       String colunaIdPrato = ContratoItemPedido.ITEM_PEDIDO_ID_PRATO;
       int colunaIndexIdPrato = cursor.getColumnIndex(colunaIdPrato);
       long idPrato= cursor.getLong(colunaIndexIdPrato);
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setValor(valor);
        itemPedido.setQuantidade(quantidade);
        itemPedido.setPrato(pratoDAO.getPorId(idPrato));
        itemPedido.setIdItemPedido(id);


        return itemPedido;
    }


    private ItemPedido criar(String query, String[] args)   {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        ItemPedido itemPedido = null;
        if (cursor.moveToNext()) {
            itemPedido = criarItemPedido(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return itemPedido;
    }




    public ItemPedido getItemPedidoPorId(long id) {
        String query = SELECT_FROM_ITEM_PEDIDO +
                " WHERE id = ? ";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }
    public ItemPedido getItemPedidoPorIdPedido(long idPedido) {
        String query =   SELECT_FROM_ITEM_PEDIDO +
                " WHERE idPedido = ? ";
        String[] args = {String.valueOf(idPedido)};
        return this.criar(query, args);
    }

    public ItemPedido getPedidoPorIdPrato(long idPrato) {
        String query = SELECT_FROM_ITEM_PEDIDO +
                " WHERE idPrato = ? ";
        String[] args = {String.valueOf(idPrato)};
        return this.criar(query, args);
    }

    public List<ItemPedido>getItensPedidoPorIdPedido(long idPedido){
        String query = SELECT_FROM_ITEM_PEDIDO +
                " WHERE idPedido = ? ";
        String[] args = {String.valueOf(idPedido)};
        return this.criarListaItensPedido(query, args);
    }


    public List<ItemPedido> listaItensPedido(long idPedido){
        String query ="SELECT * FROM itemPedido IP INNER JOIN pedidoItemPedido PIP ON IP.id = PIP.idItemPedido " +
                " INNER JOIN pedido P ON P.id = PIP.idPedido " +
                " WHERE P.id= ?";

        String [] args = {String.valueOf(idPedido)};
        return this.criarListaItensPedido(query,args);

    }

    public void updateItemPedido(ItemPedido itemPedido) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = " id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoItemPedido.ITEM_PEDIDO_QUANTIDADE,itemPedido.getQuantidade());
        values.put(ContratoItemPedido.ITEM_PEDIDO_VALOR,itemPedido.getValor().toString());
        String[] args = {String.valueOf(itemPedido.getIdItemPedido())};
        escritorBanco.update(ContratoItemPedido.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }

    private List<ItemPedido> criarListaItensPedido(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        List<ItemPedido> listaItensPedidos = new ArrayList<>();
        ItemPedido itemPedido=null;
        if (cursor.moveToFirst()) {
            do {
                itemPedido = criarItemPedido(cursor);
                listaItensPedidos.add(itemPedido);
            } while (cursor.moveToNext());
        }
        cursor.close();
        leitorBanco.close();
        return listaItensPedidos;
    }

}
