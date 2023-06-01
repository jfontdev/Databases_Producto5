package Databases_dao;

import java.sql.Connection;

public class MySQLClienteDAOFactory implements ClienteFactoryDAO {

    @Override
    public ClienteDAO createDAO(Connection conexion) {

        return new MySQLClienteDAO(conexion);
    }
}
