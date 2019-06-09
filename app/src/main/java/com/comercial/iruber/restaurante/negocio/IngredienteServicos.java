package com.comercial.iruber.restaurante.negocio;

import android.content.Context;

import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.restaurante.dominio.Ingrediente;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.IngredienteDAO;

import java.util.ArrayList;

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

    public void updateIngrediente(Ingrediente ingrediente) throws IruberException {
        ingredienteDAO.updateIngrediente(ingrediente);
    }

    public void desabilitarIngrediente(Ingrediente ingrediente, Restaurante restaurante) throws IruberException {
        if (ingredienteRegistrado(ingrediente.getNome(), restaurante)) {
            ingredienteDAO.desabilitarIngrediente(ingrediente);
        } else {
            throw new IruberException("Ingrediente não cadastrado");
        }
    }

    public ArrayList<Ingrediente> listarIngredientes(Restaurante restaurante) {
        return ingredienteDAO.getIngredientesPorIdRestaurante(restaurante.getIdRestaurante());
    }

}


