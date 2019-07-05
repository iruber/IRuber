package com.comercial.iruber.cliente.gui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.Sessao;
import com.comercial.iruber.pedido.dominio.ItemPedido;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.dominio.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class CardapioAdapter extends RecyclerView.Adapter<CardapioAdapter.ViewHolder> {
    private ArrayList<Prato> mPratos;
    private Pedido pedido = new Pedido();
    private AdapterListeners onClickListener;

    public CardapioAdapter(ArrayList<Prato> lista, AdapterListeners listener){
        this.mPratos = lista;
        this.onClickListener = listener;
        this.pedido.setItemPedidos(new ArrayList<ItemPedido>());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.restaurante_cardapio_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Prato prato = mPratos.get(i);
        TextView tvNome = viewHolder.nomePrato;
        TextView tvPreco = viewHolder.precoPrato;
        tvNome.setText(prato.getNome());
        tvPreco.setText(prato.getValor().toString());
    }

    @Override
    public int getItemCount() {
        return mPratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomePrato;
        TextView precoPrato;
        Button addPrato;
        Button detalhes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomePrato = itemView.findViewById(R.id.nomePratoCR);
            precoPrato = itemView.findViewById(R.id.precoPratoCR);
            addPrato = itemView.findViewById(R.id.addCR);
            detalhes = itemView.findViewById(R.id.detalhesCR);
            addPrato.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onClickListener.adicionarPrato(v, getAdapterPosition());
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPrato(mPratos.get(getAdapterPosition()));
                    getPedido().getItemPedidos().add(itemPedido);
                }
            });
            detalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.verDetalhes(v, getAdapterPosition());
                }
            });
        }
    }

    public interface AdapterListeners{
        void adicionarPrato(View v, int position);
        void verDetalhes(View v, int position);
    }
    public Pedido getPedido(){
        return this.pedido;
    }
}
