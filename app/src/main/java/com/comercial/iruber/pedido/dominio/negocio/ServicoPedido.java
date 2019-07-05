package com.comercial.iruber.pedido.dominio.negocio;

import android.content.Context;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.StatusPedido;
import com.comercial.iruber.pedido.dominio.persistencia.PedidoDAO;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.Restaurante;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ServicoPedido {
    private PedidoDAO pedidoDAO;

    public ServicoPedido(Context context){
        this.pedidoDAO = new PedidoDAO(context);
    }

    public void updatePedido(Pedido pedido) {
        pedidoDAO.updatePedido(pedido);
    }

    public void registrarPedido(Pedido pedido)   {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);

        pedido.setData(date);
        pedido.setValorTotal(new BigDecimal("123"));
        pedido.setStatusPedido(StatusPedido.EM_ESPERA);
        pedidoDAO.inserirPedido(pedido);
    }

    public List<Pedido> listarPedidosC(long idCliente){
        return pedidoDAO.getPedidosPorIdCliente(idCliente);
    }

    public List<Pedido> listarPedidosE(Entregador entregador){
        return pedidoDAO.getPedidosPorIdEntregador(entregador.getIdEntregador());
    }

    public List<Pedido> listarPedidosR(Restaurante restaurante){
        return pedidoDAO.getPedidosPorIdRestaurante(restaurante.getIdRestaurante());
    }
}