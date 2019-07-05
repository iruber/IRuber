package com.comercial.iruber.cliente.gui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.restaurante.dominio.Restaurante;

import java.util.List;

public class RestaurantesAdapter extends RecyclerView.Adapter<RestaurantesAdapter.ViewHolder> {
    private List<Restaurante> mRestaurantes;
    private static ClickListener clickListener;

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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView nomeRestaurante;
        String idRestaurante;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            nomeRestaurante = itemView.findViewById(R.id.nomeRestauranteLista);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        public boolean onLongClick(View v){
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        RestaurantesAdapter.clickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}

