package datos;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Precio;
import entidades.Producto;
import excepciones.RespuestaServidor;

public class CatalogoProductos extends CatalogoBase
{
	public static Producto buscarProducto(String idProducto)
	{
		abrirEntityManager();
		Producto producto = getEm().find(Producto.class, idProducto);
		
		if (producto != null)
			buscarUltimoPrecio(producto);
		
		cerrarEntityManager();
		return producto;
	}
	
	public static void guardarProducto(Producto producto) throws RespuestaServidor
	{
		RespuestaServidor rs = validarProducto(producto);
		if (!rs.getStatus())
			throw rs;
		
		abrirEntityManager();
		Producto dbProducto = getEm().find(Producto.class, producto.getId());
		
		getEm().getTransaction().begin();
		
		if(dbProducto == null)
			getEm().persist(producto);
		else
			getEm().merge(producto);
		
		getEm().getTransaction().commit();
		
		cerrarEntityManager();
	}
	

	@SuppressWarnings("unchecked")
	public static ArrayList<Producto> buscarTodosProductos()
	{
		abrirEntityManager();
		ArrayList<Producto> todosProductos = (ArrayList<Producto>)getEm().createQuery("SELECT p FROM Producto p").getResultList();
		
		for(Producto p : todosProductos)
			buscarUltimoPrecio(p);
	
		cerrarEntityManager();
		return todosProductos;
	}
	
	public static String buscarUltimoIdProducto(char tipo, char subtipo)
	{
		abrirEntityManager();
		
		String parametro = Character.toString(tipo) + Character.toString(subtipo) + "%";
		String resultado = (String)getEm().createQuery("SELECT p.id FROM Producto p WHERE p.id like (:tiposubtipo) ORDER BY p.id DESC").setMaxResults(1).setParameter("tiposubtipo", parametro).getSingleResult();
		
		cerrarEntityManager();
		return resultado;
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
	
}
