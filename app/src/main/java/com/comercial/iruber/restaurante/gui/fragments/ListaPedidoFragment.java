package com.comercial.iruber.restaurante.gui.fragments;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.negocio.ServicoPedido;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.gui.PedidosAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaPedidoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_pedido, parent, false);
        RecyclerView rvPedidos = (RecyclerView) rootView.findViewById(R.id.recyclerViewPedidos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        Restaurante restaurante = Sessao.getSessaoRestaurante(getActivity());
        ServicoPedido servicoPedido = new ServicoPedido(getActivity());
        final List<Pedido> pedidos = servicoPedido.listarPedidosR(restaurante);
        rvPedidos.setLayoutManager(linearLayoutManager);
        PedidosAdapter adapter = new PedidosAdapter(pedidos, new PedidosAdapter.AdapterListeners() {
            @Override
            public void verPratos(View v, int position) {
                Sessao sessao = new Sessao();
                sessao.editSessaoPedido(pedidos.get(position), getActivity());
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ComfirmacaoPedidoFragment fragment = new ComfirmacaoPedidoFragment();
                transaction.replace(R.id.frameRestaurante, fragment);
                transaction.commit();
            }
        });
        rvPedidos.setAdapter(adapter);
        return rootView;
    }

}
