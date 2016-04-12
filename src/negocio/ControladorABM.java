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
	public RespuestaServidor agregarProducto(char tipo, char subTipo, String descripcion, int estado, float precio)
	{
		final int cantidadDigitos = 7;
		String id = CatalogoProductos.ultimoIdProducto(tipo, subTipo);
		String idNuevo = Integer.toString(Integer.parseInt(id.substring(2, cantidadDigitos))+1);
		int a = cantidadDigitos-2-idNuevo.length(); //-2 porque le saco los 2 primeros digitos que son letras
		for(int i = 1; i<=a; i++)
		{
			idNuevo = "0"+idNuevo;
		}
		String idNuevoCompleto = Character.toString(tipo).concat(Character.toString(subTipo)).concat(idNuevo);
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		Producto pr = new Producto(descripcion,estado,idNuevoCompleto,new Precio(today.getTime(), precio));
		RespuestaServidor sr = CatalogoProductos.agregarProducto(pr);
		return sr;
	}
	
	public static void modificarProducto(String id, String newDescripcion,int estado, float newPrecio)
	{
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		CatalogoProductos.modificarProducto(new Producto(newDescripcion,estado, id,new Precio(today.getTime(), newPrecio)));
	}
	
	public static ArrayList<Cliente> buscarClientes()
	{
		return CatalogoClientes.buscarClientes();
	}
	
	public static RespuestaServidor agregarCliente(Cliente cl)
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
	

}
