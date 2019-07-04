package com.comercial.iruber.pedido.dominio.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comercial.iruber.cliente.persistencia.ClienteDAO;
import com.comercial.iruber.infra.persistencia.DbHelper;
import com.comercial.iruber.pedido.dominio.ItemPedido;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.StatusPedido;
import com.comercial.iruber.restaurante.persistencia.ContratoPrato;
import com.comercial.iruber.restaurante.persistencia.IngredienteDAO;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PedidoDAO {

    private static final String SELECT_FROM_PEDIDO = "SELECT * FROM pedido ";
    private DbHelper bancoDados;
    private IngredienteDAO ingrediente;
    private ClienteDAO clienteDAO;
    private PedidoItemPedidoDAO pedidoItemPedidoDAO;
    private ItemPedidoDAO itemPedidoDAO;

    public PedidoDAO(Context context) {
        bancoDados = new DbHelper(context);
        ingrediente = new IngredienteDAO(context);
        pedidoItemPedidoDAO = new PedidoItemPedidoDAO(context);
        itemPedidoDAO= new ItemPedidoDAO(context);
        clienteDAO = new ClienteDAO(context);
    }

    public long inserirPedido(Pedido pedido) {
        SQLiteDatabase bancoEscreve = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        List<Long> itensPedidos;
        long idRestaurante = pedido.getIdrestaurante();
        Date date = pedido.getData();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String snascimento = dateFormat.format(date);
        itensPedidos=pegarIdsItens(pedido);
        this.inserirTodosItens(pedido.getIdPedido(),itensPedidos);
        StatusPedido statusPedido = pedido.getStatusPedido();
        values.put(ContratoPedido.PEDIDO_CLIENTE_ID, pedido.getCliente().getIdCliente());
        values.put(ContratoPedido.PEDIDO_RESTAURANTE_ID, idRestaurante);
        values.put(ContratoPedido.PEDIDO_DATA, snascimento);
        values.put(ContratoPedido.PEDIDO_STATUS, statusPedido.getDescricao());
        long id= bancoEscreve.insert(ContratoPedido.NOME_TABELA, null, values);
        bancoEscreve.close();
        return  id;
    }

    private Pedido criarPedido(Cursor cursor){
        String colunaId = ContratoPedido.PEDIDO_ID;
        int indexColunaId = cursor.getColumnIndex(colunaId);
        long id = cursor.getLong(indexColunaId);
        String idClienteColuna = ContratoPedido.PEDIDO_CLIENTE_ID;
        int indexColunaCliente = cursor.getColumnIndex(idClienteColuna);
        long idCliente = cursor.getLong(indexColunaCliente);
        String idRestauranteColuna = ContratoPedido.PEDIDO_RESTAURANTE_ID;
        int indexColunaRestaurante = cursor.getColumnIndex(idRestauranteColuna);
        long idRestaurante = cursor.getLong(indexColunaRestaurante);
      String dataColuna = ContratoPedido.PEDIDO_DATA;
       int indexColunaData = cursor.getColumnIndex(dataColuna);
       String dataString = cursor.getString(indexColunaData);
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA).parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

     //   String valorTotalColuna =ContratoPedido.PEDIDO_VALORTOTAL;
     //   int colunaIndexValorTotal=cursor.getColumnIndex(valorTotalColuna);
//        String valorTotalString=cursor.getString(colunaIndexValorTotal);
      //  BigDecimal valorTotal = new BigDecimal(valorTotalString);
       String statuaColuna=ContratoPedido.PEDIDO_STATUS;
        int statusIndexColuna=cursor.getColumnIndex(statuaColuna);
        String statusString=cursor.getString(statusIndexColuna);
        StatusPedido statusPedido=StatusPedido.valueOf(statusString);
        Pedido pedido = new Pedido();
        pedido.setIdPedido(id);
        pedido.setIdrestaurante(idRestaurante);
        pedido.setCliente(clienteDAO.getClienteById(idCliente));
     //   pedido.setValorTotal(valorTotal);
        pedido.setStatusPedido(statusPedido);
        pedido.setItemPedidos(this.listaItensPedidos(id));
        pedido.setData(date);
        return pedido;
    }

    private Pedido criar(String query, String[] args){
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

    public Pedido getPedidoPorId(long id){
        String query = SELECT_FROM_PEDIDO +
                " WHERE id = ?";
        String[] args = {String.valueOf(id)};
        return this.criar(query, args);
    }


    public Pedido getPedidoPorIdRestaurante(long idRestaurante){
        String query = SELECT_FROM_PEDIDO +
                " WHERE idRestaurante = ?";
        String[] args = {String.valueOf(idRestaurante)};
        return this.criar(query, args);
    }

    public Pedido getPedidoPorIdCliente(long idCliente){
        String query = SELECT_FROM_PEDIDO +
                " WHERE idCliente = ?";
        String[] args = {String.valueOf(idCliente)};
        return this.criar(query, args);
    }

    public Pedido getPedidoPorIdEntregador(long idEntregador){
        String query = SELECT_FROM_PEDIDO +
                " WHERE idEntregador = ?";
        String[] args = {String.valueOf(idEntregador)};
        return this.criar(query, args);
    }



    public void updatePedido(Pedido pedido) {
        SQLiteDatabase escritorBanco = bancoDados.getWritableDatabase();
        String query = " id = ?";
        ContentValues values = new ContentValues();
        values.put(ContratoPedido.PEDIDO_STATUS,pedido.getStatusPedido().toString());
        values.put(ContratoPedido.PEDIDO_VALORTOTAL,pedido.getValorTotal().toString());
        values.put(ContratoPedido.PEDIDO_DATA,pedido.getData().toString());
        values.put(ContratoPedido.PEDIDO_CLIENTE_ID,pedido.getCliente().getIdCliente());
        values.put(ContratoPedido.PEDIDO_ENTREGADOR_ID,pedido.getIdentregador());
        values.put(ContratoPedido.PEDIDO_RESTAURANTE_ID,pedido.getIdrestaurante());
        String[] args = {String.valueOf(pedido.getIdPedido())};
        escritorBanco.update(ContratoPedido.NOME_TABELA, values, query, args);
        escritorBanco.close();
    }

    public List<Pedido> getPedidosPorIdRestaurante(long idRestaurante){
        String query = SELECT_FROM_PEDIDO +
                " WHERE idRestaurante = ?";
        String [] args = {String.valueOf(idRestaurante)};
        return this.criarListaPedidos(query,args);
    }

    public List<Pedido> getPedidosPorIdEntregador(long idEntregador){
        String query = SELECT_FROM_PEDIDO +
                " WHERE idEntregador = ?";
        String [] args = {String.valueOf(idEntregador)};
        return this.criarListaPedidos(query,args);
    }

    public List<Pedido> getPedidosPorIdCliente (long idCliente) {
        String query = SELECT_FROM_PEDIDO +
                " WHERE idCliente = ?";
        String [] args = {String.valueOf(idCliente)};
        return this.criarListaPedidos(query,args);
    }

    private ArrayList<Pedido> criarListaPedidos(String query, String [] args){
        SQLiteDatabase leitorBanco = bancoDados.getReadableDatabase();
        Cursor cursor = leitorBanco.rawQuery(query,args);
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        Pedido pedido;
        if (cursor.moveToFirst()){
            do {
                pedido = criarPedido(cursor);
                listaPedidos.add(pedido);
            }while (cursor.moveToNext());
        }
        cursor.close();
        leitorBanco.close();
        return listaPedidos;
    }

    private List<Long> pegarIdsItens(Pedido pedido){
        List<Long> result = new ArrayList<Long>();
        for(ItemPedido item : pedido.getItemPedidos()){
            result.add(item.getIdItemPedido());
        }
        return result;
    }
    private void inserirTodosItens(long idPedido,List<Long> list){
        for(Long id : list){
            pedidoItemPedidoDAO.inserirPedidoItemPedido(idPedido,id);
        }

    }

    private  List<ItemPedido> listaItensPedidos(long idPedido){
        List<ItemPedido> listItens= itemPedidoDAO.listaItensPedido(idPedido);

        return listItens;
    }
}
