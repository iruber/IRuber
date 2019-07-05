package com.comercial.iruber.cliente.gui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.comercial.iruber.R;

import org.w3c.dom.Text;

import java.util.Objects;


public class AvaliacaoFragment extends Fragment {

    private RatingBar ratingBar;
    private TextView tvRatingBar;
    private Button btnCancel, btnSubmit;

    private OnFragmentInteractionListener mListener;

    public AvaliacaoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rv = inflater.inflate(R.layout.fragment_avaliacao, container, false);
        Button btnSubmit = rv.findViewById(R.id.btnSubmit);
        Button btnCancelar = rv.findViewById(R.id.btnCancelSubmit);
        RatingBar rbAvaliacao = ratingBar.findViewById(R.id.ratingBarAvaliacao);
        final TextView tvRatingExibido = tvRatingBar.findViewById(R.id.tvRatingScale);
        rbAvaliacao.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                tvRatingExibido.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        tvRatingExibido.setText("Muito Ruim");
                        break;
                    case 2:
                        tvRatingExibido.setText("Precisa melhorar");
                        break;
                    case 3:
                        tvRatingExibido.setText("Bom");
                        break;
                    case 4:
                        tvRatingExibido.setText("Ótimo");
                        break;
                    case 5:
                        tvRatingExibido.setText("Eu Adorei!");
                        break;
                    default:
                        tvRatingExibido.setText("");
                }
            }});
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Avaliação Enviada", Toast.LENGTH_SHORT).show();

                                         }
                                     });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                ListaRestauranteFragment listaRestauranteFragmentt = new ListaRestauranteFragment();
                transaction.replace(R.id.frameCliente, listaRestauranteFragmentt);
                transaction.commit();
            }
        });
        return rv;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
