package com.comercial.iruber.restaurante.gui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.negocio.ServicoPedido;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.EnumEntregador;
import com.comercial.iruber.restaurante.gui.fragments.EscolhaEntregadorPedidoFragment;
import com.comercial.iruber.restaurante.gui.fragments.ListaPedidoFragment;
import com.comercial.iruber.restaurante.negocio.EntregadorServicos;

import java.util.List;

public class EntregadorAdapter extends RecyclerView.Adapter<EntregadorAdapter.ViewHolder> {
    private List<Entregador> mEmtregadores;
    public EntregadorAdapter(List<Entregador> entregadores) {
        mEmtregadores = entregadores;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View entregadorView = inflater.inflate(R.layout.entregadores_item, viewGroup, false);
        return new ViewHolder(entregadorView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Entregador entregador = mEmtregadores.get(i);
        TextView nome = viewHolder.tvNome;
        TextView telefone = viewHolder.tvTelefone;
        TextView status = viewHolder.tvStatus;
        nome.setText(entregador.getNome());
        telefone.setText(entregador.getTelefone());
        status.setText(entregador.getEstado().getDescricao());
        viewHolder.id = entregador.getIdEntregador();
        viewHolder.entregadorv = entregador;
    }

    @Override
    public int getItemCount() {
        return mEmtregadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        long id;
        Entregador entregadorv;
        TextView tvNome;
        TextView tvTelefone;
        TextView tvStatus;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.nomeEntregadorItem);
            tvTelefone = itemView.findViewById(R.id.telefoneEntregadorItem);
            tvStatus = itemView.findViewById(R.id.statusEntregadorItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!EscolhaEntregadorPedidoFragment.pedido.equals("")) {
                        Pedido pedido = Sessao.getSessaoPedido(itemView.getContext());
                        pedido.setIdentregador(id);
                        entregadorv.setEstado(EnumEntregador.INDISPONIVEL);
                        new EntregadorServicos(itemView.getContext()).updateEntregador(entregadorv);
                        EscolhaEntregadorPedidoFragment.pedido = "";
                        new ServicoPedido(itemView.getContext()).updatePedido(pedido);
                        ((Activity) itemView.getContext()).setTitle("Pedidos");
                        FragmentTransaction t = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager().beginTransaction();
                        Fragment mFrag = new ListaPedidoFragment();
                        t.replace(R.id.frameRestaurante, mFrag);
                        t.commit();
                    }
                }
            });
        }
    }
}
