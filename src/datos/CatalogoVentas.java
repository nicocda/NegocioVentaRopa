package datos;

import java.util.ArrayList;
import java.util.Date;

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
				p.setEstado(estado.VENDIDO.ordinal());
			}
			
			getEm().getTransaction().commit();	
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Venta> buscarVentasPorDia(Date fechaMin,
			Date fechaMax, int idCliente, int formaPago) 
	{
		abrirEntityManager();
		try
		{
			ArrayList<Venta> ventas = new ArrayList<Venta>();
			 ventas = (ArrayList<Venta>) getEm().createQuery("FROM Venta v where fechaVenta >= :fmin and fechaVenta <= :fmax")
			.setParameter("fmin", fechaMin).setParameter("fmax", fechaMax).getResultList();
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
			vta = (Venta)getEm().createQuery("FROM Venta v where id = :id").setParameter("id", idVenta).getSingleResult();
		}
		finally
		{
			cerrarEntityManager();
		}
		return vta;
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
}
