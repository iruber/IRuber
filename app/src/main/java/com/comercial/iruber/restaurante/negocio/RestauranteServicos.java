package com.comercial.iruber.restaurante.negocio;

import android.content.Context;


import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.persistencia.RestauranteDAO;
import com.comercial.iruber.usuario.dominio.Endereco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void updateRestaurante(Restaurante restaurante)  {
        restauranteDAO.updateRestaurante(restaurante);
    }

    public List<Restaurante> listarRestaurantes() {
        return restauranteDAO.getListaRestaurante();
    }

    public Map<String,String> enderecosRestaurante() {
        Map<String, String> example = new HashMap<>();
        List<Restaurante> restaurantes =this.listarRestaurantes();
        Endereco endereco;
        Restaurante restaurante;
        for(int i=0; i<restaurantes.size();i++){
            restaurante = restaurantes.get(i);
            endereco= restaurante.getEndereco();
           String ruaNumero= endereco.getRua()+" "+endereco.getNumero();
            example.put(restaurante.getNome(), (ruaNumero));
        }
        return example;
    }

    public Restaurante restaurantePorId(long id ){
        return restauranteDAO.getRestauranteById(id);
    }
}


