package com.comercial.iruber.restaurante.gui.fragments;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.infra.servicos.Validacao;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.negocio.PratoServicos;

import java.math.BigDecimal;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroPratoFragment extends Fragment {
    private EditText nomePrato, descricaoPrato, valorPrato;
    private Button criarPrato;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_cadastro_prato, container, false);
        criarPrato = inflate.findViewById(R.id.criarPrato);
        criarPrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cadastrarPrato();
                } catch (IruberException e) {
                    e.printStackTrace();
                }
            }
        });
        nomePrato = inflate.findViewById(R.id.novoPratoNome);
        descricaoPrato = inflate.findViewById(R.id.novoPratoDescricao);
        valorPrato = inflate.findViewById(R.id.novoPratoValor);
        return inflate;
    }

    private void cadastrarPrato() throws IruberException {
        if (!validarCampos()){
            return;
        }
        Prato prato = new Prato();
        prato.setNome(nomePrato.getText().toString());
        prato.setDescricao(descricaoPrato.getText().toString());
        prato.setDisponivel(true);
        prato.setIdRestaurante(Sessao.getSessaoRestaurante(getContext()).getIdRestaurante());
        BigDecimal valor = new BigDecimal(valorPrato.getText().toString());
        prato.setValor(valor);
        PratoServicos pratoNegocio = new PratoServicos(getContext());
        if(pratoNegocio.registrarPrato(prato, Sessao.getSessaoRestaurante(getContext()))){
            getActivity().setTitle("Lista de Pratos");
            FragmentTransaction t = getFragmentManager().beginTransaction();
            Fragment mFrag = new ListaPratoFragment();
            t.replace(R.id.frameRestaurante, mFrag);
            t.commit();
        }
    }

    private boolean validarCampos(){
        Validacao validacao = new Validacao();
        if (!validacao.verificarCampoVazio(nomePrato.getText().toString())){
            nomePrato.setError("Nome do prato vazio");
            return false;
        }
        if (!validacao.verificarCampoVazio(descricaoPrato.getText().toString())){
            descricaoPrato.setError("Descrição do prato vazia");
            return false;
        }
        if (!validacao.verificarCampoVazio(valorPrato.getText().toString())){
            valorPrato.setError("Valor do prato vazio");
            return false;
        }
        return true;
    }
}