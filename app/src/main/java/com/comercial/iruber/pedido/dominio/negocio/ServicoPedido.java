package com.comercial.iruber.pedido.dominio.negocio;

import android.content.Context;

import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.IruberException;
import com.comercial.iruber.pedido.dominio.Pedido;
import com.comercial.iruber.pedido.dominio.persistencia.PedidoDAO;
import com.comercial.iruber.restaurante.dominio.Entregador;
import com.comercial.iruber.restaurante.dominio.Restaurante;

import java.util.List;

public class ServicoPedido {
    private PedidoDAO pedidoDAO;

    public ServicoPedido(Context context){
        this.pedidoDAO = new PedidoDAO(context);
    }

    public void updatePedido(Pedido pedido) throws IruberException {
        pedidoDAO.updatePedido(pedido);
    }

    public void registrarPedido(Pedido pedido) throws IruberException {
        pedidoDAO.inserirPedido(pedido);
    }

    public List<Pedido> listarPedidos(Cliente cliente) throws Exception {
        return pedidoDAO.getPedidosPorIdCliente(cliente.getIdCliente());
    }

    public List<Pedido> listarPedidos(Entregador entregador) throws Exception {
        return pedidoDAO.getPedidosPorIdEntregador(entregador.getIdEntregador());
    }

    public List<Pedido> listarPedidos(Restaurante restaurante) throws Exception {
        return pedidoDAO.getPedidosPorIdRestaurante(restaurante.getIdRestaurante());
    }
}