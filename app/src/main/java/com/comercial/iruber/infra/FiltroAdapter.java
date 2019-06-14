package com.comercial.iruber.infra;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.comercial.iruber.R;

import java.util.List;

public class FiltroAdapter extends BaseAdapter {
    Activity activity;
    List<OpcoesFiltro> opcoes;
    LayoutInflater inflater;

    public FiltroAdapter(Activity activity, List<OpcoesFiltro> opcoes) {
        this.activity = activity;
        this.opcoes = opcoes;
    }

    public FiltroAdapter(Activity activty) {
        this.activity = activty;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void updateRecords(List<OpcoesFiltro>  opcoes){
        this.opcoes = opcoes;

        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        class ViewHolder{

            TextView tvOpcao;
            ImageView ivCheckBox;

        }

        ViewHolder holder;

        if(view == null){
            view = inflater.inflate(R.layout.list_view_itemopcao, viewGroup, false);
            holder = new ViewHolder();
            holder.tvOpcao = (TextView) view.findViewById(R.id.tv_opcao);
            holder.ivCheckBox = (ImageView) view.findViewById(R.id.iv_check_box);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        OpcoesFiltro opcao = opcoes.get(i);
        holder.tvOpcao.setText(opcao.getOpcao());

        if(opcao.isSelected()){
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);
        } else {
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);
        }

        return view;

    }
}
