package Databases_dao.Hibernate_DAO;

import Databases_dao.ArticuloFactoryDAO;
import Databases_dao.ArticuloDAO;
import Databases_dao.ArticuloFactoryDAO;

import java.sql.Connection;

public class HibernateDaoArticuloFactory implements ArticuloFactoryDAO {
    @Override
    public ArticuloDAO createDAO(Connection conexion) {return new HibernateArticuloDao();}
}
