package com.comercial.iruber.restaurante.negocio;

import android.content.Context;

import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.PratoDAO;

import java.util.ArrayList;

public class PratoServicos {
    private PratoDAO pratoDAO;

    public PratoServicos(Context context) {
        this.pratoDAO = new PratoDAO(context);
    }

    public boolean pratoRegistrado(String nome, Restaurante restaurante) throws IruberException {
        Prato pratoBuscado = pratoDAO.getPratoPorNome(nome, restaurante.getIdRestaurante());
        return pratoBuscado != null;
    }

    public void registrarPrato(Prato prato, Restaurante restaurante) throws IruberException {
        if (pratoRegistrado(prato.getNome(), restaurante)) {
            throw new IruberException("Prato já cadastrado");
        } else {
            pratoDAO.inserirPrato(prato);
        }
    }

    public void updatePrato(Prato prato) throws IruberException {
        pratoDAO.updatePrato(prato);
    }

    public void desabilitarPrato(Prato prato, Restaurante restaurante) throws IruberException {
        if (pratoRegistrado(prato.getNome(), restaurante)) {
            pratoDAO.desabilitarPrato(prato);
        } else {
            throw new IruberException("Prato não cadastrado");
        }

    }

    public ArrayList<Prato> listarPratos(Restaurante restaurante) {
        return pratoDAO.getPratosPorIdRestaurante(restaurante.getIdRestaurante());
    }
}
