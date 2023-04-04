package Databases_controlador;

import Databases_modelo.Pedido;
import Databases_modelo.Cliente;
import Databases_modelo.Datos;
import Databases_vista.ClienteVista;
import Databases_vista.PedidoVista;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class PedidosControlador {

    private Datos datos;
    private PedidoVista pedidoVista;

    public PedidosControlador(Datos datos) {
        this.datos = datos;
        this.pedidoVista = new PedidoVista();
    }

    public void OrderList() {
        final ArrayList<Pedido> pedidos = datos.getOrders();
        this.pedidoVista.renderAll(pedidos);
    }

    public void createorder(Pedido pedido){
        try {
            this.datos.(pedido);
            this.pedidoVista.render(pedido);
        }catch (Exception e){
            pedidoVista.error(e);
        }
    }


}








