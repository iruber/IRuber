package com.comercial.iruber.restaurante.gui.fragments;
import android.util.Log;
import com.comercial.iruber.restaurante.gui.FiltroAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.EnumFiltro;
import com.comercial.iruber.restaurante.gui.FiltroAdapter;
import com.comercial.iruber.infra.OpcoesFiltro;

import java.util.ArrayList;
import java.util.List;

public class PratoFiltroFragment extends Fragment {
    private EnumFiltro novoFiltro;
    private EnumFiltro anteriorfiltro;
    private Integer preSelectedIndex;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filtro_fragment, parent, false);
        final List<OpcoesFiltro> opcoes = new ArrayList<>();
        opcoes.add(new OpcoesFiltro(false, "Nome", EnumFiltro.NOME));
        opcoes.add(new OpcoesFiltro(false, "Preço", EnumFiltro.PRECO));
        Bundle bundle = getArguments();
        anteriorfiltro = (EnumFiltro) bundle.get("TipoFiltro");
        novoFiltro = EnumFiltro.SEM_FILTRO;

        TextView fechar = rootView.findViewById(R.id.fechar);
        fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("TipoFiltro", anteriorfiltro);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ListaPratoFragment listaPratoFragment = new ListaPratoFragment();
                listaPratoFragment.setArguments(bundle1);
                transaction.replace(R.id.frameRestaurante, listaPratoFragment);
                transaction.commit();
            }
        });

        Button limpar = rootView.findViewById(R.id.limparFiltros);
        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("TipoFiltro", EnumFiltro.SEM_FILTRO);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ListaPratoFragment listaPratoFragment = new ListaPratoFragment();
                listaPratoFragment.setArguments(bundle2);
                transaction.replace(R.id.frameRestaurante, listaPratoFragment);
                transaction.commit();
            }
        });

        Button filtrar = rootView.findViewById(R.id.filtrarlista);
        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!opcoes.get(0).isSelected() && !opcoes.get(1).isSelected()) {
                    novoFiltro = EnumFiltro.SEM_FILTRO;
                }
                Bundle bundle3 = new Bundle();
                bundle3.putSerializable("TipoFiltro", novoFiltro);

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ListaPratoFragment listaPratoFragment = new ListaPratoFragment();
                listaPratoFragment.setArguments(bundle3);
                transaction.replace(R.id.frameRestaurante, listaPratoFragment);
                transaction.commit();
            }
        });

        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        final FiltroAdapter adapter = new FiltroAdapter(getActivity(), opcoes);
        listView.setAdapter(adapter);
        Log.d("TAG", "1 O adapter foi setado.");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OpcoesFiltro opcao = opcoes.get(i);
                if(preSelectedIndex != null){
                    OpcoesFiltro preRecord = opcoes.get(preSelectedIndex);
                    if(i == preSelectedIndex){
                        opcao.setSelected(!preRecord.isSelected());
                        opcoes.set(i,opcao);
                    } else {
                        opcao.setSelected(true);
                        opcoes.set(i,opcao);
                        preRecord.setSelected(false);
                        opcoes.set(preSelectedIndex, preRecord);
                    }
                } else {
                    opcao.setSelected(true);
                    opcoes.set(i, opcao);
                }
                preSelectedIndex = i;
                for(OpcoesFiltro op : opcoes){
                    if(op.isSelected()){
                        switch (op.getEnumFiltro()){
                            case SEM_FILTRO:
                                novoFiltro = EnumFiltro.SEM_FILTRO;
                                break;
                            case NOME:
                                novoFiltro = EnumFiltro.NOME;
                                break;
                            case PRECO:
                                novoFiltro = EnumFiltro.PRECO;
                                break;
                            default:
                                novoFiltro = EnumFiltro.SEM_FILTRO;
                                break;
                        }
                    }
                }
                adapter.updateRecords(opcoes);
            }
        });
        return rootView;
    }


}
