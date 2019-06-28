package com.comercial.iruber.restaurante.gui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.gui.fragments.EditarPratoFragment;

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
        return new ViewHolder(pratoView);
    }

    @Override
    public void onBindViewHolder(@NonNull PratosAdapter.ViewHolder viewHolder, int position) {
        Prato prato = mPratos.get(position);
        TextView nomeView = viewHolder.nomePrato;
        TextView valorView = viewHolder.valorPrato;
        nomeView.setText(prato.getNome());
        String valor = "R$"+prato.getValor().toString();
        valorView.setText(valor);
        Log.d("valor: ", valor);
        viewHolder.idPrato = (Long.toString(prato.getId()));
    }

    @Override
    public int getItemCount() {
        return mPratos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomePrato;
        TextView valorPrato;
        String idPrato;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomePrato = itemView.findViewById(R.id.nomePratoLista);
            valorPrato = itemView.findViewById(R.id.valorPratoLista);
            final Context contexto = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) contexto).setTitle("Editar Prato");
                    FragmentTransaction t = ((AppCompatActivity) contexto).getSupportFragmentManager().beginTransaction();
                    Fragment mFrag = new EditarPratoFragment();
                    EditarPratoFragment.idPrato = idPrato;
                    t.replace(R.id.frameRestaurante, mFrag);
                    t.commit();
                }
            });
        }
    }
}
