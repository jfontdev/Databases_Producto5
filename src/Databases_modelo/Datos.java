package Databases_modelo;

import enums.ClienteTipo;

import java.time.LocalDate;

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
        Articulo articulo1 = new Articulo("1", "Nevera", 499.99f, 19.99f, 3);
        try {
          listaArticulos.add(articulo1);
          Articulo articulo2 = new Articulo("2", "Batidora", 500.90f, 7.00f, 1);
          listaArticulos.add(articulo2);
          Articulo articulo3 = new Articulo("3", "Microondas", 56.99f, 45.89f, 2);
          listaArticulos.add(articulo3);
          Articulo articulo4 = new Articulo("4", "Monitor", 34.67f, 34.89f, 1);
          listaArticulos.add(articulo4);
        }catch (Exception e){
          System.out.println("La carga de datos ha fallado");
        }
        Pedido pedido1 = new Pedido(listaClientes.getAt(0),listaArticulos.getAt(0),  20);
        try {
            listaPedidos.add(pedido1);
            Pedido pedido2 = new Pedido(listaClientes.getAt(1),listaArticulos.getAt(2),  25);
            listaPedidos.add(pedido2);
            Pedido pedido3 = new Pedido(listaClientes.getAt(2),listaArticulos.getAt(1),  10);
            listaPedidos.add(pedido3);
            Pedido pedido4 = new Pedido(listaClientes.getAt(3),listaArticulos.getAt(0),  5);
            listaPedidos.add(pedido4);
        }catch (Exception e) {
            System.out.println("La carga de datos ha fallado");
        }
    }




    public ArrayList<Cliente> getCustomers() {
        return this.listaClientes.getArrayList();
    }

    public ListaArticulos getArticulos() {
        return this.listaArticulos;
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

    public boolean articleExists(String artCod){
        return this.listaArticulos.contains(artCod);
    }

    public int customerLength(){
        return this.listaClientes.getSize();
    }

    public ArrayList<Articulo> getArticles() {
      return this.listaArticulos.getArrayList();
    }
    public void articleAdd(Articulo articulo) throws Exception {
      try{
        this.listaArticulos.add(articulo);
      }catch (Exception e){
        throw e;
      }
    }

    /*
     *********Pedidos***********
     */

    public ArrayList<Pedido> getOrders() {
        return this.listaPedidos.getArrayList();
    }


    public ArrayList<Pedido> getPedidosEnviados(){
        ArrayList<Pedido> pedidosEnviados = new ArrayList<Pedido>();
        for (int i = 0; i < this.listaPedidos.getSize(); i++){
            if (this.listaPedidos.getAt(i).pedidoEnviado()){
                pedidosEnviados.add(this.listaPedidos.getAt(i));
            }
        }
        return pedidosEnviados;
    }

    public ArrayList<Pedido> getPedidosPendientes(){
        ArrayList<Pedido> pedidosPendientes = new ArrayList<Pedido>();

        for (int i = 0; i < this.listaPedidos.getSize(); i++){
            if (!this.listaPedidos.getAt(i).pedidoEnviado()){
                pedidosPendientes.add(this.listaPedidos.getAt(i));
            }
        }
        return pedidosPendientes;
    }


    public boolean pedidoExists(String numPedido){
        return this.listaPedidos.contains(numPedido);
    }


    public void pedidoAdd(Pedido pedido) throws Exception {
        try{
            this.listaPedidos.add(pedido);
        }catch (Exception e){
            throw e;
        }
    }

    public void borrarPedido(Pedido pedido){this.listaPedidos.delete(pedido);}

    public int longitudPedidos() {return this.listaPedidos.getSize();}

}
