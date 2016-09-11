package datos;

import java.util.ArrayList;
import java.util.Date;

import entidades.Precio;
import entidades.Producto;
import entidades.Producto.estado;
import entidades.Venta;
import excepciones.RespuestaServidor;

public class CatalogoVentas  extends CatalogoBase
{

	public static void registrarVenta(Venta vta) throws RespuestaServidor
	{
		RespuestaServidor rs = validarVenta(vta);
		if (!rs.getStatus())
			throw rs;
		
		abrirEntityManager();
		try
		{
			getEm().getTransaction().begin();
			getEm().persist(vta);
			
			for(Producto p : vta.getProductosArrayList())
			{
				Producto dbProducto = getEm().find(Producto.class, p.getId());
				dbProducto.setEstado(estado.VENDIDO.ordinal());
				dbProducto.setVenta(vta);
			}
			
			getEm().getTransaction().commit();	
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Venta> buscarVentasPorDia(Date fechaMin, Date fechaMax, int idCliente, int formaPago) 
	{
		abrirEntityManager();
		try
		{
			ArrayList<Venta> ventas = new ArrayList<Venta>();
			 ventas = (ArrayList<Venta>) getEm().createQuery("FROM Venta v WHERE (fechaVenta >= :fmin AND fechaVenta <= :fmax) AND (:idCliente = 0 OR (idCliente = :idCliente)) AND (:formaPago < 0 OR (formaPago = :formaPago))")
			.setParameter("fmin", fechaMin).setParameter("fmax", fechaMax).setParameter("idCliente", idCliente).setParameter("formaPago", formaPago).getResultList();
			return ventas;	
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	public static Venta buscarVenta(int idVenta)
	{
		abrirEntityManager();
		Venta vta = null;
		try
		{
			vta = getEm().find(Venta.class,idVenta);
			ArrayList<Producto> productos = vta.getProductosArrayList();
			for(Producto p : productos)
			{
				buscarUltimoPrecio(p);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cerrarEntityManager();
		}
		return vta;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Venta> buscarVentasCliente(int idCliente) 
	{
		abrirEntityManager();
		try
		{
			ArrayList<Venta> ventas = new ArrayList<Venta>();
			 ventas = (ArrayList<Venta>) getEm().createQuery("FROM Venta v where idCliente = :idCli").setParameter("idCli", idCliente).getResultList();
			return ventas;	
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	
	
	private static RespuestaServidor validarVenta(Venta vta)
	{
		RespuestaServidor sr = new RespuestaServidor();
		if(vta.getCliente() == null)
		{
			sr.addError("El cliente es Obligatorio.");
		}
		if(vta.getProductosArrayList().isEmpty())
		{
			sr.addError("La venta debe contener al menos un producto.");
		}
		if(vta.getFormaPago()==0)
		{
			sr.addError("Seleccione una forma de pago.");
		}
		return sr;
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
}
