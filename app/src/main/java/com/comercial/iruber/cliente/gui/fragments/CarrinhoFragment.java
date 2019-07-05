package com.comercial.iruber.cliente.gui.fragments;

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
import android.widget.Toast;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.cliente.gui.CarrinhoAdapter;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.pedido.dominio.ItemPedido;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.StatusPedido;
import com.comercial.iruber.pedido.dominio.negocio.ServicoPedido;
import com.comercial.iruber.restaurante.dominio.Prato;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class CarrinhoFragment extends Fragment {
    private ArrayList<Prato> pratos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carrinho, container, false);
        Pedido pedido = Sessao.getSessaoPedido(getActivity());
        pratos = getPratosFomPedido(pedido);
        final Cliente cliente = Sessao.getSessaoCliente(getActivity());
        RecyclerView rvCarrinho = rootView.findViewById(R.id.rvPratosCarrinho);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvCarrinho.setLayoutManager(linearLayoutManager);
        CarrinhoAdapter adapter = new CarrinhoAdapter(pratos);
        rvCarrinho.setAdapter(adapter);
        Button btnComprar = rootView.findViewById(R.id.btnComprarCarrinho);
        Button btnCancelar = rootView.findViewById(R.id.btnCancelarCarrinho);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                Toast.makeText(getActivity(), "Compra realizada", Toast.LENGTH_SHORT).show();
                Sessao sessao = new Sessao();
                Date date = new Date();
                Pedido pedido2 = Sessao.getSessaoPedido(getActivity());
                ServicoPedido servicoPedido = new ServicoPedido(getActivity());
                pedido2.setCliente(cliente);
                pedido2.setData(date);
                pedido2.setStatusPedido(StatusPedido.EM_ESPERA);
                servicoPedido.registrarPedido(pedido2);
                sessao.editSessaoPedido(new Pedido(), getActivity());
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ListaRestauranteFragment listaRestauranteFragment = new ListaRestauranteFragment();
                transaction.replace(R.id.frameCliente, listaRestauranteFragment);
                transaction.commit();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Compras canceladas", Toast.LENGTH_SHORT).show();
                Sessao sessao = new Sessao();
                sessao.editSessaoPedido(new Pedido(), getActivity());
                FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ListaRestauranteFragment listaRestauranteFragment = new ListaRestauranteFragment();
                transaction.replace(R.id.frameCliente, listaRestauranteFragment);
                transaction.commit();
            }
        });
        return rootView;
    }

    private ArrayList<Prato> getPratosFomPedido(Pedido pedido) {
        ArrayList<Prato> result = new ArrayList<Prato>();
        for(ItemPedido itemPedido : pedido.getItemPedidos()){
            result.add(itemPedido.getPrato());
        }
        return result;
    }
}
