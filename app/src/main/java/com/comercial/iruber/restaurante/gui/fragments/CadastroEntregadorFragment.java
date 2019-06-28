package com.comercial.iruber.restaurante.gui.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.infra.servicos.Validacao;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.negocio.EntregadorServicos;
import com.comercial.iruber.usuario.dominio.Usuario;

public class CadastroEntregadorFragment extends Fragment {
    private EditText nomeEntregador;
    private EditText userName;
    private EditText senha;
    private EditText senhaConfirm;
    private EditText campoEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_cadastro_prato, container, false);
        Button criarEntregador = inflate.findViewById(R.id.btnCadastrarEntregador);
        criarEntregador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cadastrarEntregador();
                } catch (IruberException e) {
                    e.printStackTrace();
                }
            }
        });
        nomeEntregador = inflate.findViewById(R.id.nomeEntregador);
        userName = inflate.findViewById(R.id.userNameEntregador);
        senha = inflate.findViewById(R.id.etSenhaEntregador);
        senhaConfirm = inflate.findViewById(R.id.etSenhaEntregadorConfirm);
        campoEmail = inflate.findViewById(R.id.etEmailEntregador);
        return inflate;
    }
    private void cadastrarEntregador() throws IruberException {
        if (!validarCampos()) {
            return;
        }
        Entregador entregador = criarEntregador();
        EntregadorServicos entregadorServicos = new EntregadorServicos(getContext());
        if (entregadorServicos.registrarEntregador(entregador, Sessao.getSessaoRestaurante(getContext()))) {
            getActivity().setTitle("Lista de Entregadores");
            FragmentTransaction t = getFragmentManager().beginTransaction();
            Fragment mFrag = new ListaEntregadorFragment();
            t.replace(R.id.frameEntregador, mFrag);
            t.commit();
        }
    }

    private Entregador criarEntregador() {
        Entregador entregador = new Entregador();
        return entregador;
    }


    private Usuario criarUsuario() {
        String email = campoEmail.getText().toString().trim();
        String senha = senhaConfirm.getText().toString().trim();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }
    private boolean validarCampos() {
        Validacao validar = new Validacao();
        return validar.verificarCampoVazio(nomeEntregador.getText().toString());
    }
}
