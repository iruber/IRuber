package com.comercial.iruber.restaurante.gui.fragments;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.EnumTipo;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.infra.servicos.Validacao;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.EnumEntregador;
import com.comercial.iruber.restaurante.negocio.EntregadorServicos;
import com.comercial.iruber.usuario.dominio.Usuario;

public class CadastroEntregadorFragment extends Fragment {
    private EditText nomeEntregador;
    private EditText telefone;
    private EditText login;
    private EditText senhaEntregador;
    private EditText senhaConfirm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_cadastro_entregador, container, false);
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
        telefone = inflate.findViewById(R.id.telefoneEntregador);
        login = inflate.findViewById(R.id.loginEntregador);
        senhaEntregador = inflate.findViewById(R.id.etSenhaEntregador);
        senhaConfirm = inflate.findViewById(R.id.etSenhaEntregadorConfirm);
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
            t.replace(R.id.frameRestaurante, mFrag);
            t.commit();
        }
    }

    private Entregador criarEntregador(){
        String nome = nomeEntregador.getText().toString().trim();
        Entregador entregador = new Entregador();
        entregador.setNome(nome);
        entregador.setEstado(EnumEntregador.DISPONIVEL);
        entregador.setUsuario(criarUsuario());
        entregador.setTelefone(telefone.getText().toString());
        entregador.setIdRestaurante(Sessao.getSessaoRestaurante(getContext()).getIdRestaurante());
        return entregador;
    }
    private Usuario criarUsuario() {
        String email = login.getText().toString().trim();
        String senha = senhaConfirm.getText().toString().trim();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setTipo(EnumTipo.ENTREGADOR);
        return usuario;
    }
    private boolean validarCampos() {
        Validacao validar = new Validacao();
        return validar.verificarCampoVazio(nomeEntregador.getText().toString());
    }
}
