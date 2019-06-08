package com.comercial.iruber.restaurante.gui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.comercial.iruber.R;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.restaurante.gui.PedidosAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_pedido, parent, false);
        RecyclerView rvPedidos = (RecyclerView) rootView.findViewById(R.id.recyclerViewPedidos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        List<Pedido> pedidos = new ArrayList<>();
        rvPedidos.setLayoutManager(linearLayoutManager);
        PedidosAdapter adapter = new PedidosAdapter(pedidos);
        rvPedidos.setAdapter(adapter);
        return rootView;
    }

}
