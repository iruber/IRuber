package com.comercial.iruber.restaurante.gui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.gui.EntregadorAdapter;
import com.comercial.iruber.restaurante.negocio.EntregadorServicos;
import com.comercial.iruber.restaurante.negocio.RestauranteServicos;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EscolhaEntregadorPedidoFragment extends Fragment {
    public static String pedido = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_escolha_entregador_pedido, container, false);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerEscolhaEntregador);
        EntregadorServicos entregadorServicos = new EntregadorServicos(getContext());
        List<Entregador> entregadores = entregadorServicos.listarEntregadores(Sessao.getSessaoRestaurante(getContext()));
        EntregadorAdapter entregadorAdapter = new EntregadorAdapter(entregadores);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(entregadorAdapter);
        return inflate;
    }

}
