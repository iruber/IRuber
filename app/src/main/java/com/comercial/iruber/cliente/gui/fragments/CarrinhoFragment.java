package com.comercial.iruber.cliente.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.gui.CarrinhoAdapter;
import com.comercial.iruber.pedido.dominio.ItemPedido;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.restaurante.dominio.Prato;

import java.util.ArrayList;

public class CarrinhoFragment extends Fragment {
    private ArrayList<Prato> pratos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carrinho, container, false);
        Bundle bundle = getArguments();
        Pedido pedido = (Pedido) bundle.get("pedido");
        RecyclerView rvCarrinho = rootView.findViewById(R.id.rvPratosCarrinho);
        CarrinhoAdapter adapter = new CarrinhoAdapter(pratos);
        rvCarrinho.setAdapter(adapter);
        Button btnComprar = rootView.findViewById(R.id.btnComprarCarrinho);
        Button btnCancelar = rootView.findViewById(R.id.btnCancelarCarrinho);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rootView;
    }
}
