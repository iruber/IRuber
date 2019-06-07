package com.comercial.iruber.restaurante.gui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.infra.servicos.Validacao;
import com.comercial.iruber.restaurante.dominio.Ingrediente;
import com.comercial.iruber.restaurante.negocio.IngredienteServicos;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroIngredienteFragment extends Fragment {
    private Button novoIngrediente;
    private EditText nomeIngrediente;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_cadastro_ingrediente, container, false);
        nomeIngrediente = inflate.findViewById(R.id.novoIngredienteNome);
        novoIngrediente = inflate.findViewById(R.id.novoIngredienteButton);
        novoIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cadastrarIngrediente();
                } catch (IruberException e) {
                    e.printStackTrace();
                }
            }
        });
        return inflate;
    }

    private void cadastrarIngrediente() throws IruberException {
        if (!validarCampo()){
            return;
        }
        Ingrediente ingrediente =  new Ingrediente();
        ingrediente.setNome(nomeIngrediente.getText().toString());
        IngredienteServicos ingredienteNegocio = new IngredienteServicos(getContext());
        if(ingredienteNegocio.registrarIngrediente(ingrediente, Sessao.getSessaoRestaurante(getContext()))){
            getActivity().setTitle("Lista de Ingredientes");
            FragmentTransaction t = getFragmentManager().beginTransaction();
            Fragment mFrag = new ListaIngredienteFragment();
            t.replace(R.id.frameRestaurante, mFrag);
            t.commit();
        }
    }

    private boolean validarCampo() {
        Validacao validar = new Validacao();
        if (!validar.verificarCampoVazio(nomeIngrediente.getText().toString())){
            return false;
        }
        return true;
    }

}
