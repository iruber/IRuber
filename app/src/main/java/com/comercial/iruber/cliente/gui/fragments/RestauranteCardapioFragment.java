package com.comercial.iruber.cliente.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.gui.CardapioAdapter;
import com.comercial.iruber.pedido.dominio.ItemPedido;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.negocio.PratoServicos;

import java.util.ArrayList;

public class RestauranteCardapioFragment extends Fragment {
    private Restaurante restaurante;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cardapio_restaurante, container, false);
        pegarRestaurante();
        PratoServicos servicos = new PratoServicos(getContext());
        TextView tvNome = rootView.findViewById(R.id.nomeCR);
        tvNome.setText(restaurante.getNome());
        final ArrayList<Prato> pratos = (ArrayList<Prato>) servicos.listarPratos(restaurante);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView rvPratos = rootView.findViewById(R.id.listaPratosCR);
        rvPratos.setLayoutManager(linearLayoutManager);
        CardapioAdapter adapter = new CardapioAdapter(pratos, new CardapioAdapter.AdapterListeners() {
            @Override
            public void adicionarPrato(View v, int position) {
            }

            @Override
            public void verDetalhes(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("prato", pratos.get(position));
                bundle.putParcelable("restaurante", restaurante);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                PratoDetalhesFragment detalhesPrato = new PratoDetalhesFragment();
                detalhesPrato.setArguments(bundle);
                transaction.replace(R.id.frameCliente, detalhesPrato);
                transaction.commit();
            }
        });
        rvPratos.setAdapter(adapter);
        Button btnCarrinho = rootView.findViewById(R.id.btnCarrinhoCR);
        btnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                Bundle bundle = new Bundle();
                bundle.putSerializable("pedido",pedido);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                CarrinhoFragment carrinhoFragment = new CarrinhoFragment();
                carrinhoFragment.setArguments(bundle);
                transaction.replace(R.id.frameCliente, carrinhoFragment);
                transaction.commit();
                 **/
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void pegarRestaurante(){
        Bundle bundle = getArguments();
        this.restaurante = (Restaurante) bundle.getParcelable("restaurante");
    }
}
