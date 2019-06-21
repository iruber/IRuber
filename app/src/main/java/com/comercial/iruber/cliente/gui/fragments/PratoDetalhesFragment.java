package com.comercial.iruber.cliente.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.comercial.iruber.R;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.dominio.Restaurante;

public class PratoDetalhesFragment extends Fragment {
    private Prato prato;
    private Restaurante restaurante;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalhes_prato,container,false);
        Bundle bundle = getArguments();
        prato = (Prato) bundle.get("prato");
        restaurante = (Restaurante) bundle.get("restaurante");
        TextView tvNome = rootView.findViewById(R.id.nomePratoDetalhes);
        TextView tvDescricao = rootView.findViewById(R.id.descricaoPratoDetalhes);
        tvNome.setText(prato.getNome());
        tvDescricao.setText(prato.getDescricao());
        Button btnVoltar = rootView.findViewById(R.id.voltarPratoDetalhes);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("restaurante", restaurante);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                RestauranteCardapioFragment restauranteCardapioFragment = new RestauranteCardapioFragment();
                restauranteCardapioFragment.setArguments(bundle);
                transaction.replace(R.id.frameCliente, restauranteCardapioFragment);
                transaction.commit();
            }
        });
        return rootView;
    }
}
