package Databases_dao.Hibernate_DAO;

import Databases_dao.ArticuloDAO;
import Databases_dao.DAOException;
import Databases_modelo.Articulo;
import Databases_modelo.Cliente;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;


public class HibernateArticuloDao implements ArticuloDAO {

    @Override
    public void create(Articulo insertado) throws DAOException {
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();
            session.save(insertado);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.err.println("Hibernate Articulo Dao ha fallado" + exception.getMessage());
        } finally {
            HibernateUtil.closeSessionFactory();
        }
    }

    @Override
    public Articulo readOne(String id) throws DAOException {
        Articulo articulo = null;
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            articulo = (Articulo) session.get(Articulo.class, id);
        } catch (Exception exception) {
            System.err.println("Hibernate Articulo Dao ha fallado" + exception.getMessage());
        } finally {
            HibernateUtil.closeSessionFactory();
        }
        return articulo;
    }

    @Override
    public ArrayList<Articulo> readAll() throws DAOException {
        ArrayList<Articulo> articulos = null;
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            String hibernate = "FROM Articulo";
            Query query = session.createQuery(hibernate);
            articulos = (ArrayList<Articulo>) query.list();
        } catch (Exception exception) {
            System.err.println("Hibernate Articulo Dao ha fallado" + exception.getMessage());
        } finally {
            HibernateUtil.closeSessionFactory();
        }
        return articulos;
    }


    public void update(Articulo modificado) throws DAOException {
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();
            session.update(modificado);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.err.println("Hibernate Articulo Dao ha fallado" + exception.getMessage());
        } finally {
            HibernateUtil.closeSessionFactory();
        }
    }


    @Override
    public void delete(Articulo eliminado) throws DAOException {
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            session.delete(eliminado);
            session.getTransaction().commit();
        } catch (Exception exception) {
            System.err.println("Hibernate Articulo Dao ha fallado" + exception.getMessage());
        } finally {
            HibernateUtil.closeSessionFactory();
        }
    }

}







