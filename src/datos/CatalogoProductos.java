package datos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Cliente;
import entidades.Precio;
import entidades.Producto;
import entidades.Producto.estado;
import excepciones.RespuestaServidor;

public class CatalogoProductos extends CatalogoBase
{
	public static Producto buscarProducto(String idProducto)
	{
		abrirEntityManager();
		
		try
		{
			Producto producto = getEm().find(Producto.class, idProducto);
			if (producto != null)
				buscarUltimoPrecio(producto);
			
			return producto;
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	public static void guardarProducto(Producto producto) throws RespuestaServidor
	{
		RespuestaServidor rs = validarProducto(producto);
		if (!rs.getStatus())
			throw rs;
		
		abrirEntityManager();
		try
		{
			Producto dbProducto = getEm().find(Producto.class, producto.getId());
			
			getEm().getTransaction().begin();
			
			if(dbProducto == null)
				getEm().persist(producto);
			else
				getEm().merge(producto);
			
			getEm().getTransaction().commit();
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	

	@SuppressWarnings("unchecked")
	public static ArrayList<Producto> buscarTodosProductos()
	{
		abrirEntityManager();
		try
		{
			ArrayList<Producto> todosProductos = (ArrayList<Producto>)getEm().createQuery("SELECT p FROM Producto p").getResultList();
			
			for(Producto p : todosProductos)
				buscarUltimoPrecio(p);
	
			return todosProductos;
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Producto> buscarTodosProductosEnStock()
	{
		abrirEntityManager();
		try
		{
			ArrayList<Producto> todosProductos = (ArrayList<Producto>)getEm().createQuery("SELECT p FROM Producto p").getResultList();
			
			for(Producto p : todosProductos)
				buscarUltimoPrecio(p);
	
			return todosProductos;
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	public static String buscarUltimoIdProducto(char tipo, char subtipo)
	{
		abrirEntityManager();
		
		try
		{
			String parametro = Character.toString(tipo) + Character.toString(subtipo) + "%";
			
			List query = getEm().createQuery("SELECT p.id FROM Producto p WHERE p.id like (:tiposubtipo) ORDER BY p.id DESC").setMaxResults(1).setParameter("tiposubtipo", parametro).getResultList();
			
			String resultado = null;
			if(!query.isEmpty())
				resultado = (String)query.get(0);

			return resultado;
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	private static void buscarUltimoPrecio(Producto producto)
	{
		producto.setPrecio(producto.getPrecios().get(0));
		for(Precio pr : producto.getPrecios())
		{
			if(producto.getPrecio().getFecha().before(pr.getFecha()))
				producto.setPrecio(pr);
		}
	}
	
	private static RespuestaServidor validarProducto(Producto producto) 
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if(!(producto.getId() != null && !producto.getId().isEmpty()))
			sr.addError("Ocurrió un error interno. El id es obligatorio.");
		
		if(!(producto.getDescripcion() != null && !producto.getDescripcion().isEmpty()))
			sr.addError("El producto debe tener una descripción.");
		
		if(producto.getPrecio() != null && producto.getPrecio().getPrecio() < 0)
			sr.addError("El precio ingresado no es válido.");
		return sr;
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Producto> buscarProductoDescripcion(String cadena) {
		
		
		abrirEntityManager();
		try
		{
			ArrayList<Producto> productos = new ArrayList<Producto>();
			productos = (ArrayList<Producto>)getEm().createQuery("FROM Producto p WHERE p.descripcion like (:cade)").setParameter("cade", "%"+cadena+"%").getResultList();
			
			for(Producto p : productos)
				buscarUltimoPrecio(p);
	
			return productos;
		}
		finally
		{
			cerrarEntityManager();
		}
	}

	public static void devolverProducto(Producto p) 
	{
		abrirEntityManager();
		getEm().getTransaction().begin();
		try
		{
			
		Producto dbProducto = getEm().find(Producto.class, p.getId());
		dbProducto.setEstado(estado.STOCK.ordinal());
		dbProducto.setVenta(null);
		getEm().getTransaction().commit();
		}catch (Exception e) {
			getEm().getTransaction().rollback();
		}
		finally
		{
			cerrarEntityManager();
		}
		
		
	}
	
}
