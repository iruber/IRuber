package com.comercial.iruber.restaurante.gui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
    private List<Entregador> entregadores;

    public ListaEntregadorFragment() {
        // Required empty public constructor
    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_entregador, container, false);
        EnumFiltro tipoFiltro;
        Bundle bundle;
        bundle = getArguments();
        if(bundle != null){
            tipoFiltro = (EnumFiltro) bundle.get("TipoFiltro");
        } else {
            tipoFiltro = EnumFiltro.SEM_FILTRO;
        }
        final RecyclerView rvEntregador = inflate.findViewById(R.id.recyclerEntregador);
        TextView filtrar = inflate.findViewById(R.id.filtrarPratos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        entregadores = listarEntregador();
        rvEntregador.setLayoutManager(linearLayoutManager);
        EntregadorAdapter adapter = new EntregadorAdapter(entregadores);
        rvEntregador.setAdapter(adapter);
        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Serializable tipoFiltro = null;
                bundle.putSerializable("TipoFiltro", tipoFiltro);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                PratoFiltroFragment pratoFiltroFragment = new PratoFiltroFragment();
                pratoFiltroFragment.setArguments(bundle);
                transaction.replace(R.id.frameRestaurante, pratoFiltroFragment);
                transaction.commit();
            }
        });
        Button novoEntregador = inflate.findViewById(R.id.novoPrato);
        novoEntregador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarEntregador();
            }
        });
        return inflate;
    }

    private List<Entregador> listarEntregador() {

        EntregadorServicos entregadorServicos = new EntregadorServicos(getContext());
        ArrayList<Entregador> entregadores = (ArrayList<Entregador>) entregadorServicos.listarEntregadores(Sessao.getSessaoRestaurante(getContext()));
        return entregadores;
    }


    public void criarEntregador() {
        getActivity().setTitle("Novo Entregador");
        FragmentTransaction t = getFragmentManager().beginTransaction();
        Fragment mFrag = new CadastroPratoFragment();
        t.replace(R.id.listaEntregadores, mFrag);
        t.commit();
    }

}
