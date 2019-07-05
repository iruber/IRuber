package com.comercial.iruber.restaurante.gui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.restaurante.dominio.Ingrediente;

import java.util.ArrayList;
import java.util.List;

public class IngredientesAdapter extends RecyclerView.Adapter<IngredientesAdapter.ViewHolder> {
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
        return new ViewHolder(ingredienteView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientesAdapter.ViewHolder viewHolder, int position) {
        Ingrediente ingrediente = mIngredientes.get(position);
        viewHolder.bind(ingrediente);
        TextView nomeView = viewHolder.nomeIngredienteLista;
        nomeView.setText(ingrediente.getNome());
        viewHolder.idIngrediente = (Long.toString(ingrediente.getIdIngrediente()));
        TextView status = viewHolder.statusIngredienteLista;
        status.setText(ingrediente.getDisponivel().getDescricao());
        if (status.getText().toString().equals("ativo")){
            status.setTextColor(Color.parseColor("#0afa0e"));
        }else {
            status.setTextColor(Color.parseColor("#d9190f"));
        }
    }

    @Override
    public int getItemCount() {
        return mIngredientes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nomeIngredienteLista;
        private TextView statusIngredienteLista;
        private String idIngrediente;
        private CheckBox check;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeIngredienteLista = itemView.findViewById(R.id.nomeIngredienteLista);
            statusIngredienteLista = itemView.findViewById(R.id.statusIngredienteLista);
            check = itemView.findViewById(R.id.checkBox);
        }

        void bind(final Ingrediente ingrediente) {
            check.setChecked(ingrediente.isChecked());
            nomeIngredienteLista.setText(ingrediente.getNome());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ingrediente.setChecked(!ingrediente.isChecked());
                    check.setChecked(ingrediente.isChecked());
                }
            });
        }
    }

    public List<Ingrediente> getAll() {
        return mIngredientes;
    }

    public List<Ingrediente> getSelected() {
        List<Ingrediente> selected = new ArrayList<>();
        for (int i = 0; i < mIngredientes.size(); i++) {
            if (mIngredientes.get(i).isChecked()) {
                selected.add(mIngredientes.get(i));
            }
        }
        return selected;
    }
}
