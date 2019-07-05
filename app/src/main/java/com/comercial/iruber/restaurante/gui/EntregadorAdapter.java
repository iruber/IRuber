package com.comercial.iruber.restaurante.gui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.restaurante.dominio.Entregador;

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
    }

    @Override
    public int getItemCount() {
        return mEmtregadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvTelefone;
        TextView tvStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.nomeEntregadorItem);
            tvTelefone = itemView.findViewById(R.id.telefoneEntregadorItem);
            tvStatus = itemView.findViewById(R.id.statusEntregadorItem);
        }
    }
}
