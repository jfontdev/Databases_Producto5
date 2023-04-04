package Databases_modelo;

import enums.ClienteTipo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Datos {
    private ListaArticulos listaArticulos;
    private ListaClientes listaClientes;
    private ListaPedidos listaPedidos;

    public Datos(){
        listaArticulos = new ListaArticulos();
        listaClientes = new ListaClientes();
        listaPedidos = new ListaPedidos();

        cargarDatos();
    }

    public void cargarDatos(){
        Cliente clientePremium1 = new ClientePremium("Jordi","Font","C/Rocafort 125","12345678A","jordi@gmail.com",ClienteTipo.PREMIUM);
        try {
            listaClientes.add(clientePremium1);
            ClientePremium clientePremium2 = new ClientePremium("Abel","Gimenez","C/Calabria 134","98763121B","abel@gmail.com",ClienteTipo.PREMIUM);
            listaClientes.add(clientePremium2);
            ClienteEstandard clienteEstandard1 = new ClienteEstandard("David","Gonzalez","C/Cantabria 92","12356732C","david@gmail.com",ClienteTipo.ESTANDARD);
            listaClientes.add(clienteEstandard1);
            ClienteEstandard clienteEstandard2 = new ClienteEstandard("Marta","Rodriguez","C/Mallorca 22","6355232D","marta@gmail.com",ClienteTipo.ESTANDARD);
            listaClientes.add(clienteEstandard2);
        }catch (Exception e){
            System.out.println("La carga de datos ha fallado");
        }

    }

    public ArrayList<Cliente> getCustomers() {
        return this.listaClientes.getArrayList();
    }

    public ArrayList<Cliente> getPremiumCustomers(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        for (int i=0; i < this.listaClientes.getSize(); i++){
            if (this.listaClientes.getAt(i).getTipo() == ClienteTipo.PREMIUM){
                clientes.add(this.listaClientes.getAt(i));
            }
        }
        return clientes;
    }

    public ArrayList<Cliente> getStandardCustomers(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        for (int i=0; i < this.listaClientes.getSize(); i++){
            if (this.listaClientes.getAt(i).getTipo() == ClienteTipo.ESTANDARD){
                clientes.add(this.listaClientes.getAt(i));
            }
        }
        return clientes;
    }

    public void customerAdd(Cliente cliente) throws Exception {
        try {
            this.listaClientes.add(cliente);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean customerExists(String email){
        return this.listaClientes.contains(email);
    }

    public int customerLength(){
        return this.listaClientes.getSize();
    }

    /*
     *Pedidos
     */

    public ArrayList<Pedido> getOrders() {
        return this.listaPedidos.getArrayList();
    }
    public String verPedidos() {
        return listaPedidos.toString();
    }

    public void orderAdd(int cantidad, String numeroPedido, LocalDateTime now, String nif, String codigoArticulo) {
        //int posicion = -1;
        Articulo art = null;
        Cliente cli = null;
        for (Articulo aux : listaArticulos.getArrayList()) {
            if (aux.getCodigo().equals(codigoArticulo)) {
                //posicion = listaArticulos.getArrayList().indexOf(aux);
                art = aux;
                break;
            }
        }


    }

}
