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
import com.comercial.iruber.restaurante.dominio.Prato;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PratosAdapter extends RecyclerView.Adapter<PratosAdapter.ViewHolder> {
    private List<Prato> mPratos;

    public PratosAdapter(List<Prato> pratos) {
        mPratos = pratos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pratoView = inflater.inflate(R.layout.prato_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(pratoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PratosAdapter.ViewHolder viewHolder, int position) {
        Prato prato = mPratos.get(position);
        TextView nomeView = viewHolder.nomePrato;
        nomeView.setText(prato.getNome());
        viewHolder.idPrato = (Long.toString(prato.getIdProduto()));
    }

    @Override
    public int getItemCount() {
        return mPratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nomePrato;
        public String idPrato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomePrato = itemView.findViewById(R.id.nomePratoLista);
        }
    }
}
