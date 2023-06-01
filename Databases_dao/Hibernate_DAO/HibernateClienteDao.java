package Databases_dao.Hibernate_DAO;

import Databases_dao.ClienteDAO;
import Databases_dao.DAOException;
import Databases_modelo.Cliente;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;


public class HibernateClienteDao implements ClienteDAO {

    @Override
    public void create(Cliente insertado) throws DAOException{
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();
            session.save(insertado);
            session.getTransaction().commit();
        }catch (Exception exception){
            System.err.println("Hibernate cliente Dao ha fallado." + exception.getMessage());
        }finally {
            HibernateUtil.closeSessionFactory();
        }
    }

    @Override
    public Cliente readOne(String id) throws DAOException{
        Cliente cliente = null;
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            cliente = (Cliente) session.get(Cliente.class,id);
        }catch (Exception exception){
            System.err.println("Hibernate cliente Dao ha fallado." + exception.getMessage());
        }finally {
            HibernateUtil.closeSessionFactory();
        }
        return cliente;
    }

    @Override
    public ArrayList<Cliente> readAll() throws DAOException{
        ArrayList<Cliente> clientes = null;
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            String hibernate = "FROM Cliente";
            Query query = session.createQuery(hibernate);
            clientes = (ArrayList<Cliente>) query.list();
        }catch (Exception exception){
            System.err.println("Hibernate cliente Dao ha fallado." + exception.getMessage());
        }finally {
            HibernateUtil.closeSessionFactory();
        }
        return  clientes;
    }

    @Override
    public void update(Cliente modificado)throws DAOException{
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();
            session.update(modificado);
            session.getTransaction().commit();
        }catch (Exception exception){
            System.err.println("Hibernate cliente Dao ha fallado." + exception.getMessage());
        }finally {
            HibernateUtil.closeSessionFactory();
        }
    }

    @Override
    public void delete(Cliente eliminado) throws DAOException{
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();
            session.delete(eliminado);
            session.getTransaction().commit();
        }catch (Exception exception){
            System.err.println("Hibernate cliente Dao ha fallado." + exception.getMessage());
        }finally {
            HibernateUtil.closeSessionFactory();
        }
    }
}
