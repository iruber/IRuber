package com.comercial.iruber.pedido.dominio.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.StatusPedido;
import com.comercial.iruber.restaurante.dominio.Prato;
import com.comercial.iruber.restaurante.persistencia.RestauranteDAO;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PedidoDAO {

    private DbHelper bancoDados;
    private ItemPedidoDAO itemPedidoDAO;
    private ClienteDAO clienteDAO;
    private RestauranteDAO restauranteDAO;



    public PedidoDAO(Context context) {
        bancoDados = new DbHelper(context);
        itemPedidoDAO = new ItemPedidoDAO(context);
        clienteDAO =  new ClienteDAO(context);
        restauranteDAO = new RestauranteDAO(context);

    }


    public long inserirPedido (Pedido pedido){

        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();

        BigDecimal valor = pedido.getValorTotal();
        String valorTotal= valor.toString();
        Date dataCompra = pedido.getData();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String dataDaCompra = dateFormat.format(dataCompra);
        StatusPedido status= pedido.getStatusPedido();
        String statusString= status.toString();
        long idRestaurante = this.restauranteDAO.inserirRestaurante(pedido.getRestaurante());
        long idCliente = this.clienteDAO.inserirCliente(pedido.getCliente());

        values.put(ContratoPedido.PEDIDO_CLIENTE_ID,idCliente);
        values.put(ContratoPedido.PEDIDO_DATA,dataDaCompra);
        values.put(ContratoPedido.PEDIDO_STATUS,statusString);
        values.put(ContratoPedido.PEDIDO_RESTAURANTE_ID,idRestaurante);
        values.put(ContratoPedido.PEDIDO_VALORTOTAL,valorTotal);

        long id = bancoEscreve.insert(ContratoPedido.NOME_TABELA,null,values);

        return id;
    }
    public Pedido criarPedido(Cursor cursor){
        String id  = ContratoPedido.PEDIDO_ID;
        int colunaId=cursor.getColumnIndex(id);
        long idPedido = cursor.getLong(colunaId);
        String valor=ContratoPedido.PEDIDO_VALORTOTAL;
        int colunaValor=cursor.getColumnIndex(valor);
        String valorTotal= cursor.getString(colunaValor);
        BigDecimal valorTotalBig= new BigDecimal(valorTotal);
        String colunaStatus=ContratoPedido.PEDIDO_STATUS;
        int colunaIndexStatus=cursor.getColumnIndex(colunaStatus);
        String statusPedido = cursor.getString(colunaIndexStatus);
        StatusPedido statusPedido1 = StatusPedido.valueOf(statusPedido);
        String idRestaurante = ContratoPedido.PEDIDO_RESTAURANTE_ID;
        int colunaIdRestaurante=cursor.getColumnIndex(idRestaurante);
        long idRestaurante1= cursor.getLong(colunaIdRestaurante);
        String idCliente = ContratoPedido.PEDIDO_CLIENTE_ID;
        int colunaIdCliente=cursor.getColumnIndex(idCliente);
        long idCliente1= cursor.getLong(colunaIdCliente);
        String dataColuna=ContratoPedido.PEDIDO_DATA;
        int dataIndex=cursor.getColumnIndex(dataColuna);
        String dataString =cursor.getString(dataIndex);
        Date date = new Date(dataString);

        Pedido pedido = new Pedido();
        pedido.setIdPedido(idPedido);
        pedido.setData(date);
        pedido.setValorTotal(valorTotalBig);
        pedido.setStatusPedido(statusPedido1);
        pedido.setCliente(clienteDAO.getClienteById(idCliente1));
        pedido.setRestaurante(restauranteDAO.getRestauranteById(idRestaurante1));
        return pedido;

    }

    private Pedido criar(String query, String[] args) {

        SQLiteDatabase db = bancoDados.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, args);
         Pedido pedido = null;
        if (cursor.moveToNext()) {
            pedido = this.criarPedido(cursor);
        }
        cursor.close();
        db.close();
        return pedido;
    }
    public Pedido getByID(long id) {
        String query = "SELECT * FROM pedido " +
                "WHERE id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }



    public ArrayList<Pedido> getAll(){
        String query = "SELECT * FROM pedido";
        String[] args = {};
        return this.criarMuitosPedidos(query, null);
    }

    public ArrayList<Pedido> criarMuitosPedidos(String query, String[] args) {
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query, args);
        ArrayList<Pedido> listaPedidos= new ArrayList();

        Pedido pedido = null;
        if (cursor.moveToFirst()) {
            do{
                pedido = criarPedido(cursor);
                listaPedidos.add(pedido);

            }while(cursor.moveToNext());
        }
        cursor.close();
        leitorBanco.close();
        return listaPedidos;
    }
}
