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

    public void listarPedidos() {
        final ArrayList<Pedido> pedidos = datos.getOrders();
        this.pedidoVista.renderAll(pedidos);
    }

    public void listarPedidosEnviados() {
        ArrayList<Pedido> pedidos = datos.getPedidosEnviados();
        this.pedidoVista.renderAll(pedidos);
    }

    public void listarPedidosPendientes() {
        ArrayList<Pedido> pedidos = datos.getPedidosPendientes();
        this.pedidoVista.renderAll(pedidos);
    }

    public boolean pedidoExists(String numeroPedido) {
        return this.datos.pedidoExists(numeroPedido);
    }


    public void crearPedido(Pedido pedido){
        try {
            this.datos.pedidoAdd(pedido);
            this.pedidoVista.render(pedido);
        }catch (Exception e){
            this.pedidoVista.error(e);
        }
    }

    public void borrar(Pedido pedido){datos.borrarPedido(pedido);}

    public Pedido mostrarPedido(String id){
        Pedido pedido = null;
        for (int i = 0; i < this.datos.longitudPedidos(); i++){
            if (id.equals(this.datos.getOrders().get(i).getNumeroPedido())){
                pedido = this.datos.getOrders().get(i);
            }
        }
        return pedido;
    }

}








