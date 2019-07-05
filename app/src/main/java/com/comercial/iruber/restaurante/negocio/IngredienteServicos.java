package com.comercial.iruber.restaurante.negocio;

import java.util.ArrayList;

import android.content.Context;

import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.pedido.dominio.StatusDisponibilidade;
import com.comercial.iruber.restaurante.dominio.Ingrediente;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.IngredienteDAO;

import java.util.List;

public class IngredienteServicos {
    private IngredienteDAO ingredienteDAO;

    public IngredienteServicos(Context context) {
        this.ingredienteDAO = new IngredienteDAO(context);
    }

    private boolean ingredienteRegistrado(String nome, Restaurante restaurante) {
        Ingrediente ingredienteBuscado = ingredienteDAO.getIngredientePorNome(nome, restaurante.getIdRestaurante());
        return ingredienteBuscado != null;
    }

    public boolean registrarIngrediente(Ingrediente ingrediente, Restaurante restaurante) throws IruberException {
        if (this.ingredienteRegistrado(ingrediente.getNome(), restaurante)) {
            throw new IruberException("Ingrediente já cadastrado");
        } else {
            ingredienteDAO.inserirIngrediente(ingrediente);
            return true;
        }
    }

    public void updateIngrediente(List<Ingrediente> ingrediente) throws IruberException {
        for (int i = 0; i < ingrediente.size(); i++) {
            ingredienteDAO.updateIngrediente(ingrediente.get(i));
        }
    }

    public void desabilitarIngrediente(Ingrediente ingrediente, Restaurante restaurante) throws IruberException {
        if (ingredienteRegistrado(ingrediente.getNome(), restaurante)) {
            ingrediente.setDisponivel(StatusDisponibilidade.DESATIVADO.getDescricao());
            ingredienteDAO.updateIngrediente(ingrediente);
        } else {
            throw new IruberException("Ingrediente não cadastrado");
        }
    }

    public ArrayList<Ingrediente> listarIngredientes(Restaurante restaurante) {
        return ingredienteDAO.getIngredientesPorIdRestaurante(restaurante.getIdRestaurante());
    }

}


