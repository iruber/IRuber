package com.comercial.iruber.restaurante.gui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.comercial.iruber.R;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.negocio.PratoServicos;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditarPratoFragment extends Fragment {
    public static String idPrato;

    public EditarPratoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_editar_prato, container, false);
        Log.d("prato", idPrato);
//        Prato prato = new PratoServicos();
        EditText nome = inflate.findViewById(R.id.nomeEdit);
        EditText valor = inflate.findViewById(R.id.valorEdit);
        EditText descricao = inflate.findViewById(R.id.descricaoEdit);
        Button atualizarPrato = inflate.findViewById(R.id.atualizarPrato);
        Button voltar = inflate.findViewById(R.id.voltarAtualizarPrato);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });
        atualizarPrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PratoServicos pratoServicos = new PratoServicos(getContext());
//                pratoServicos.updatePrato(prato);
                voltar();
            }
        });
        return inflate;
    }

    public void voltar() {
        getActivity().setTitle("Lista de Pratos");
        FragmentTransaction t = getFragmentManager().beginTransaction();
        Fragment mFrag = new ListaPratoFragment();
        t.replace(R.id.frameRestaurante, mFrag);
        t.commit();
    }

}
