package datos;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Usuario;

public class CatalogoUsuarios 
{
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServicioRopa");
	private static EntityManager em = emf.createEntityManager();
	
	public static Usuario buscarUsuario(String nombreUsuario)
	{
		return em.find(Usuario.class, nombreUsuario);
	}
	
	public static void guardarUsuario(Usuario usuario)
	{
		Usuario dbUsuario = em.find(Usuario.class, usuario.getUsuario());
		if(dbUsuario == null)
		{
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		}
		else
		{
			em.getTransaction().begin();

			dbUsuario.setEmail(usuario.getEmail());
			dbUsuario.setNombreYApellido(usuario.getNombreYApellido());
			dbUsuario.setPassword(usuario.getPassword());
			dbUsuario.setTipoUsuario(usuario.getTipoUsuario());
			
			em.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Usuario> buscarTodosUsuarios()
	{
		return (ArrayList<Usuario>)em.createQuery("SELECT u FROM Usuario u").getResultList();
	}
}
