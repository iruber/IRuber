package com.comercial.iruber.restaurante.gui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.comercial.iruber.restaurante.dominio.Entregador;

import java.util.List;

public class EntregadorAdapter extends RecyclerView.Adapter {
    public EntregadorAdapter(List<Entregador> entregadores) {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
