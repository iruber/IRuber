package com.comercial.iruber.restaurante.gui.fragments;

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
import com.comercial.iruber.infra.FiltroAdapter;
import com.comercial.iruber.infra.OpcoesFiltro;

import java.util.ArrayList;
import java.util.List;

public class IngredientesFiltroFragment extends Fragment {
    private EnumFiltro novoFiltro;
    private EnumFiltro anteriorfiltro;
    private Integer preSelectedIndex;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filtro_fragment, parent, false);

        final List<OpcoesFiltro> opcoes = new ArrayList<>();
        opcoes.add(new OpcoesFiltro(false, "Nome", EnumFiltro.NOME));
        opcoes.add(new OpcoesFiltro(false, "Ativado", EnumFiltro.ATIVADO));
        opcoes.add(new OpcoesFiltro(false, "Desativado", EnumFiltro.DESATIVADO));
        opcoes.add(new OpcoesFiltro(false, "Em falta", EnumFiltro.EM_FALTA));

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
                ListaIngredienteFragment listaIngredienteFragment = new ListaIngredienteFragment();
                listaIngredienteFragment.setArguments(bundle1);
                transaction.replace(R.id.frameRestaurante, listaIngredienteFragment);
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
                ListaIngredienteFragment listaIngredienteFragment = new ListaIngredienteFragment();
                listaIngredienteFragment.setArguments(bundle2);
                transaction.replace(R.id.frameRestaurante, listaIngredienteFragment);
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
                ListaIngredienteFragment listaIngredienteFragment = new ListaIngredienteFragment();
                listaIngredienteFragment.setArguments(bundle3);
                transaction.replace(R.id.frameRestaurante, listaIngredienteFragment);
                transaction.commit();
            }
        });

        ListView listView = (ListView) rootView.findViewById(R.id.listview);

        final FiltroAdapter adapter = new FiltroAdapter(getActivity(), opcoes);
        listView.setAdapter(adapter);
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
                            case ATIVADO:
                                novoFiltro = EnumFiltro.ATIVADO;
                                break;
                            case DESATIVADO:
                                novoFiltro = EnumFiltro.DESATIVADO;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
