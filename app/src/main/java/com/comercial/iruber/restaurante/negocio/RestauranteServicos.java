package com.comercial.iruber.restaurante.negocio;

import android.content.Context;

import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.RestauranteDAO;

import java.util.List;

public class RestauranteServicos {
    private RestauranteDAO restauranteDAO;

    public RestauranteServicos(Context context) {
        this.restauranteDAO = new RestauranteDAO(context);
    }

    private boolean restauranteCadastrado(String nome) {
        Restaurante restaurante = restauranteDAO.getRestaurantePorNome(nome);
        return restaurante != null;
    }

    public void registrarRestaurante(Restaurante restaurante) throws IruberException {
        if (restauranteCadastrado(restaurante.getNome())) {
            throw new IruberException("Restaurante já cadastrado");
        } else {
            restauranteDAO.inserirRestaurante(restaurante);
        }
    }

    public void updateRestaurante(Restaurante restaurante) throws IruberException {
        restauranteDAO.updateRestaurante(restaurante);
    }

    public List<Restaurante> listarRestaurantes() {
        return restauranteDAO.getListaRestaurante();
    }
}
