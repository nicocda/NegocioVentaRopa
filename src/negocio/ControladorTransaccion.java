package negocio;
import entidades.Cliente;
import entidades.Producto;
import entidades.Tarjeta;
import entidades.Usuario;
import entidades.Venta;
import entidades.Cuota;
import entidades.Precio;
import excepciones.RespuestaServidor;
import java.util.ArrayList;
import java.util.Date;

import datos.*;

public class ControladorTransaccion {

	public Usuario buscarUsuario(String id, String pass)
	{	
		Usuario usu = null;
		usu = CatalogoUsuarios.buscarUsuario(id);
		if(usu.getPassword() != pass)
		{
			usu = null;
		}
		return usu; 	
	}
	private static String contraseñaAdministracion = "1234";
	
	public static void definirContraseñaAdministracion(String pass)
	{
		contraseñaAdministracion = pass;
	}
	public static boolean validarContraseñaAdministracion(String pass)
	{
		if(contraseñaAdministracion.equals(pass))
			return true;
		else
			return false;
		
	}
	
	public static Producto buscarProducto (String id)
	{
		return CatalogoProductos.buscarProducto(id);
	}
	
	public static void devolverProducto(Producto p)
	{
		CatalogoProductos.devolverProducto(p);
	}
	public static Cliente buscarCliente(int idCliente) {
		
		return CatalogoClientes.buscarCliente(idCliente);
	}

	public static void registrarVenta(Venta vta) throws RespuestaServidor
	{	
		if(vta.getFormaPago() == Venta.formaPago.TARJETA.ordinal())
		{
			Tarjeta tar = CatalogoTarjetas.buscarTarjeta(vta.getTarjeta());
			if(tar == null)
			{
				CatalogoTarjetas.registrarTarjeta(vta.getTarjeta());
			}
		}	
		CatalogoVentas.registrarVenta(vta);
	}
	
	public static ArrayList<Venta> buscarVentasDia(Date fechaMin, Date fechaMax, int idCliente, int formaPago) throws RespuestaServidor
	{
		return CatalogoVentas.buscarVentasPorDia(fechaMin, fechaMax, idCliente, formaPago);
	}

	public static ArrayList<Venta> buscarVentasCliente(int idClie) {
	
		ArrayList<Venta> ventasMorosas = new ArrayList<Venta>();
		ArrayList<Venta> ventas = CatalogoVentas.buscarVentasCliente(idClie);
		for(Venta v : ventas)
		{
			if(v.getFormaPago() == Venta.formaPago.CTACORRIENTE.ordinal())
			{
				int cantPagado = 0;
				ArrayList<Cuota> cuotas = CatalogoCuotas.buscarCuotas(v);
				if(cuotas != null)
				{
					for(Cuota c : cuotas)
					{
						cantPagado += c.getImporte();
					}
				}
				if(cantPagado < v.getImporte())
				{
					v.setDeudaPendiente(v.getImporte() - cantPagado);
					ventasMorosas.add(v);
				}
			}
			
		}
		return ventasMorosas;
	}

	public static ArrayList<Producto> buscarProductosDescripcion(String cadena) {
		
		return CatalogoProductos.buscarProductoDescripcion(cadena);
	}

	public static Venta buscarVenta(int idVenta)
	{
		return CatalogoVentas.buscarVenta(idVenta);
	}

	public static void guardarPrecios(float porcentaje, String[] productos, Date fechaInicio, Date fechaFin) throws RespuestaServidor
	{
		RespuestaServidor sr = validarCambioDePrecios(porcentaje, productos, fechaInicio, fechaFin);
		
		if(!sr.getStatus())
			throw sr;
		
		ArrayList<Producto> listaProductos = new ArrayList<>();
		
		// Con cada string del array busco los productos correspondientes
		for(String p : productos)
		{
			listaProductos.add(CatalogoProductos.buscarProducto(p));
		}
		
		for (Producto producto : listaProductos)
		{
			//Precio actual
			float precioActual = producto.getPrecio().getPrecio();
			
			// Creo los nuevos precios
			Precio precio = new Precio();
			Precio precioFuturo = new Precio();
			
			precio.setFecha(fechaInicio);
			precio.setPrecio(precioActual - precioActual * porcentaje);
			precio.setProducto(producto);
			
			precioFuturo.setFecha(fechaFin);
			precioFuturo.setPrecio(precioActual);
			precioFuturo.setProducto(producto);
						
			// Se los seteo al producto
			producto.getPrecios().add(precio);
			producto.getPrecios().add(precioFuturo);
			
			// Lo guardo en la DB
			CatalogoProductos.guardarProducto(producto);
		}
	}
	
	private static RespuestaServidor validarCambioDePrecios(float porcentaje,  String[] productos, Date fechaInicio, Date fechaFin)
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if (fechaInicio == null)
			sr.addError("No se ingresó fecha de inicio.");
		
		if (fechaFin == null)
			sr.addError("No se ingresó fecha final.");
		
		if (porcentaje == 0)
			sr.addError("El porcentaje ingresado no es válido.");
		
		if (porcentaje < 0)
			sr.addError("No se puede ingresar un descuento negativo :).");
		
		if (productos.length == 0)
			sr.addError("Debe ingresar al menos un producto.");
				
		return sr;
	}
}
