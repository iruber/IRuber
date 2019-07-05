package com.comercial.iruber.restaurante.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.comercial.iruber.infra.servicos.MascaraMonetaria;
import com.comercial.iruber.infra.servicos.Validacao;
import com.comercial.iruber.pedido.dominio.StatusDisponibilidade;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.negocio.PratoServicos;

import java.math.BigDecimal;

public class CadastroPratoFragment extends Fragment {
    private EditText nomePrato;
    private EditText descricaoPrato;
    private EditText valorPrato;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_cadastro_prato, container, false);
        Button criarPrato = inflate.findViewById(R.id.criarPrato);
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
        valorPrato.addTextChangedListener(new MascaraMonetaria(valorPrato));
        return inflate;
    }

    private void cadastrarPrato() throws IruberException {
        if (!validarCampos()) {
            return;
        }
        Prato prato = criarPrato();
        PratoServicos pratoNegocio = new PratoServicos(getContext());
        if (pratoNegocio.registrarPrato(prato, Sessao.getSessaoRestaurante(getContext()))) {
            getActivity().setTitle("Lista de Pratos");
            FragmentTransaction t = getFragmentManager().beginTransaction();
            Fragment mFrag = new ListaPratoFragment();
            t.replace(R.id.frameRestaurante, mFrag);
            t.commit();
        }
    }

    @NonNull
    private Prato criarPrato() {
        Prato prato = new Prato();
        prato.setNome(nomePrato.getText().toString());
        prato.setDescricao(descricaoPrato.getText().toString());
        prato.setDisponivel(StatusDisponibilidade.ATIVO.getDescricao());
        prato.setIdRestaurante(Sessao.getSessaoRestaurante(getContext()).getIdRestaurante());
        BigDecimal valor = new BigDecimal(valorPrato.getText().toString().substring(3).replace(',','.'));
        prato.setValor(valor);
        return prato;
    }

    private boolean validarCampos() {
        Validacao validacao = new Validacao();
        if (!validacao.verificarCampoVazio(nomePrato.getText().toString())) {
            nomePrato.setError("Nome do prato vazio");
            return false;
        }
        if (!validacao.verificarCampoVazio(descricaoPrato.getText().toString())) {
            descricaoPrato.setError("Descrição do prato vazia");
            return false;
        }
        if (!validacao.verificarCampoVazio(valorPrato.getText().toString())) {
            valorPrato.setError("Valor do prato vazio");
            return false;
        }
        return true;
    }
}
