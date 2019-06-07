package com.comercial.iruber.restaurante.gui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.restaurante.dominio.Ingrediente;
import com.comercial.iruber.restaurante.dominio.Prato;

import java.util.List;

public class IngredientesAdapter extends RecyclerView.Adapter<IngredientesAdapter.ViewHolder>{
    private List<Ingrediente> mIngredientes;

    public IngredientesAdapter(List<Ingrediente> ingredientes) {
        mIngredientes = ingredientes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View ingredienteView = inflater.inflate(R.layout.ingrediente_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(ingredienteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientesAdapter.ViewHolder viewHolder, int position) {
        Ingrediente ingrediente = mIngredientes.get(position);
        TextView nomeView = viewHolder.nomeIngredienteLista;
        nomeView.setText(ingrediente.getNome());
        viewHolder.idIngrediente = (Long.toString(ingrediente.getIdIngrediente()));
    }

    @Override
    public int getItemCount() {
        return mIngredientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nomeIngredienteLista;
        public String idIngrediente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeIngredienteLista = itemView.findViewById(R.id.nomeIngredienteLista);
        }
    }
}
