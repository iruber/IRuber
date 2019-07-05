package com.comercial.iruber.restaurante.negocio;

import android.content.Context;

import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.pedido.dominio.ItemPedido;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.IngredientePratoDAO;
import com.comercial.iruber.restaurante.persistencia.PratoDAO;

import java.util.ArrayList;
import java.util.List;

public class PratoServicos {
    private PratoDAO pratoDAO;
    private IngredientePratoDAO ingredientePratoDAO;

    public PratoServicos(Context context) {
        this.pratoDAO = new PratoDAO(context);
        this.ingredientePratoDAO=new IngredientePratoDAO(context);
    }

    public boolean pratoRegistrado(String nome, Restaurante restaurante) throws IruberException {
        Prato pratoBuscado = pratoDAO.getPratoPorNome(nome, restaurante.getIdRestaurante());
        return pratoBuscado != null;
    }

    public boolean registrarPrato(Prato prato, Restaurante restaurante) throws IruberException {
        if (pratoRegistrado(prato.getNome(), restaurante)) {
            throw new IruberException("Prato já cadastrado");
        } else {
            pratoDAO.inserirPrato(prato);
            return true;
        }
    }

    public void updatePrato(Prato prato)   {
        pratoDAO.updatePrato(prato);
    }

    public void desabilitarPrato(Prato prato, Restaurante restaurante) throws IruberException {
        if (pratoRegistrado(prato.getNome(), restaurante)) {
            pratoDAO.desabilitarPrato(prato);
        } else {
            throw new IruberException("Prato não cadastrado");
        }

    }

    public Prato getPratoPorId(long id){
        return pratoDAO.getPorId(id);
    }

    public  List<Prato> listaDePratosPorItem(ItemPedido itemPedido){
        return pratoDAO.getPratosPorIdItemPedido(itemPedido.getIdItemPedido());
    }
    public void inserirIdPratoIngrediente(long idprato,long idIngrediente){
        ingredientePratoDAO.inserirPratoIngrediente(idprato,idIngrediente);
    }


    public List<Prato> listarPratos(Restaurante restaurante) {
        return pratoDAO.getPratosPorIdRestaurante(restaurante.getIdRestaurante());
    }
}
