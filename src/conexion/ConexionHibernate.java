package conexion;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexionHibernate
{
	 private static final SessionFactory sessionFactory;   

	    static 
	    { 
	        try 
	        { 
	            sessionFactory = new Configuration().configure().buildSessionFactory(); 
	        } 
	        catch (HibernateException he) 
	        { 
	           System.err.println("Ocurri� un error en la inicializaci�n de la SessionFactory: " + he); 
	           throw new ExceptionInInitializerError(he); 
	        } 
	    }  

	    public static SessionFactory getSessionFactory() 
	    { 
	        return sessionFactory; 
	    } 
}
