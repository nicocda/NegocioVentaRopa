package negocio;
import entidades.Cliente;
import entidades.Producto;
import entidades.Usuario;
import entidades.Venta;
import excepciones.RespuestaServidor;
import util.UtilidadesWeb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.sun.javafx.webkit.UtilitiesImpl;

import datos.CatalogoClientes;
import datos.CatalogoProductos;
import datos.CatalogoUsuarios;
import datos.CatalogoVentas;

public class ControladorTransaccion {

	public Usuario buscarUsuario(String id, String pass)
	{	
		return CatalogoUsuarios.buscarUsuario(id, pass);	
	}
	
	public static Producto buscarProducto (String id)
	{
		return CatalogoProductos.buscarProducto(id);
	}

	public static Cliente buscarCliente(String nombreCliente) {
		
		return CatalogoClientes.buscarCliente(nombreCliente);
	}

	public static void registrarVenta(Venta vta) throws RespuestaServidor
	{
		 CatalogoVentas.registrarVenta(vta);
	}
	
	public static ArrayList<Venta> buscarVentasDia(Date fechaMin, Date fechaMax) throws RespuestaServidor
	{
			return CatalogoVentas.buscarVentasPorDia(fechaMin, fechaMax);
	}
}
