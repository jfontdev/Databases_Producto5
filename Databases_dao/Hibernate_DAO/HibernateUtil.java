package Databases_dao.Hibernate_DAO;


import Databases_modelo.Articulo;
import Databases_modelo.Cliente;
import Databases_modelo.Pedido;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory  sessionFactory;
    private static Session session;

    public  static void buildSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Cliente.class);
        configuration.addAnnotatedClass(Articulo.class);
        configuration.addAnnotatedClass(Pedido.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        try {
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }catch (HibernateException exception){
            System.err.println("No ha sido posible crear la sessionFactory" + exception);
        }
    }

    private static void openSession(){

        session = sessionFactory.openSession();
    }

    public static  Session getCurrentSession(){
        if((session == null) || !session.isOpen()){
            openSession();
        }
        return  session;
    }
    public static  void closeSessionFactory(){
        if(session != null)
            session.close();

        if(sessionFactory != null)
            sessionFactory.close();
    }
}
