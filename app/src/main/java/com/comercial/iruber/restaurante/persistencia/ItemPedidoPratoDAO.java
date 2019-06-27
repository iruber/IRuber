package com.comercial.iruber.restaurante.persistencia;

import android.content.Context;

import com.comercial.iruber.infra.persistencia.DbHelper;

public class ItemPedidoPratoDAO {
    private DbHelper bancoDados;
    private Context contexto;
    public ItemPedidoPratoDAO(Context context){
        bancoDados = new DbHelper(context);
        contexto = context;

    }
}
