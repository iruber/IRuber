package com.comercial.iruber.usuario.gui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.EnumTipo;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.usuario.dominio.Usuario;

public class PerfilEditFragment extends Fragment {
    protected String campoAlterar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_perfil_edit, container, false);
        Usuario usuario = Sessao.getSessao(getContext());
        Restaurante restaurante = new Restaurante();
        Cliente cliente = new Cliente();
        Entregador entregador = new Entregador();
        if (usuario.getTipo() == EnumTipo.RESTAURANTE){
            restaurante = Sessao.getSessaoRestaurante(getContext());
        }else if(usuario.getTipo() == EnumTipo.CLIENTE){
            cliente = Sessao.getSessaoCliente(getContext());
        }else {
            entregador = Sessao.getSessaoEntregador(getContext());
        }
        EditText alterarCampo = inflate.findViewById(R.id.alterarPerfilEdit);
        TextView alterarCampoView = inflate.findViewById(R.id.textViewAlterar);
        if (campoAlterar.equals("email")){
            alterarCampoView.setText("E-mail");
            alterarCampo.setText(usuario.getEmail());
        }else if(campoAlterar.equals("nome")){
            alterarCampoView.setText("Nome Completo");
            alterarCampo.setText(usuario.getEmail());
        }else if(campoAlterar.equals("documento")){
            if (usuario.getTipo() == EnumTipo.RESTAURANTE){
                alterarCampoView.setText("Cnpj");
                alterarCampo.setText(restaurante.getCnpj());
            }else{
                alterarCampoView.setText("Cpf");
                alterarCampo.setText(cliente.getCpf());
            }
        }else if(campoAlterar.equals("telefone")){
            alterarCampoView.setText("Telefone");
            alterarCampo.setText(entregador.getTelefone());
        }else if(campoAlterar.equals("senha")){
            alterarCampoView.setText("Senha");
        }else if(campoAlterar.equals("endereco")){
            alterarCampoView.setText("Endereco");
            alterarCampo.setText(cliente.getEndereco().getBairro());
        }
        return inflate;
    }

}
