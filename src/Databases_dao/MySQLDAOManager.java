package Databases_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOManager implements DAOManager{
    private Connection conexion;

    private ClienteDAO clienteDAO = null;

    private MySQLClienteDAOFactory clienteDAOFactory;



    public MySQLDAOManager() throws SQLException{
        this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Producto3","Databases","Databases");
        clienteDAOFactory = new MySQLClienteDAOFactory();
    }

    @Override
    public ClienteDAO getClienteDAO() {
        if(clienteDAO == null ){
            clienteDAO = clienteDAOFactory.createDAO(conexion);
        }
        return clienteDAO;
    }

}
