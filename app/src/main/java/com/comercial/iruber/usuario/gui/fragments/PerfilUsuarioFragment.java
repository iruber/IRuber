package com.comercial.iruber.usuario.gui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.EnumTipo;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.usuario.dominio.Usuario;

public class PerfilUsuarioFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_perfil_usuario, container, false);
        Usuario usuario = Sessao.getSessao(getContext());
        final CardView cardDocumento = inflate.findViewById(R.id.cardDocumento);
        CardView cardTelefone = inflate.findViewById(R.id.cardTelefone);
        final CardView cardEmail = inflate.findViewById(R.id.cardEmail);
        final CardView cardNome = inflate.findViewById(R.id.cardNome);
        CardView cardEndereco = inflate.findViewById(R.id.cardEndereco);
        CardView cardSenha = inflate.findViewById(R.id.cardSenha);
        final TextView documentoTipo = inflate.findViewById(R.id.textViewDocumento);
        TextView documento = inflate.findViewById(R.id.documento_user_show);
        final TextView nome = inflate.findViewById(R.id.nome_user_show);
        TextView telefone = inflate.findViewById(R.id.telefone_user_show);
        final TextView endereco = inflate.findViewById(R.id.endereco_user_show);
        final TextView email = inflate.findViewById(R.id.email_user_show);
        if(usuario.getTipo().getDescricao().equals(EnumTipo.RESTAURANTE.getDescricao())){
            carregarDadosRestaurante(usuario, cardTelefone, cardEmail, documentoTipo, documento, nome, email);
        }else if(usuario.getTipo().getDescricao().equals(EnumTipo.ENTREGADOR.getDescricao())){
            carregarDadosEntregador(usuario, cardDocumento, cardEndereco, nome, telefone, email);
        }else{
            carregarDadosCliente(usuario, cardTelefone, cardEmail, documento, nome, endereco, email);
        }
        cardEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarPerfil("Alterar E-mail", "email");
            }
        });
        cardSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarPerfil("Alterar Senha", "senha");
            }
        });
        cardDocumento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarPerfil("Alterar Documento", "documento");
            }
        });
        cardNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarPerfil("Alterar Nome", "nome");
            }
        });
        cardTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarPerfil("Alterar Telefone", "telefone");
            }
        });
        cardEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarPerfil("Alterar Endereco", "endereco");
            }
        });
        cardSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarPerfil("Alterar Senha", "senha");
            }
        });
        return inflate;
    }

    private void alterarPerfil(String titulo, String campo) {
        getActivity().setTitle(titulo);
        FragmentTransaction t = getFragmentManager().beginTransaction();
        Fragment mFrag = new PerfilEditFragment();
        ((PerfilEditFragment) mFrag).campoAlterar = campo;
        t.replace(R.id.perfilUsuario, mFrag);
        t.commit();
    }

    public void carregarDadosCliente(Usuario usuario, CardView cardTelefone, CardView cardEmail, TextView documento, TextView nome, TextView endereco, TextView email) {
        cardEmail.setVisibility(View.GONE);
        cardTelefone.setVisibility(View.GONE);
        Cliente cliente = Sessao.getSessaoCliente(getContext());
        documento.setText(cliente.getCpf());
        nome.setText(cliente.getNome());
        email.setText(usuario.getEmail());
        endereco.setText(cliente.getEndereco().getCep());
    }

    public void carregarDadosEntregador(Usuario usuario, CardView cardDocumento, CardView cardEndereco, TextView nome, TextView telefone, TextView email) {
        cardDocumento.setVisibility(View.GONE);
        cardEndereco.setVisibility(View.GONE);
        Entregador entregador = Sessao.getSessaoEntregador(getContext());
        nome.setText(entregador.getNome());
        telefone.setText(entregador.getTelefone());
        email.setText(usuario.getEmail());
    }

    public void carregarDadosRestaurante(Usuario usuario, CardView cardTelefone, CardView cardEmail, TextView documentoTipo, TextView documento, TextView nome, TextView email) {
        cardTelefone.setVisibility(View.GONE);
        cardEmail.setVisibility(View.GONE);
        documentoTipo.setText("CNPJ");
        Restaurante restaurante = Sessao.getSessaoRestaurante(getContext());
        documento.setText(restaurante.getCnpj());
        nome.setText(restaurante.getNome());
        email.setText(usuario.getEmail());
    }

}
