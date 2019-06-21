package com.comercial.iruber.pedido.dominio.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.StatusPedido;
import com.comercial.iruber.restaurante.persistencia.ContratoPrato;
import com.comercial.iruber.restaurante.persistencia.IngredienteDAO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PedidoDAO {

    private static final String SELECT_FROM_PEDIDO = "SELECT * FROM pedido ";
    private DbHelper bancoDados;
    private IngredienteDAO ingrediente;

    public PedidoDAO(Context context) {
        bancoDados = new DbHelper(context);
        ingrediente = new IngredienteDAO(context);

    }

    public long inserirPedido(Pedido pedido) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        long idCliente = pedido.getIdcliente();
        long idRestaurante = pedido.getIdrestaurante();
        long idEntregador = pedido.getIdentregador();
        long idItemPedido = pedido.getItenPedido();
        Date date = pedido.getData();
        BigDecimal valorTotal = pedido.getValorTotal();
        StatusPedido statusPedido = pedido.getStatusPedido();

        values.put(ContratoPedido.PEDIDO_CLIENTE_ID, idCliente);
        values.put(ContratoPedido.PEDIDO_RESTAURANTE_ID, idRestaurante);
        values.put(ContratoPedido.PEDIDO_ENTREGADOR_ID, idEntregador);
        values.put(ContratoPedido.PEDIDO_ID_ITEMPEDIDO, idItemPedido);
        values.put(ContratoPedido.PEDIDO_DATA, date.toString());
        values.put(ContratoPedido.PEDIDO_VALORTOTAL, valorTotal.toString());
        values.put(ContratoPedido.PEDIDO_STATUS, statusPedido.getDescricao());


        return bancoEscreve.insert(ContratoPrato.NOME_TABELA, null, values);
    }

    private Pedido criarPedido(Cursor cursor)throws Exception {
        String colunaId = ContratoPedido.PEDIDO_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        String idClienteColuna = ContratoPedido.PEDIDO_CLIENTE_ID;
        int indexColunaCliente = cursor.getColumnIndex(idClienteColuna);
        long idCliente = cursor.getLong(indexColunaCliente);
        String idRestauranteColuna = ContratoPedido.PEDIDO_RESTAURANTE_ID;
        int indexColunaRestaurante = cursor.getColumnIndex(idRestauranteColuna);
        long idRestaurante = cursor.getLong(indexColunaRestaurante);
        String idEntregadorColuna = ContratoPedido.PEDIDO_ENTREGADOR_ID;
        int indexColunaEntregador = cursor.getColumnIndex(idEntregadorColuna);
        long idEntregador = cursor.getLong(indexColunaEntregador);
        String idItemPedidoColuna = ContratoPedido.PEDIDO_ID_ITEMPEDIDO;
        int indexColunaItemPedido = cursor.getColumnIndex(idItemPedidoColuna);
        long idItemPedido = cursor.getLong(indexColunaItemPedido);
        String dataColuna = ContratoPedido.PEDIDO_DATA;
        int indexColunaData = cursor.getColumnIndex(dataColuna);
        String dataString = cursor.getString(indexColunaData);


        String valorTotalColuna =ContratoPedido.PEDIDO_VALORTOTAL;
        int colunaIndexValorTotal=cursor.getColumnIndex(valorTotalColuna);
        String valorTotalString=cursor.getString(colunaIndexValorTotal);
        BigDecimal valorTotal = new BigDecimal(valorTotalString);
        String statuaColuna=ContratoPedido.PEDIDO_STATUS;
        int statusIndexColuna=cursor.getColumnIndex(statuaColuna);
        String statusString=cursor.getString(statusIndexColuna);
        StatusPedido statusPedido=StatusPedido.valueOf(statusString);

        Pedido pedido = new Pedido();
        pedido.setIdPedido(id);
        pedido.setIdrestaurante(idRestaurante);
        pedido.setIdcliente(idCliente);
        pedido.setIdentregador(idEntregador);
        pedido.setItenPedido(idItemPedido);
        pedido.setValorTotal(valorTotal);
        pedido.setStatusPedido(statusPedido);
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
        pedido.setData(date1);
        return pedido;
    }


    private Pedido criar(String query, String[] args) throws Exception {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        Pedido pedido = null;
        if (cursor.moveToNext()) {
            pedido = criarPedido(cursor);
        }
        cursor.close();
        leitorBanco.close();
        return pedido;
    }

    public Pedido getPedidoPorId(long id)throws Exception {
        String query = SELECT_FROM_PEDIDO +
                "WHERE id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }


    public Pedido getPedidoPorIdRestaurante(long idRestaurante)throws Exception {
        String query = SELECT_FROM_PEDIDO +
                "WHERE idRestaurante = ?";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criar(query, args);
    }

    public Pedido getPedidoPorIdCliente(long idCliente)throws Exception {
        String query = SELECT_FROM_PEDIDO +
                "WHERE idCliente = ?";
        String[] args = {String.valueOf(idCliente)};
        return this.criar(query, args);
    }

    public Pedido getPedidoPorIdEntregador(long idEntregador)throws Exception {
        String query = SELECT_FROM_PEDIDO +
                "WHERE idEntregador = ?";
        String[] args = {String.valueOf(idEntregador)};
        return this.criar(query, args);
    }


    public void updatePedido(Pedido pedido) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = "id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoPedido.PEDIDO_STATUS,pedido.getStatusPedido().toString());
        values.put(ContratoPedido.PEDIDO_VALORTOTAL,pedido.getValorTotal().toString());
        values.put(ContratoPedido.PEDIDO_DATA,pedido.getData().toString());
        values.put(ContratoPedido.PEDIDO_ID_ITEMPEDIDO,pedido.getItenPedido());
        values.put(ContratoPedido.PEDIDO_CLIENTE_ID,pedido.getIdcliente());
        values.put(ContratoPedido.PEDIDO_ENTREGADOR_ID,pedido.getIdentregador());
        values.put(ContratoPedido.PEDIDO_RESTAURANTE_ID,pedido.getIdrestaurante());



        String[] args = {String.valueOf(pedido.getIdPedido())};
        escritorBanco.update(ContratoPedido.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }


}
