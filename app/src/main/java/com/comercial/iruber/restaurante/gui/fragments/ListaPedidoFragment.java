package com.comercial.iruber.restaurante.gui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.persistencia.PedidoDAO;
import com.comercial.iruber.restaurante.gui.PedidosAdapter;

import java.util.ArrayList;

public class ListaPedidoFragment extends Fragment {

    private ArrayList<Pedido> pedidos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_pedido, parent, false);
        RecyclerView rvPedidos = (RecyclerView) rootView.findViewById(R.id.recyclerViewPedidos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        pedidos = new ArrayList<Pedido>();
        rvPedidos.setLayoutManager(linearLayoutManager);
        PedidosAdapter adapter = new PedidosAdapter(pedidos);
        rvPedidos.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }


}
