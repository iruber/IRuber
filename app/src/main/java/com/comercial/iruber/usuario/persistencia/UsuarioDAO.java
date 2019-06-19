package com.comercial.iruber.usuario.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.comercial.iruber.cliente.persistencia.ContratoCliente;
import com.comercial.iruber.infra.EnumTipo;
import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.infra.persistencia.DbHelper;

import java.io.ByteArrayOutputStream;

public class UsuarioDAO {
    public static final String SELECT_FROM_USUARIO = "SELECT * FROM usuario ";
    private DbHelper bancoDados;
    Bitmap yourBitmap;
    public UsuarioDAO(Context context) {
        bancoDados = new DbHelper(context);
    }

    public long inserirUsuario(Usuario usuario) {
        SQLiteDatabase db = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        String email = usuario.getEmail();
        values.put(ContratoUsuario.USUARIO_EMAIL, email);
        String senha = usuario.getSenha();
        values.put(ContratoUsuario.USUARIO_SENHA, senha);
        EnumTipo tipo = usuario.getTipo();
        values.put(ContratoUsuario.USUARIO_TIPO, tipo.toString());
        long id = db.insert(ContratoUsuario.NOME_TABELA, null, values);
        db.close();
        return id;
    }

    public Usuario criarUsuario(Cursor cursor) {
        EnumTipo enumTipo;
        String colunaId = ContratoUsuario.USUARIO_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        String colunaEmail = ContratoUsuario.USUARIO_EMAIL;
        int indexColunaEmail = cursor.getColumnIndex(colunaEmail);
        String email = cursor.getString(indexColunaEmail);
        String colunaSenha = ContratoUsuario.USUARIO_SENHA;
        int indexColunaSenha = cursor.getColumnIndex(colunaSenha);
        String senha = cursor.getString(indexColunaSenha);
        String img = ContratoUsuario.USUARIO_IMAGEM;
        int imgColunaIndex = cursor.getColumnIndex(img);
        byte[] byteArray = cursor.getBlob(imgColunaIndex);
        Bitmap imgBitMap = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
        String colunaTipo = ContratoUsuario.USUARIO_TIPO;
        int indexColunaTipo = cursor.getColumnIndex(colunaTipo);
        String tipo = cursor.getString(indexColunaTipo);
        Log.d("logar", "criarUsuario: "+ tipo);
        if (tipo.equals("cliente")) {
            enumTipo = EnumTipo.CLIENTE;
        } else {
            enumTipo = EnumTipo.RESTAURANTE;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setTipo(enumTipo);
        usuario.setImagem(imgBitMap);
        return usuario;
    }

    public Usuario logarUsuario(String email, String senha, String tipo) {
        String query = SELECT_FROM_USUARIO +
                "WHERE email = ? AND senha = ? AND tipo = ?";
        String[] args = {email, senha, tipo};
        return this.criar(query, args);
    }

    private Usuario criar(String query, String[] args) {

        SQLiteDatabase db = bancoDados.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, args);
        Usuario usuario = null;
        if (cursor.moveToFirst()) {
            usuario = this.criarUsuario(cursor);
        }
        cursor.close();
        db.close();
        return usuario;
    }

    public Usuario getById(long id) {
        String query = SELECT_FROM_USUARIO +
                "WHERE usuarioId = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }

    public Usuario getByEmail(String email) {
        String query = SELECT_FROM_USUARIO +
                "WHERE email = ?";
        String[] args = {email};
        return this.criar(query, args);
    }

    private  byte[] getBytesFromBitmap(Bitmap bitmap) {
        if (bitmap!=null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
            return stream.toByteArray();
        }
        return null;
    }

    public void updateSenhaUsuario(Usuario usuario) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        byte [] bArray=this.getBytesFromBitmap(usuario.getImagem());
        values.put(ContratoUsuario.USUARIO_IMAGEM,bArray);
        values.put(ContratoUsuario.USUARIO_SENHA, usuario.getSenha());
        String[] args = {String.valueOf(usuario.getId())};
        escritorBanco.update(ContratoUsuario.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }
}
