package negocio;
import entidades.Cliente;
import entidades.Producto;
import entidades.Usuario;
import entidades.Venta;
import excepciones.RespuestaServidor;

import java.util.ArrayList;
import java.util.Calendar;

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
	
	public static ArrayList<Venta> buscarVentasDia()
	{
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY,0);
		try
		{
			return CatalogoVentas.buscarVentas(today);
		}
		catch (RespuestaServidor sr)
		{
			return new ArrayList<>();
			//HAY QUE VALIDAR! :D
		}
	}
}
