package com.comercial.iruber.infra.persistencia;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.comercial.iruber.infra.IruberApp;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appiruber";


    public DbHelper() {
        super(IruberApp.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario(" +
                "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "senha TEXT NOT NULL, " +
                "email TEXT NOT NULL); ");

        db.execSQL("CREATE TABLE pessoa (" +
                "idPessoa INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "idade TEXT NOT NULL," +
                "cpf TEXT NOT NULL);");


        db.execSQL("CREATE TABLE pedido(" +
                "idPedido INTEGER PRIMARY KEY AUTOINCREMENT," +
                "data Date NOT NULL, " +
                "valorTotal TEXT NOT NULL," +
                "statusPedido TEXT NOT NULL); ");


        db.execSQL("CREATE TABLE prato(" +
                "idPrato INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL, " +
                "descricao TEXT NOT NULL," +
                "disponivel TEXT NOT NULL," +
                "valor TEXT NOT NULL," +
                "ingredientes TEXT NOT NULL); ");

        db.execSQL("CREATE TABLE restaurante (" +
                "idRestaurante INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "cnpj TEXT NOT NULL," +
                "nota TEXT NOT NULL," +
                "idEntregador INTEGER NOT NULL); ");

        db.execSQL("CREATE TABLE entregador (" +
                "idEntregador INTEGER PRIMARY KEY AUTOINCREMENT," +
                "numeroEntregas TEXT NOT NULL); ");







    }









    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE usuario;");
        this.onCreate(db);
    }
}


