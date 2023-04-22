package Databases_dao;

import java.sql.Connection;

public class MySQLPedidoDAOFactory implements PedidoFactoryDAO {

    @Override
    public PedidoDAO createDAO(Connection conexion) {

        return new MySQLPedidoDAO(conexion);
    }
}
