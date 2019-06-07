package com.comercial.iruber.restaurante.negocio;

import android.content.Context;

import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.EntregadorDAO;

public class EntregadorServicos {
    private EntregadorDAO entregadorDao;

    public EntregadorServicos(Context context) {
        this.entregadorDao = new EntregadorDAO(context);
    }

    public boolean entregadorRegistrado(String nome, Restaurante restaurante) {
        Entregador entregadorBuscado = entregadorDao.getEntregadorPorNome(nome, restaurante.getIdRestaurante());
        return entregadorBuscado != null;
    }

    public void registrarEntregador(Entregador entregador, Restaurante restaurante) throws IruberException {
        if (entregadorRegistrado(entregador.getNome(), restaurante)) {
            throw new IruberException("Entregador já cadastrado");
        } else {
            entregadorDao.inserirEntregador(entregador);
        }
    }

    public void updateEntregador(Entregador entregador) throws IruberException {
        entregadorDao.updateEntregador(entregador);
    }

    public void desabilitarEntregador(Entregador entregador, Restaurante restaurante) throws IruberException {
        if (entregadorRegistrado(entregador.getNome(), restaurante)) {
            entregadorDao.desabilitarEntregador(entregador);
        } else {
            throw new IruberException("Entregador não cadastrado");
        }

    }
}
