package datos;

import java.util.ArrayList;

import org.hibernate.Session;

import entidades.Usuario;

public class CatalogoUsuarios extends CatalogoBase
{
	public static Usuario buscarUsuario(String nombreUsuario)
	{
		abrirEntityManager();
		try
		{
			return getEm().find(Usuario.class, nombreUsuario);
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	public static void eliminarUsuario(String usuario)
	{
		abrirEntityManager();
		getEm().getTransaction().begin();
		try
		{
			Usuario us = getEm().find(Usuario.class, usuario);
			us.setTipoUsuario(0);
			getEm().getTransaction().commit();
		}
		finally
		{
			//getEm().getTransaction().rollback();
			cerrarEntityManager();
		}
	}
	
	public static void guardarUsuario(Usuario usuario)
	{
		abrirEntityManager();
		try
		{
			getEm().getTransaction().begin();
			Usuario dbUsuario = getEm().find(Usuario.class, usuario.getUsuario());
			
			if(dbUsuario == null)
				getEm().persist(usuario);
			else
			{
				dbUsuario.setEmail(usuario.getEmail());
				dbUsuario.setNombreYApellido(usuario.getNombreYApellido());
				dbUsuario.setPassword(usuario.getPassword());
				dbUsuario.setTipoUsuario(usuario.getTipoUsuario());
			}
			getEm().getTransaction().commit();
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Usuario> buscarTodosUsuarios()
	{
		abrirEntityManager();
		try
		{
			return (ArrayList<Usuario>)getEm().createQuery("SELECT u FROM Usuario u where u.tipoUsuario > 0").getResultList();
		}
		finally
		{
			cerrarEntityManager();
		}
	}
}

