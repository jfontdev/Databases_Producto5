package Databases_dao;

import java.util.ArrayList;

public interface DAO<T,K>{

    public void create(T insertado) throws DAOException;

    public T readOne(K id) throws DAOException;

    public ArrayList<T> readAll() throws DAOException;

    public void update(T modificado) throws DAOException;

    public void delete(T eliminado) throws DAOException;
}
