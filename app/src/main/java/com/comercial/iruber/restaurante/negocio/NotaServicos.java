package com.comercial.iruber.restaurante.negocio;

import android.content.Context;

import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.restaurante.dominio.Nota;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.NotaDAO;

import java.util.List;

public class NotaServicos {
    private NotaDAO notaDAO;

    public NotaServicos(Context context){
        this.notaDAO = new NotaDAO(context);
    }

    public List<Nota> listarNotas(Restaurante restaurante) throws IruberException {
        return notaDAO.getNotasPorIdRestaurante(restaurante.getIdRestaurante());
    }

    public void registrarNota(Nota nota, Restaurante restaurante) throws IruberException{
        notaDAO.inserirNota(nota);
    }
}
