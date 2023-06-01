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
    private final HibernateDaoManager hibernateDaoManager;
    private ArrayList<Articulo> listaArticulos;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Pedido> listaPedidos;

    public Datos() throws DAOException, SQLException {
//        this.mySQLDAOManager = new MySQLDAOManager();
        this.hibernateDaoManager = new HibernateDaoManager();
        listaClientes = readDBClientes();
        listaArticulos = readDBArticulos();
        listaPedidos = readDBPedidos();

        //cargarDatos();
    }

    //**** Funciones BBDD Cliente

    public ArrayList<Cliente> getClientes(){return this.listaClientes;}

    public ArrayList<Cliente> readDBClientes() throws DAOException{
        return hibernateDaoManager.getClienteDAO().readAll();
    }

    public ArrayList<Articulo> readDBArticulos() throws DAOException{
        return hibernateDaoManager.getArticuloDAO().readAll();
    }

    public ArrayList<Pedido> readDBPedidos() throws DAOException{
        return hibernateDaoManager.getPedidoDAO().readAll();
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

    public void customerUpdate(Cliente cliente) throws DAOException{
        try {
            this.hibernateDaoManager.getClienteDAO().update(cliente);
            this.listaClientes = readDBClientes();
        } catch (DAOException e) {
            throw  e;
        }
    }

    public void deleteCustomer(Cliente cliente) throws DAOException {
        this.hibernateDaoManager.getClienteDAO().delete(cliente);
        this.listaClientes = readDBClientes();
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

  public void articleAdd(Articulo articulo) throws Exception {
      try{
          this.hibernateDaoManager.getArticuloDAO().create(articulo);
          this.listaArticulos = readDBArticulos();
      }catch (Exception e) {
          throw e;
      }
  }

  public boolean articleExists(String codigoArticulo){
      boolean existe = false;
      for (Articulo listaArticulo : this.listaArticulos){
          if (codigoArticulo.equals(listaArticulo.getCodigoArticulo())){
              existe = true;
          }
      }
      return existe;
  }
    public void articleUpdate(Articulo articulo) throws DAOException{
        try {
            this.hibernateDaoManager.getArticuloDAO().update(articulo);
            this.listaArticulos = readDBArticulos();
        } catch (DAOException e) {
            throw  e;
        }
    }
    public void deleteArticle(Articulo articulo) throws DAOException {
        this.hibernateDaoManager.getArticuloDAO().delete(articulo);
        this.listaArticulos = readDBArticulos();
    }


    public int articleLength(){
      return this.listaArticulos.size();
}

public ArrayList<Articulo> getArticles() {
        return this.listaArticulos;
    }



    /*
     *********Pedidos***********
     */

    public ArrayList<Pedido> getOrders() {
        return this.listaPedidos;
    }


    public ArrayList<Pedido> getPedidosEnviados(){
        ArrayList<Pedido> pedidosEnviados = new ArrayList<Pedido>();
        for (Pedido listaPedidos : this.listaPedidos){
            if (listaPedidos.pedidoEnviado()){
                pedidosEnviados.add(listaPedidos);
            }
        }
        return pedidosEnviados;
    }

    public ArrayList<Pedido> getPedidosPendientes(){
        ArrayList<Pedido> pedidosPendientes = new ArrayList<Pedido>();

        for (Pedido listaPedidos : this.listaPedidos){
            if (!listaPedidos.pedidoEnviado()){
                pedidosPendientes.add(listaPedidos);
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

    public void borrarPedido(Pedido pedido){listaPedidos.remove(pedido);}

    public int longitudPedidos() {return listaPedidos.size();}

}
