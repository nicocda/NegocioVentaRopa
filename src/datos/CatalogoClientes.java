package datos;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import entidades.Cliente;
import entidades.Producto;
import excepciones.RespuestaServidor;

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
	
	public static void guardarCliente(Cliente cliente) throws RespuestaServidor
	{
		RespuestaServidor rs = validarCliente(cliente);
		if (!rs.getStatus())
		throw rs;
		
		abrirEntityManager();
		try
		{
			getEm().getTransaction().begin();
			
			if(cliente.getId() == 0)
				getEm().persist(cliente);
			else
			{
				Cliente dbCliente = getEm().find(Cliente.class, cliente.getId());
				
				if (dbCliente != null)
				{
					dbCliente.setDireccion(cliente.getDireccion());
					dbCliente.setNombre(cliente.getNombre());
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
	
	private static RespuestaServidor validarCliente(Cliente cl) 
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if((cl.getId() < 0))
			sr.addError("Ocurrió un error interno. El id es obligatorio.");
		
		if(!(cl.getNombre() != null && !cl.getNombre().isEmpty()))
			sr.addError("El nombre del cliente es obligatorio.");
		
		return sr;
	}
}


