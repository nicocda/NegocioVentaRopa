package negocio;
import entidades.Cliente;
import entidades.Producto;
import entidades.Tarjeta;
import entidades.Usuario;
import entidades.Venta;
import entidades.Cuota;
import excepciones.RespuestaServidor;
import util.UtilidadesWeb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.javafx.webkit.UtilitiesImpl;

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

	
}
