package Databases_vista;

import Databases_modelo.Cliente;

import java.util.ArrayList;

public class ClienteVista {

    public void render(Cliente cliente){
        System.out.println("El cliente " + cliente.getNombre() + " " + cliente.getApellidos() + " se ha creado");
    }

    public void renderAll(ArrayList<Cliente> clientes){
        for (Cliente cliente : clientes){
            System.out.println(cliente.getNombre() + " " + cliente.getApellidos() + " " + cliente.getTipo() + " " + cliente.getTasaCliente() + " " + cliente.getDescuentoCliente() );
        }
    }

    public void error(Exception exception){
        System.out.println("Ha ocurrido un error en clientes");
    }
}
