package datos;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import entidades.Cliente;

public class CatalogoClientes extends CatalogoBase
{	
	public static Cliente buscarCliente(int idCliente)
	{
		abrirEntityManager();
		try
		{
			return getEm().find(Cliente.class, idCliente);
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	public static void guardarCliente(Cliente cliente)
	{
		abrirEntityManager();
		try
		{
			getEm().getTransaction().begin();
			
			if(cliente.getId() == 0)
			{
				getEm().persist(cliente);
			}
			else
			{
				Cliente dbCliente = getEm().find(Cliente.class, cliente.getId());
				
				if (dbCliente != null)
				{
					dbCliente.setDireccion(cliente.getDireccion());
					dbCliente.setNombreApellido(cliente.getNombreApellido());
					dbCliente.setTelefono(cliente.getTelefono());		
				}
			}
			
			getEm().getTransaction().commit();
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Cliente> buscarTodosClientes()
	{
		abrirEntityManager();
		try
		{
			return (ArrayList<Cliente>)getEm().createQuery("SELECT c FROM Cliente c").getResultList();
		}
		finally
		{
			cerrarEntityManager();
		}
	}
}


