package com.comercial.iruber.restaurante.gui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.comercial.iruber.R;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.dominio.Ingrediente;
import com.comercial.iruber.restaurante.gui.IngredientesAdapter;
import com.comercial.iruber.restaurante.negocio.IngredienteServicos;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaIngredienteFragment extends Fragment {
    private ArrayList<Ingrediente> ingredientes;
    private Button novoIngrediente;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_lista_ingrediente, container, false);
        final RecyclerView rvIngrediente = (RecyclerView) inflate.findViewById(R.id.recyclerIngrediente);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        IngredienteServicos ingredienteServicos = new IngredienteServicos(getContext());
        ingredientes = ingredienteServicos.listarIngredientes(Sessao.getSessaoRestaurante(getContext()));
        rvIngrediente.setLayoutManager(linearLayoutManager);
        IngredientesAdapter adapter = new IngredientesAdapter(ingredientes);
        rvIngrediente.setAdapter(adapter);
        novoIngrediente = inflate.findViewById(R.id.novoIngrediente);
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
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Ingrediente> ingredientesad = new ArrayList<Ingrediente>();
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

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return inflate;
    }

    private void criarIngrediente() {
        getActivity().setTitle("Novo Ingrediente");
        FragmentTransaction t = getFragmentManager().beginTransaction();
        Fragment mFrag = new CadastroIngredienteFragment();
        t.replace(R.id.frameRestaurante, mFrag);
        t.commit();
    }

}
