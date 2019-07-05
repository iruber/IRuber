package com.comercial.iruber.cliente.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.negocio.ServicoPedido;
import com.comercial.iruber.restaurante.gui.PedidosAdapter;

import java.util.List;

public class PedidosClienteFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_pedido, container, false);
        RecyclerView rvPedidos = (RecyclerView) rootView.findViewById(R.id.recyclerViewPedidos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        Cliente cliente = Sessao.getSessaoCliente(getActivity());
        ServicoPedido servicoPedido = new ServicoPedido(getActivity());
        List<Pedido> pedidos = servicoPedido.listarPedidosC(cliente.getIdCliente());
        PedidosAdapter adapter = new PedidosAdapter(pedidos, new PedidosAdapter.AdapterListeners() {
            @Override
            public void verPratos(View v, int position) {
                Toast.makeText(getActivity(), "Pedido clickado", Toast.LENGTH_SHORT).show();
            }
        });
        rvPedidos.setLayoutManager(linearLayoutManager);
        rvPedidos.setAdapter(adapter);
        return rootView;
    }
}
