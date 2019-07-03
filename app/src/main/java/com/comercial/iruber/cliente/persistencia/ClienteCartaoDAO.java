package com.comercial.iruber.cliente.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.comercial.iruber.infra.persistencia.DbHelper;

public class ClienteCartaoDAO {
    private DbHelper bancoDados;
    public ClienteCartaoDAO(Context context){
        bancoDados= new DbHelper(context);
    }

    public long inserirCartao(long idCliente,long idCartao){
        SQLiteDatabase escreverBanco=bancoDados.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ContratoClienteCartao.CLIENTE_CARTAO_CLIENTE_ID,idCliente);
        contentValues.put(ContratoClienteCartao.CLIENTE_CARTAO_ID_CARTAO,idCartao);
        long id =escreverBanco.insert(ContratoClienteCartao.NOME_TABELA,null,contentValues);
        escreverBanco.close();
        return id;

    }
}
