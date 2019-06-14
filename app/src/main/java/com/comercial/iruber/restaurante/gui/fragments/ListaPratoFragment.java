package com.comercial.iruber.restaurante.gui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.EnumFiltro;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.gui.PratosAdapter;
import com.comercial.iruber.restaurante.negocio.PratoServicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaPratoFragment extends Fragment {
    private List<Prato> pratos;
    private EditText buscaPrato;
    private EnumFiltro tipoFiltro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_lista_pratos, container, false);
        Bundle bundle = getArguments();
        if(bundle != null){
            tipoFiltro = (EnumFiltro) bundle.get("TipoFiltro");
        } else {
            tipoFiltro = EnumFiltro.SEM_FILTRO;
        }
        final RecyclerView rvPratos = (RecyclerView) inflate.findViewById(R.id.recyclerPrato);
        TextView filtrar = inflate.findViewById(R.id.filtrarPratos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        PratoServicos pratoServicos = new PratoServicos(getContext());
        pratos = ordenarLista((ArrayList<Prato>) pratoServicos.listarPratos(Sessao.getSessaoRestaurante(getContext())));
        rvPratos.setLayoutManager(linearLayoutManager);
        PratosAdapter adapter = new PratosAdapter(pratos);
        rvPratos.setAdapter(adapter);
        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("TipoFiltro", tipoFiltro);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                PratoFiltroFragment pratoFiltroFragment = new PratoFiltroFragment();
                pratoFiltroFragment.setArguments(bundle);
                transaction.replace(R.id.frameRestaurante, pratoFiltroFragment);
                transaction.commit();
            }
        });
        Button novoPrato = inflate.findViewById(R.id.novoPrato);
        novoPrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarPrato();
            }
        });
        buscaPrato = inflate.findViewById(R.id.buscaPrato);
        buscaPrato.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //precisa existir para utilização
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buscarIngredinte(rvPratos);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //precisa existir para utilização
            }
        });
        return inflate;
    }

    public void buscarIngredinte(RecyclerView rvPratos) {
        ArrayList<Prato> pratosad = new ArrayList<>();
        int indice = 0;
        for (int i = 0; i < pratos.size(); i++) {
            if (pratos.get(i).getNome().contains(buscaPrato.getText().toString())) {
                pratosad.add(indice, pratos.get(i));
                indice += 1;
            }
        }
        RecyclerView.Adapter adapter = new PratosAdapter(pratosad);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvPratos.setLayoutManager(llm);
        rvPratos.setAdapter(adapter);
    }

    public void criarPrato() {
        getActivity().setTitle("Novo Prato");
        FragmentTransaction t = getFragmentManager().beginTransaction();
        Fragment mFrag = new CadastroPratoFragment();
        t.replace(R.id.listaPratos, mFrag);
        t.commit();
    }

    private ArrayList<Prato> ordenarLista(ArrayList<Prato> pratos){
        PratoServicos pratoServicos = new PratoServicos(getContext());
        switch (tipoFiltro){
            case SEM_FILTRO:
                pratos = (ArrayList<Prato>) pratoServicos.listarPratos(Sessao.getSessaoRestaurante(getContext()));
                break;
            case NOME:
                pratos = ordenarListaPorNome((ArrayList<Prato>) pratoServicos.listarPratos(Sessao.getSessaoRestaurante(getContext())));
                break;
            case PRECO:
                pratos = ordenarListaPorPreco((ArrayList<Prato>) pratoServicos.listarPratos(Sessao.getSessaoRestaurante(getContext())));
                break;
            default:
                pratos = (ArrayList<Prato>) pratoServicos.listarPratos(Sessao.getSessaoRestaurante(getContext()));
                break;
        }
        return pratos;
    }

    private ArrayList<Prato> ordenarListaPorPreco(ArrayList<Prato> pratos) {
        ArrayList<Prato> result = new ArrayList<Prato>();
        ArrayList<Prato> lista = pratos;
        ArrayList<BigDecimal> precos = new ArrayList<BigDecimal>();

        for(Prato p: pratos){
            precos.add(p.getValor());
        }

        Collections.sort(precos);
        for(BigDecimal b : precos){
            for(Prato p : lista){
                if(b.equals(p.getValor())){
                    result.add(p);
                    p.setValor(new BigDecimal("-1"));
                }
            }
        }
        return result;
    }

    private ArrayList<Prato> ordenarListaPorNome(ArrayList<Prato> pratos) {
        ArrayList<Prato> result = new ArrayList<Prato>();
        ArrayList<Prato> lista = pratos;
        ArrayList<String> nomes = new ArrayList<String>();

        for(Prato p: pratos){
            nomes.add(p.getNome());
        }

        Collections.sort(nomes);
        for(String s : nomes){
            for(Prato p : lista){
                if(s.equals(p.getNome())){
                    result.add(p);
                    p.setNome("");
                }
            }
        }
        return result;
    }


}
