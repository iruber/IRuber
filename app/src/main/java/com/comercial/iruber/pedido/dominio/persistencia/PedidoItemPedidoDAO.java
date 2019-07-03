package com.comercial.iruber.pedido.dominio.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.pedido.dominio.Pedido;

public class PedidoItemPedidoDAO {
    private DbHelper bancoDados;

    public PedidoItemPedidoDAO(Context context) {
        bancoDados = new DbHelper(context);
    }

    public long inserirPedidoItemPedido(long idPedido, long idItemPedido) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ContratoPedidoItemPedido.PEDIDO_ITEM_PEDIDO_ID_PEDIDO, idPedido);
        values.put(ContratoPedidoItemPedido.PEDIDO_ITEM_PEDIDO_ID_ITEM_PEDIDO, idItemPedido);


        long id = bancoEscreve.insert(ContratoPedidoItemPedido.NOME_TABELA, null, values);
        bancoEscreve.close();
        return id;
    }
}