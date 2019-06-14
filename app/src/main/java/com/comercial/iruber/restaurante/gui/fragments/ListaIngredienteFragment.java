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
import com.comercial.iruber.pedido.dominio.StatusDisponibilidade;
import com.comercial.iruber.restaurante.dominio.Ingrediente;
import com.comercial.iruber.restaurante.gui.IngredientesAdapter;
import com.comercial.iruber.restaurante.negocio.IngredienteServicos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaIngredienteFragment extends Fragment {
    private List<Ingrediente> ingredientes;
    private EnumFiltro tipoFiltro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_lista_ingrediente, container, false);
        Bundle bundle = getArguments();
        if(bundle != null){
            tipoFiltro = (EnumFiltro) bundle.get("TipoFiltro");
        } else {
            tipoFiltro = EnumFiltro.SEM_FILTRO;
        }

        final RecyclerView rvIngrediente = (RecyclerView) inflate.findViewById(R.id.recyclerIngrediente);
        TextView filtrar = inflate.findViewById(R.id.filtrarIngredientes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        final IngredienteServicos ingredienteServicos = new IngredienteServicos(getContext());
        ingredientes = ordenarLista(ingredienteServicos.listarIngredientes(Sessao.getSessaoRestaurante(getContext())));
        rvIngrediente.setLayoutManager(linearLayoutManager);
        IngredientesAdapter adapter = new IngredientesAdapter(ingredientes);
        rvIngrediente.setAdapter(adapter);
        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("TipoFiltro", tipoFiltro);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                IngredientesFiltroFragment ingredientesFiltroFragment = new IngredientesFiltroFragment();
                ingredientesFiltroFragment.setArguments(bundle);
                transaction.replace(R.id.frameRestaurante, ingredientesFiltroFragment);
                transaction.commit();
            }
        });
        Button novoIngrediente = inflate.findViewById(R.id.novoIngrediente);
        novoIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarIngrediente();
            }
        });
        final EditText buscaIngrediente = inflate.findViewById(R.id.buscaIngrediente);
        buscaIngrediente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //precisa existir para utilização
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buscarIngrediente(buscaIngrediente, rvIngrediente);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //precisa existir para utilização
            }
        });
        return inflate;
    }

    public void buscarIngrediente(EditText buscaIngrediente, RecyclerView rvIngrediente) {
        ArrayList<Ingrediente> ingredientesad = new ArrayList<>();
        int indice = 0;
        for (int i = 0; i < ingredientes.size(); i++) {
            if (ingredientes.get(i).getNome().contains(buscaIngrediente.getText().toString())) {
                ingredientesad.add(indice, ingredientes.get(i));
                indice += 1;
            }
        }
        RecyclerView.Adapter adapter = new IngredientesAdapter(ingredientesad);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvIngrediente.setLayoutManager(llm);
        rvIngrediente.setAdapter(adapter);
    }

    private void criarIngrediente() {
        getActivity().setTitle("Novo Ingrediente");
        FragmentTransaction t = getFragmentManager().beginTransaction();
        Fragment mFrag = new CadastroIngredienteFragment();
        t.replace(R.id.frameRestaurante, mFrag);
        t.commit();
    }

    private ArrayList<Ingrediente> ordenarLista(ArrayList<Ingrediente> ingredientes){
        IngredienteServicos ingredienteServicos = new IngredienteServicos(getContext());
        switch (tipoFiltro){
            case SEM_FILTRO:
                ingredientes = (ArrayList<Ingrediente>) ingredienteServicos.listarIngredientes(Sessao.getSessaoRestaurante(getContext()));
                break;
            case NOME:
                ingredientes = ordenarListaPorNome((ArrayList<Ingrediente>) ingredienteServicos.listarIngredientes(Sessao.getSessaoRestaurante(getContext())));
                break;
            case ATIVADO:
                ingredientes = exibirAtivados((ArrayList<Ingrediente>) ingredienteServicos.listarIngredientes(Sessao.getSessaoRestaurante(getContext())));
                break;
            case DESATIVADO:
                ingredientes = exibirDesativados((ArrayList<Ingrediente>) ingredienteServicos.listarIngredientes(Sessao.getSessaoRestaurante(getContext())));
                break;
            case EM_FALTA:
                ingredientes = exibirEmFalta((ArrayList<Ingrediente>) ingredienteServicos.listarIngredientes(Sessao.getSessaoRestaurante(getContext())));
                break;
            default:
                ingredientes = (ArrayList<Ingrediente>) ingredienteServicos.listarIngredientes(Sessao.getSessaoRestaurante(getContext()));
                break;
        }
        return ingredientes;
    }

    private ArrayList<Ingrediente> ordenarListaPorNome(ArrayList<Ingrediente> ingredientes) {
        ArrayList<Ingrediente> result = new ArrayList<Ingrediente>();
        ArrayList<Ingrediente> lista = ingredientes;
        List<String> nomes = new ArrayList<String>();

        for(Ingrediente i : ingredientes){
            nomes.add(i.getNome());
        }

        Collections.sort(nomes);
        for(String s : nomes){
            for(Ingrediente i : lista){
                if(s.equals(i.getNome())){
                    result.add(i);
                    i.setNome("");
                }
            }
        }
        return result;
    }

    private ArrayList<Ingrediente> exibirAtivados(ArrayList<Ingrediente> ingredientes) {
        ArrayList<Ingrediente> result = new ArrayList<Ingrediente>();
        for(Ingrediente i : ingredientes){
            if(i.getDisponivel() == StatusDisponibilidade.ATIVO){
                result.add(i);
            }
        }
        return result;
    }

    private ArrayList<Ingrediente> exibirDesativados(ArrayList<Ingrediente> ingredientes) {
        ArrayList<Ingrediente> result = new ArrayList<Ingrediente>();
        for(Ingrediente i : ingredientes){
            if(i.getDisponivel() == StatusDisponibilidade.DESATIVADO){
                result.add(i);
            }
        }
        return result;
    }

    private ArrayList<Ingrediente> exibirEmFalta(ArrayList<Ingrediente> ingredientes){
        ArrayList<Ingrediente> result = new ArrayList<Ingrediente>();
        for(Ingrediente i : ingredientes){
            if(i.getDisponivel() == StatusDisponibilidade.EM_FALTA){
                result.add(i);
            }
        }
        return result;
    }

}
