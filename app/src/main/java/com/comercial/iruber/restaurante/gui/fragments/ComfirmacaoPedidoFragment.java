package com.comercial.iruber.restaurante.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.gui.fragments.ListaRestauranteFragment;
import com.comercial.iruber.infra.EnumTipo;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.pedido.dominio.ItemPedido;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.StatusPedido;
import com.comercial.iruber.pedido.dominio.negocio.ServicoPedido;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.gui.PratosAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ComfirmacaoPedidoFragment extends Fragment {
    private Pedido pedido;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comfirmacao_pedido, container, false);
        RecyclerView rvPratos = rootView.findViewById(R.id.recyclerPratoCP);
        pedido = Sessao.getSessaoPedido(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvPratos.setLayoutManager(linearLayoutManager);
        List<Prato> pratos = extrairPratosDeItensPedido(pedido);
        PratosAdapter adapter = new PratosAdapter(pratos);
        rvPratos.setAdapter(adapter);
        LinearLayout confirmarLayout = rootView.findViewById(R.id.confirmacaoLayout);
        LinearLayout entregadorBotao = rootView.findViewById(R.id.entregadorBotao);
        LinearLayout entregadorConfirmar = rootView.findViewById(R.id.entregadorConfirmarLayout);
        if (Sessao.getSessao(getContext()).getTipo().getDescricao().equals(EnumTipo.ENTREGADOR)){
            confirmarLayout.setVisibility(View.GONE);
            entregadorBotao.setVisibility(View.GONE);
            entregadorConfirmar.setVisibility(View.VISIBLE);
        }
        if (!pedido.getStatusPedido().getDescricao().equals(StatusPedido.EM_ESPERA.getDescricao())){
            confirmarLayout.setVisibility(View.GONE);
        }
        if(pedido.getStatusPedido().getDescricao().equals(StatusPedido.EM_PREPARO.getDescricao())){
            if (pedido.getIdentregador() == 0){
                entregadorBotao.setVisibility(View.VISIBLE);
            }else {
                entregadorBotao.setVisibility(View.GONE);
            }
        }
        Button escolherEntregador = rootView.findViewById(R.id.escolhaEntregador);
        escolherEntregador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                EscolhaEntregadorPedidoFragment fragment = new EscolhaEntregadorPedidoFragment();
                EscolhaEntregadorPedidoFragment.pedido = "1";
                transaction.replace(R.id.frameRestaurante, fragment);
                transaction.commit();
            }
        });
        Button btnConfirmar = rootView.findViewById(R.id.confirmarPedido);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trasicaoTela(pedido, StatusPedido.EM_PREPARO);
            }
        });
        Button btnRejeitar = rootView.findViewById(R.id.rejeitarPedido);
        btnRejeitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trasicaoTela(pedido, StatusPedido.RECUSADO);
            }
        });
        return rootView;
    }

    private List<Prato> extrairPratosDeItensPedido(Pedido pedido){
        List<Prato> result = new ArrayList<>();
        for(ItemPedido item : pedido.getItemPedidos()){
            result.add(item.getPrato());
        }
        return result;
    }

    private void trasicaoTela(Pedido pedido, StatusPedido statusPedido){
        pedido.setStatusPedido(statusPedido);
        ServicoPedido servicoPedido = new ServicoPedido(getActivity());
        servicoPedido.updatePedido(pedido);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ListaPedidoFragment fragment = new ListaPedidoFragment();
        transaction.replace(R.id.frameRestaurante, fragment);
        transaction.commit();
    }
}
