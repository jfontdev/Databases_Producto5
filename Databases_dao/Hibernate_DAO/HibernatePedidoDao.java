package Databases_dao.Hibernate_DAO;

import Databases_dao.DAOException;
import Databases_dao.PedidoDAO;
import Databases_modelo.Pedido;
import Databases_modelo.Cliente;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

// hay que implementar los metodos de PedidoDAO y quitar que la clase sea abstract
public class HibernatePedidoDao implements PedidoDAO {

    @Override
    public void create(Pedido insertado) throws DAOException {

        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();
            session.save(insertado);
            session.getTransaction().commit();
        }catch (Exception exception){
            System.err.println("Hibernate pedido Dao ha fallado." + exception.getMessage());
        }finally {
            HibernateUtil.closeSessionFactory();
        }
    }


    @Override
    public Pedido readOne(String id) throws DAOException {
        Pedido pedido = null;
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            pedido = (Pedido) session.get(Pedido.class,id);
        }catch (Exception exception){
            System.err.println("Hibernate pedido Dao ha fallado." + exception.getMessage());
        }finally {
            HibernateUtil.closeSessionFactory();
        }

        return null;
    }

    @Override
    public ArrayList<Pedido> readAll() throws DAOException {
        ArrayList<Pedido> pedidos = null;
        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            String hibernate = "FROM Pedido";
            Query query = session.createQuery(hibernate);
            pedidos = (ArrayList<Pedido>) query.list();
        }catch (Exception exception){
            System.err.println("Hibernate pedido Dao ha fallado." + exception.getMessage());
        }finally {
            HibernateUtil.closeSessionFactory();
        }
        return  pedidos;

    }

    @Override
    public void update(Pedido modificado) throws DAOException {

        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();
            session.update(modificado);
            session.getTransaction().commit();
        }catch (Exception exception){
            System.err.println("Hibernate pedido Dao ha fallado." + exception.getMessage());
        }finally {
            HibernateUtil.closeSessionFactory();
        }
    }

    @Override
    public void delete(Pedido eliminado) throws DAOException {

        try {
            HibernateUtil.buildSessionFactory();
            Session session = HibernateUtil.getCurrentSession();
            session.delete(eliminado);
            session.getTransaction().commit();
        }catch (Exception exception){
            System.err.println("Hibernate pedido Dao ha fallado." + exception.getMessage());
        }finally {
            HibernateUtil.closeSessionFactory();
        }

    }
}
