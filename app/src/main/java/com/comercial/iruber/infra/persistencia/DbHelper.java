package com.comercial.iruber.infra.persistencia;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.comercial.iruber.cliente.persistencia.ContratoCliente;
import com.comercial.iruber.restaurante.persistencia.ContratoIngrediente;
import com.comercial.iruber.restaurante.persistencia.ContratoPrato;
import com.comercial.iruber.restaurante.persistencia.ContratoRestaurante;
import com.comercial.iruber.usuario.persistencia.ContratoUsuario;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, Contrato.DATABASE_NAME, null, Contrato.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContratoUsuario.SQL_CREATE_TABLE_USUARIO);
        db.execSQL(ContratoCliente.SQL_CREATE_TABLE_CLIENTE);
        db.execSQL(ContratoRestaurante.SQL_CREATE_TABLE_RESTAURANTE);
        db.execSQL(ContratoIngrediente.SQL_CREATE_TABLE_INGREDIENTE);
        db.execSQL(ContratoPrato.SQL_CREATE_TABLE_PRATO);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ContratoUsuario.SQL_DELETE_USUARIOS);
        db.execSQL(ContratoCliente.SQL_DELETE_ADDRESS);
        db.execSQL(ContratoRestaurante.SQL_DELETE_RESTAURANTE);
        db.execSQL(ContratoIngrediente.SQL_DELETE_INGREDIENTE);
        db.execSQL(ContratoPrato.SQL_DELETE_PRATO);
        this.onCreate(db);
    }
}


