package Databases_modelo;

import Databases_dao.DAOException;
import Databases_dao.Hibernate_DAO.HibernateDaoManager;
import Databases_dao.MySQLDAOManager;
import enums.ClienteTipo;

import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ArrayList;

public class Datos {
    private MySQLDAOManager mySQLDAOManager;
    private HibernateDaoManager hibernateDaoManager;
    private ArrayList<Articulo> listaArticulos;
    private ArrayList<Cliente> listaClientes;
    private ListaPedidos listaPedidos;

    public Datos() throws DAOException, SQLException {
        this.mySQLDAOManager = new MySQLDAOManager();
        this.hibernateDaoManager = new HibernateDaoManager();
        listaClientes = readDBClientes();
        listaArticulos = readDBArticulos();
        listaPedidos = new ListaPedidos();

        //cargarDatos();
    }

    //**** Funciones BBDD Cliente

    public ArrayList<Cliente> getClientes(){return this.listaClientes;}

    public ArrayList<Cliente> readDBClientes() throws DAOException{
        return hibernateDaoManager.getClienteDAO().readAll();
    }

    public ArrayList<Articulo> readDBArticulos() throws DAOException{
        return mySQLDAOManager.getArticuloDAO().readAll();
    }

    public ArrayList<Cliente> getPremiumCustomers(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for (Cliente listaCliente : this.listaClientes) {
            if (listaCliente.getTipo() == ClienteTipo.PREMIUM) {
                clientes.add(listaCliente);
            }
        }
        return clientes;
    }

    public ArrayList<Cliente> getStandardCustomers(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for (Cliente listaCliente : this.listaClientes) {
            if (listaCliente.getTipo() == ClienteTipo.ESTANDARD) {
                clientes.add(listaCliente);
            }
        }
        return clientes;
    }

    public void customerAdd(Cliente cliente) throws Exception {
        try {
            this.hibernateDaoManager.getClienteDAO().create(cliente);
            this.listaClientes = readDBClientes();
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean customerExists(String email){
        boolean existe = false;
        for (Cliente listaCliente : this.listaClientes) {
            if (email.equals(listaCliente.getEmail())) {
                existe = true;
            }
        }
        return existe;
    }

    public int customerLength(){
        return this.listaClientes.size();
    }


   /* public void cargarDatos(){
        Pedido pedido1 = new Pedido(listaClientes.get(0),listaArticulos.get(0),  20);
        try {
            listaPedidos.add(pedido1);
            Pedido pedido2 = new Pedido(listaClientes.get(1),listaArticulos.get(2),  25);
            listaPedidos.add(pedido2);
            Pedido pedido3 = new Pedido(listaClientes.get(2),listaArticulos.get(1),  10);
            listaPedidos.add(pedido3);
            Pedido pedido4 = new Pedido(listaClientes.get(3),listaArticulos.get(0),  5);
            listaPedidos.add(pedido4);
        }catch (Exception e) {
            System.out.println("La carga de datos ha fallado");
        }
    }*/

    /*
     *** Articulos ***
     */

    public boolean articleExists(String artCod){
        boolean existe = false;
        for (Articulo listaArticulo : this.listaArticulos) {
            if (artCod.equals(listaArticulo.getCodigoArticulo())) {
                existe = true;
            }
        }
        return existe;
    }

    public ArrayList<Articulo> getArticles() {
      return this.listaArticulos;
    }

    public void articleAdd(Articulo articulo) throws Exception {
        try {
            this.mySQLDAOManager.getArticuloDAO().create(articulo);
            this.listaArticulos = readDBArticulos();
        } catch (Exception e) {
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
