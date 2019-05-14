package com.comercial.iruber.cliente.persistencia;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.persistencia.DbHelper;
public class ClienteDAO {

    private DbHelper bancoDados;
    private SQLiteDatabase db;



    String tabela = DbHelper.TABELA_CLIENTE;
    String colunaIdPessoa = DbHelper.CLIENTE_ID_PESSOA;

    public ClienteDAO(){
        bancoDados = new DbHelper();

    }



    public long inserirPessoa(Cliente cliente) {

        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();


        long idPessoa = cliente.getPessoa().getIdPessoa();
        values.put(colunaIdPessoa, idPessoa);

        long id = db.insert(tabela, null, values);
        db.close();
        return id;

    }
}
