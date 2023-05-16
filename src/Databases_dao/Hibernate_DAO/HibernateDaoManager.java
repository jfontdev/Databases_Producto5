package Databases_dao.Hibernate_DAO;

import Databases_dao.ArticuloDAO;
import Databases_dao.ClienteDAO;
import Databases_dao.DAOManager;
import Databases_dao.PedidoDAO;

public class HibernateDaoManager implements DAOManager {
    private ClienteDAO clienteDAO = null;
    private ArticuloDAO articuloDAO;
    private PedidoDAO pedidoDAO = null;

    public HibernateDaoManager(){
        clienteDAO = new HibernateClienteDao();
        articuloDAO = null;
        pedidoDAO = new HibernatePedidoDao();

    }
    @Override
    public ClienteDAO getClienteDAO(){ return clienteDAO;}

    @Override
    public ArticuloDAO getArticuloDAO(){ return articuloDAO;}

    @Override
    public PedidoDAO getPedidoDAO(){ return pedidoDAO;}

}
