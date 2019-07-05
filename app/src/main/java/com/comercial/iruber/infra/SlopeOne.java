package com.comercial.iruber.infra;


import android.content.Context;

import com.comercial.iruber.restaurante.dominio.Nota;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.restaurante.negocio.NotaServicos;
import com.comercial.iruber.restaurante.negocio.RestauranteServicos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Daniel Lemire: A simple implementation of the weighted slope one
 * algorithm in Java for item-based collaborative filtering. <br/>
 * <p>
 * See main function for example. June 1st 2006. <br/>
 * Revised by Marco Ponzi on March 29th 2007. <br/>
 * <p>
 * Revised by Guibing Guo on June 7th, 2013.
 * this code was get in https://www.programcreek.com/java-api-examples/index.php?source_dir=HappyResearch-master/happyresearch/src/main/java/happy/research/cf/SlopeOne.java
 */

public class SlopeOne {

    public static ArrayList<String> main(Context contexto, String idcliente) {
        // this is my data base
        Map<String, Map<String, Double>> data = new HashMap<>();
        ArrayList<Nota> notas = null;
        try {
            notas = (ArrayList<Nota>) new NotaServicos(contexto).listarTodasNotas();
        } catch (IruberException e) {
            e.printStackTrace();
        }
        List<Restaurante> restaurantes = new RestauranteServicos(contexto).listarRestaurantes();
        // items
        mAllItems = new String[restaurantes.size()];
        for (int i = 0; i < restaurantes.size(); i++) {
            mAllItems[i] = String.valueOf(restaurantes.get(i).getIdRestaurante());
        }
        ArrayList<String> clientes = new ArrayList<String>();
        for (int i = 0; i < notas.size(); i++) {
            if (!clientes.contains(String.valueOf(notas.get(i).getIdCliente()))) {
                clientes.add(String.valueOf(notas.get(i).getIdCliente()));
            }
        }
        HashMap<String, Double> user = new HashMap<>();
        for (int i = 0; i < clientes.size(); i++) {
            HashMap<String, Double> cliente = new HashMap<>();
            for (int j = 0; j < notas.size(); j++) {
                if (String.valueOf(notas.get(j).getIdCliente()).equals(clientes.get(i))) {
                    cliente.put(String.valueOf(notas.get(j).getIdRestaurante()), notas.get(j).getValor().doubleValue());
                    if (String.valueOf(notas.get(j).getIdCliente()).equals(idcliente)) {
                        user.put(String.valueOf(notas.get(j).getIdRestaurante()),  notas.get(j).getValor().doubleValue());
                    }
                }
            }
            data.put(clientes.get(i), cliente);
        }
        // next, I create my predictor engine
        SlopeOne so = new SlopeOne(data);
        ArrayList<String> resultado = SlopeOne.carregarResultado(so.predict(user));
        return resultado;
    }

    Map<String, Map<String, Double>> mData;
    Map<String, Map<String, Double>> diffMatrix;
    Map<String, Map<String, Integer>> freqMatrix;

    static String[] mAllItems;

    public SlopeOne(Map<String, Map<String, Double>> data) {
        mData = data;
        buildDiffMatrix();
    }

    /**
     * Based on existing data, and using weights,
     * try to predict all missing ratings.
     * The trick to make this more scalable is to consider
     * only mDiffMatrix entries having a large  (>1) mFreqMatrix
     * entry.
     * <p>
     * It will output the prediction 0 when no prediction is possible.
     */
    public Map<String, Double> predict(Map<String, Double> user) {
        HashMap<String, Double> predictions = new HashMap<>();
        HashMap<String, Integer> frequencies = new HashMap<>();
        for (String j : diffMatrix.keySet()) {
            frequencies.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (String j : user.keySet()) {
            for (String k : diffMatrix.keySet()) {
                try {
                    Double newval = (diffMatrix.get(k).get(j) + user.get(j)) * freqMatrix.get(k).get(j).intValue();
                    predictions.put(k, predictions.get(k) + newval);
                    frequencies.put(k, frequencies.get(k) + freqMatrix.get(k).get(j).intValue());
                } catch (NullPointerException e) {
                }
            }
        }
        HashMap<String, Double> cleanpredictions = new HashMap<>();
        for (String j : predictions.keySet()) {
            if (frequencies.get(j) > 0) {
                cleanpredictions.put(j, predictions.get(j) / frequencies.get(j).intValue());
            }
        }
        for (String j : user.keySet()) {
            cleanpredictions.put(j, user.get(j));
        }
        return cleanpredictions;
    }

    /**
     * Based on existing data, and not using weights,
     * try to predict all missing ratings.
     * The trick to make this more scalable is to consider
     * only mDiffMatrix entries having a large  (>1) mFreqMatrix
     * entry.
     */
    public Map<String, Double> weightlesspredict(Map<String, Double> user) {
        HashMap<String, Double> predictions = new HashMap<>();
        HashMap<String, Integer> frequencies = new HashMap<>();
        for (String j : diffMatrix.keySet()) {
            predictions.put(j, 0.0);
            frequencies.put(j, 0);
        }
        for (String j : user.keySet()) {
            for (String k : diffMatrix.keySet()) {
                Double newval = (diffMatrix.get(k).get(j) + user.get(j));
                predictions.put(k, predictions.get(k) + newval);
            }
        }
        for (String j : predictions.keySet()) {
            predictions.put(j, predictions.get(j) / user.size());
        }
        for (String j : user.keySet()) {
            predictions.put(j, user.get(j));
        }
        return predictions;
    }

    public static ArrayList<String> carregarResultado(Map<String, Double> user) {
        ArrayList<String> resultado = new ArrayList<>();
        for (String j : user.keySet()) {
                resultado.add(j);
        }
        return resultado;
    }

    public void buildDiffMatrix() {
        diffMatrix = new HashMap<>();
        freqMatrix = new HashMap<>();
        // first iterate through users
        for (Map<String, Double> user : mData.values()) {
            // then iterate through user data
            for (Map.Entry<String, Double> entry : user.entrySet()) {
                String i1 = entry.getKey();
                double r1 = entry.getValue();
                if (!diffMatrix.containsKey(i1)) {
                    diffMatrix.put(i1, new HashMap<String, Double>());
                    freqMatrix.put(i1, new HashMap<String, Integer>());
                }
                for (Map.Entry<String, Double> entry2 : user.entrySet()) {
                    String i2 = entry2.getKey();
                    double r2 = entry2.getValue();
                    int cnt = 0;
                    if (freqMatrix.get(i1).containsKey(i2)) cnt = freqMatrix.get(i1).get(i2);
                    double diff = 0.0;
                    if (diffMatrix.get(i1).containsKey(i2)) diff = diffMatrix.get(i1).get(i2);
                    double new_diff = r1 - r2;
                    freqMatrix.get(i1).put(i2, cnt + 1);
                    diffMatrix.get(i1).put(i2, diff + new_diff);
                }
            }
        }
        for (String j : diffMatrix.keySet()) {
            for (String i : diffMatrix.get(j).keySet()) {
                Double oldvalue = diffMatrix.get(j).get(i);
                int count = freqMatrix.get(j).get(i).intValue();
                diffMatrix.get(j).put(i, oldvalue / count);
            }
        }
    }
}