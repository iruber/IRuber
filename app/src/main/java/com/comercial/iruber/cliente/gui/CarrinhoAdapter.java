package com.comercial.iruber.cliente.gui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.restaurante.dominio.Prato;

import java.util.ArrayList;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.ViewHolder>{
    private ArrayList<Prato> pratos;

    public CarrinhoAdapter(ArrayList<Prato> pratos) {
        this.pratos = pratos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.prato_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Prato prato = pratos.get(i);
        TextView tvNome = viewHolder.nomePrato;
        tvNome.setText(prato.getNome());
    }

    @Override
    public int getItemCount() {
        return pratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomePrato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomePrato = itemView.findViewById(R.id.nomePratoLista);
        }
    }
}
