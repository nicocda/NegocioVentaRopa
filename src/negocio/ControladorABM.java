package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import datos.CatalogoClientes;
import datos.CatalogoProductos;
import entidades.Cliente;
import entidades.Precio;
import entidades.Producto;
import excepciones.RespuestaServidor;

public class ControladorABM 
{
	public static RespuestaServidor agregarProducto(char tipo, char subTipo, String descripcion, int estado, float precio)
	{
		String idNuevoCompleto = obtenerIdCompleto(tipo, subTipo);
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		Producto pr = new Producto(descripcion,estado,idNuevoCompleto,new Precio(today.getTime(), precio));
		RespuestaServidor sr = CatalogoProductos.agregarProducto(pr);
		return sr;
	}
	
	//si no existe me da el primero para ese tipo y para ese subtipo.
	public static String obtenerIdCompleto(char tipo, char subTipo) 
	{
		final int cantidadDigitos = 7;
		String id = CatalogoProductos.ultimoIdProducto(tipo, subTipo);
		String idNuevo;
		if(id != null)
			idNuevo = Integer.toString(Integer.parseInt(id.substring(2, cantidadDigitos))+1);
		else
			idNuevo = "1";
		int a = cantidadDigitos-2-idNuevo.length(); //-2 porque le saco los 2 primeros digitos que son letras
		for(int i = 1; i<=a; i++)
		{
			idNuevo = "0"+idNuevo;
		}
			
		return Character.toString(tipo).concat(Character.toString(subTipo)).concat(idNuevo);
	}

	public static RespuestaServidor modificarCargarProducto(String id, char tipo, char subTipo, String newDescripcion,int estado, float newPrecio)
	{
		Producto pr = CatalogoProductos.buscarProducto(id);
		RespuestaServidor rs = new RespuestaServidor();
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		if(pr!=null)
		{
			rs=CatalogoProductos.modificarProducto(new Producto(newDescripcion,estado, id,new Precio(today.getTime(), newPrecio)));
		}
		else
		{
			String idNuevoCompleto = obtenerIdCompleto(tipo, subTipo);
			pr = new Producto(newDescripcion,estado,idNuevoCompleto,new Precio(today.getTime(), newPrecio));
			rs=CatalogoProductos.agregarProducto(pr);
		}
		return rs;
	}
	
	public static ArrayList<Cliente> buscarClientes()
	{
		return CatalogoClientes.buscarClientes();
	}
	
	public static RespuestaServidor agregarCliente(Cliente cl) throws RespuestaServidor
	{
			if(CatalogoClientes.buscarCliente(cl.getId()) != null && cl.getId() != 0)
			{
				return CatalogoClientes.modificarCliente(cl);
			}
			else if(CatalogoClientes.buscarCliente(cl.getId()) == null && cl.getId() == 0)
			{
				return CatalogoClientes.agregarCliente(cl);
			}
			else
			{
				RespuestaServidor sr = new RespuestaServidor();
				sr.addError("No existe cliente");
				return sr;
			}
		
	}
	
	public static String buscarUltimoIdProducto(char tipo, char subTipo)
	{
		return CatalogoProductos.ultimoIdProducto(tipo, subTipo);
	}

	public static ArrayList<Producto> buscarProductos()
	{
		//en realidad quiero todos. Hay que hacer métodos en el catálogo
		return CatalogoProductos.buscarProductosDisponibles();
	}
	
	public static Cliente buscarCliente(int idCliente) throws RespuestaServidor
	{
		return CatalogoClientes.buscarCliente(idCliente);
	}
}
