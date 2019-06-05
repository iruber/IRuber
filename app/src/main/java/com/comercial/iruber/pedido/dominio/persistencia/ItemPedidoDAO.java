package com.comercial.iruber.pedido.dominio.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.pedido.dominio.ItemPedido;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.PratoDAO;

import java.math.BigDecimal;


public class ItemPedidoDAO {

    private DbHelper bancoDados;
    private  PedidoDAO pedidoDAO;
    private  PratoDAO pratoDAO;
    public ItemPedidoDAO(Context context){

        bancoDados = new DbHelper(context);
        pedidoDAO = new PedidoDAO(context);
        pratoDAO =new PratoDAO(context);
    }


    public long inserirItemPedido(ItemPedido itemPedido){

        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();

        int quantidade= itemPedido.getQuantidade();
        BigDecimal valor = itemPedido.getValor();
        String valorItem= valor.toString();
        long idPedido = pedidoDAO.inserirPedido(itemPedido.getPedido());
        long idPrato= pratoDAO.inserirPrato(itemPedido.getPrato());

        values.put(ContratoItemPedido.ITEM_PEDIDO_ID_PRATO,idPrato);
        values.put(ContratoItemPedido.ITEM_PEDIDO_ID_PEDIDO,idPedido);
        values.put(ContratoItemPedido.ITEM_PEDIDO_VALOR,valorItem);
        values.put(ContratoItemPedido.ITEM_PEDIDO_QUANTIDADE,quantidade);

        long id = bancoEscreve.insert(ContratoItemPedido.NOME_TABELA,null,values);

        return id;
    }


    public ItemPedido  criarItemPedido(Cursor cursor){

        String id = ContratoItemPedido.ITEM_PEDIDO_ID;
        int colunaIndexId=  cursor.getColumnIndex(id);
        long idItemPedido = cursor.getLong(colunaIndexId);
        String idPedido = ContratoItemPedido.ITEM_PEDIDO_ID_PEDIDO;
        int colunaIdPedido=cursor.getColumnIndex(idPedido);
        long idPedidoLong = cursor.getLong(colunaIdPedido);
        String quantidade= ContratoItemPedido.ITEM_PEDIDO_QUANTIDADE;
        int colunaQuantidade= cursor.getColumnIndex(quantidade);
        int  quantidadeInt = cursor.getInt(colunaQuantidade);
        String valor = ContratoItemPedido.ITEM_PEDIDO_VALOR;
        int valorColunaIndex= cursor.getColumnIndex(valor);
        String valorString = cursor.getString(valorColunaIndex);
        BigDecimal valorBig= new BigDecimal(valorString);
        String idPrato = ContratoItemPedido.ITEM_PEDIDO_ID_PRATO;
        int colunaIdprato=cursor.getColumnIndex(idPrato);
        long idPratoLong = cursor.getLong(colunaIdprato);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(quantidadeInt);
        itemPedido.setValor(valorBig);
        itemPedido.setPrato(pratoDAO.getById(idPratoLong));
        return itemPedido;
    }

    private ItemPedido criar(String query, String[] args) {
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






}
