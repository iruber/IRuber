package com.comercial.iruber.restaurante.gui.fragments;

import android.content.Context;
import android.net.Uri;
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
import com.comercial.iruber.infra.EnumFiltro;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.gui.EntregadorAdapter;
import com.comercial.iruber.restaurante.negocio.EntregadorServicos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaEntregadorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_entregador, container, false);
        RecyclerView rvEntregadores = rootView.findViewById(R.id.recyclerEntregador);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        List<Entregador> lista = listarEntregadores();
        EntregadorAdapter adapter = new EntregadorAdapter(lista);
        rvEntregadores.setLayoutManager(linearLayoutManager);
        rvEntregadores.setAdapter(adapter);
        Button btnCadastrar = rootView.findViewById(R.id.BtnNovoEntregador);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarEntregador();
            }
        });
        return rootView;
    }

    private List<Entregador> listarEntregadores() {

        EntregadorServicos entregadorServicos = new EntregadorServicos(getContext());
        ArrayList<Entregador> entregadores = (ArrayList<Entregador>) entregadorServicos.
                listarEntregadores(Sessao.getSessaoRestaurante(getContext()));
        return entregadores;
    }

    public void criarEntregador() {
        getActivity().setTitle("Novo Entregador");
        FragmentManager manager = getFragmentManager();
        FragmentTransaction t = manager.beginTransaction();
        Fragment mFrag = new CadastroEntregadorFragment();
        t.replace(R.id.frameRestaurante, mFrag);
        t.commit();
    }

}
