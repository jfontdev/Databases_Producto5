package Databases_vista;

import Databases_modelo.Pedido;

import java.util.ArrayList;

public class PedidoVista {
    public void render(Pedido pedido){
        System.out.println("El pedido " + pedido.getNumeroPedido() + " se ha realizado correctamente");
    }

    public void renderAll(ArrayList<Pedido> pedidos){
        for (Pedido pedido : pedidos){
            System.out.println(pedido.getNumeroPedido() + " " + pedido.getCantidad() + " " + pedido.getFechaPedido() + " " + pedido.getArt() + " " + pedido.getCl() );
        }
    }

    public void error(Exception exception){
        System.out.println("Ha ocurrido un error en pedidos");
    }


}
