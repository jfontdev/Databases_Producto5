package Databases_dao.Hibernate_DAO;

import Databases_dao.ClienteDAO;
import Databases_dao.ClienteFactoryDAO;

import java.sql.Connection;

public class HibernateDaoClienteFactory implements ClienteFactoryDAO {
    @Override
    public ClienteDAO createDAO(Connection conexion) {return new HibernateClienteDao();}
}
