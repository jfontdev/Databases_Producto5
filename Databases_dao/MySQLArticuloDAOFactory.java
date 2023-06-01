package Databases_dao;

import java.sql.Connection;

public class MySQLArticuloDAOFactory implements ArticuloFactoryDAO {

    @Override
    public ArticuloDAO createDAO(Connection conexion) {

        return new MySQLArticuloDAO(conexion);
    }
}
