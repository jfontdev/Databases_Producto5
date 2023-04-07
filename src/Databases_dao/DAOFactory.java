package Databases_dao;

import java.sql.Connection;

public interface DAOFactory<T> {
    T createDAO(Connection conexion);
}
