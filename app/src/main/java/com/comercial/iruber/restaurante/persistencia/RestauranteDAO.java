package com.comercial.iruber.restaurante.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.restaurante.dominio.Empresa;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.EmpresaDAO;

public class RestauranteDAO {
    private DbHelper bancoDados;
    private EmpresaDAO empresaDAO;




    String tabela = DbHelper.TABELA_RESTAURANTE;
    String colunaIdEmpresa = DbHelper.RESTAURANTE_ID_EMPRESA;


    public RestauranteDAO() {
        bancoDados = new DbHelper();
        empresaDAO = new EmpresaDAO();
    }

    public long inserirRestaurante(Restaurante restaurante){
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();


        long idEmpresa = restaurante.getEmpresa().getId();
        values.put(colunaIdEmpresa,idEmpresa);


        long id = bancoEscreve.insert(tabela, null, values);
        bancoEscreve.close();
        return id;
    }


    public Restaurante criarRestaurante(Cursor cursor){
        String colunaId = DbHelper.RESTAURANTE_ID;
        int indexColunaId= cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);

        String colunaEmpresaId = DbHelper.RESTAURANTE_ID_EMPRESA;
        int indexColunaIdEmpresa = cursor.getColumnIndex(colunaEmpresaId);
        String idEmpresa = cursor.getString(indexColunaIdEmpresa);




        Restaurante restaurante = new Restaurante();
        restaurante.setIdRestaurante(id);
        restaurante.setEmpresa(empresaDAO.getByID(idEmpresa));



        return restaurante;
    }

}
