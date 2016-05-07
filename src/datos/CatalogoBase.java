package datos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CatalogoBase 
{
	private static EntityManagerFactory emf;	
	private static EntityManager em;

	public static void abrirEntityManager()
	{
		emf = Persistence.createEntityManagerFactory("ServicioRopa");
		em = emf.createEntityManager();
	}
	
	public static void cerrarEntityManager()
	{
		if (em.isOpen()) 
		    em.close();
		
		if (emf.isOpen())
			emf.close();
	}
	
	public static EntityManagerFactory getEmf() 
	{
		return emf;
	}
	public static void setEmf(EntityManagerFactory emf) 
	{
		CatalogoBase.emf = emf;
	}
	public static EntityManager getEm() 
	{
		return em;
	}
	public static void setEm(EntityManager em) 
	{
		CatalogoBase.em = em;
	}
}
