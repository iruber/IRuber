package com.comercial.iruber.restaurante.gui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.pedido.dominio.Pedido;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.ViewHolder> {

    private List<Pedido> mPedidos;
    private AdapterListeners onClickListener;

    public PedidosAdapter(List<Pedido> pedidos, AdapterListeners listener) {
        mPedidos = pedidos;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View pedidoView = inflater.inflate(R.layout.pedidos_list_item, parent, false);
        return new ViewHolder(pedidoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Pedido pedido = mPedidos.get(position);
        TextView nomeView = viewHolder.nomeClienteTextView;
        TextView dataView = viewHolder.dataTextView;
        TextView idView = viewHolder.idTextView;
        TextView status = viewHolder.statusTextView;
        nomeView.setText(pedido.getCliente().getNome());
        dataView.setText(convertDateToString(pedido.getData()));
        idView.setText(Long.toString(pedido.getIdPedido()));
        status.setText(pedido.getStatusPedido().getDescricao());
    }

    @Override
    public int getItemCount() {
        return mPedidos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomeClienteTextView;
        TextView dataTextView;
        TextView idTextView;
        TextView statusTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeClienteTextView = itemView.findViewById(R.id.nomeCliente);
            dataTextView = itemView.findViewById(R.id.data);
            idTextView = itemView.findViewById(R.id.id);
            statusTextView = itemView.findViewById(R.id.statusPedido);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.verPratos(v, getAdapterPosition());
                }
            });
        }
    }

    private String convertDateToString(Date date) {
        Date date1 = date;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String data = dateFormat.format(date);
        return data;
    }

    public interface AdapterListeners{
        void verPratos(View v, int position);
    }
}
