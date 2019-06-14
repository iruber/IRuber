package com.comercial.iruber.cliente.gui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.gui.RestaurantesAdapter;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.negocio.RestauranteServicos;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaRestauranteFragment extends Fragment {
    private List<Restaurante> restaurantes;
    private EditText buscaRestaurante;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_lista_restaurante, container, false);
        final RecyclerView rvRestaurante = (RecyclerView) inflate.findViewById(R.id.recyclerRestaurante);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        RestauranteServicos restauranteServicos = new RestauranteServicos(getContext());
//        restaurantes = ? get lista restaurantes
        rvRestaurante.setLayoutManager(linearLayoutManager);
        RestaurantesAdapter adapter = new RestaurantesAdapter(restaurantes);
        rvRestaurante.setAdapter(adapter);
        buscaRestaurante = inflate.findViewById(R.id.buscaRestaurante);
        buscaRestaurante.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //precisa existir para utilização
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buscarRestaurante(rvRestaurante);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //precisa existir para utilização
            }
        });
        return inflate;
    }

    private void buscarRestaurante(RecyclerView rvRestaurantes){
        ArrayList<Restaurante> restaurantesad = new ArrayList<>();
        int indice = 0;
        for (int i = 0; i < restaurantes.size(); i++) {
            if (restaurantes.get(i).getNome().contains(buscaRestaurante.getText().toString())) {
                restaurantesad.add(indice, restaurantes.get(i));
                indice += 1;
            }
        }
        RecyclerView.Adapter adapter = new RestaurantesAdapter(restaurantesad);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvRestaurantes.setLayoutManager(llm);
        rvRestaurantes.setAdapter(adapter);
    }
}
