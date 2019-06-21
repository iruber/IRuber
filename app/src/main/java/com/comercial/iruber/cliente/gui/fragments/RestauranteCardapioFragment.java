package com.comercial.iruber.cliente.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.gui.CardapioAdapter;
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
        final ArrayList<Prato> pratos = (ArrayList<Prato>) servicos.listarPratos(restaurante);
        RecyclerView rvPratos = rootView.findViewById(R.id.listaPratosCR);
        CardapioAdapter adapter = new CardapioAdapter(pratos, new CardapioAdapter.AdapterListeners() {
            @Override
            public void adicionarPrato(View v, int position) {
                //Aqui coloca-se o comportamento que se deseja ao adicionar um item ao carrinho.
            }

            @Override
            public void verDetalhes(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("prato", pratos.get(position));
                bundle.putSerializable("restaurante", restaurante);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                PratoDetalhesFragment detalhesPrato = new PratoDetalhesFragment();
                detalhesPrato.setArguments(bundle);
                transaction.replace(R.id.frameCliente, detalhesPrato);
                transaction.commit();
            }
        });
        rvPratos.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void pegarRestaurante(){
        Bundle bundle = getArguments();
        this.restaurante = (Restaurante) bundle.get("restaurante");
    }
}
