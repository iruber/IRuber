package com.comercial.iruber.cliente.gui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.restaurante.dominio.Restaurante;

import java.util.List;

public class RestaurantesAdapter extends RecyclerView.Adapter<RestaurantesAdapter.ViewHolder> {
    private List<Restaurante> mRestaurantes;

    public RestaurantesAdapter(List<Restaurante> restaurantes) {
        mRestaurantes = restaurantes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pratoView = inflater.inflate(R.layout.restaurante_item, parent, false);
        return new ViewHolder(pratoView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantesAdapter.ViewHolder viewHolder, int position) {
        Restaurante restaurante = mRestaurantes.get(position);
        TextView nomeView = viewHolder.nomeRestaurante;
        nomeView.setText(restaurante.getNome());
        viewHolder.idRestaurante = (Long.toString(restaurante.getIdRestaurante()));
    }

    @Override
    public int getItemCount() {
        return mRestaurantes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomeRestaurante;
        String idRestaurante;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeRestaurante = itemView.findViewById(R.id.nomeRestauranteLista);
        }
    }
}

