package Databases_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOManager implements DAOManager{
    private Connection conexion;

    private ClienteDAO clienteDAO = null;
    private ArticuloDAO articuloDAO = null;

    private MySQLClienteDAOFactory clienteDAOFactory;
    private MySQLArticuloDAOFactory articuloDAOFactory;


    public MySQLDAOManager() throws SQLException{
        this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3","Databases","Databases");
        clienteDAOFactory = new MySQLClienteDAOFactory();
        articuloDAOFactory = new MySQLArticuloDAOFactory();
    }

    @Override
    public ClienteDAO getClienteDAO() {
        if(clienteDAO == null ){
            clienteDAO = clienteDAOFactory.createDAO(conexion);
        }
        return clienteDAO;
    }

    @Override
    public ArticuloDAO getArticuloDAO() {
        if(articuloDAO == null ){
            articuloDAO = articuloDAOFactory.createDAO(conexion);
        }
        return articuloDAO;
    }

}
