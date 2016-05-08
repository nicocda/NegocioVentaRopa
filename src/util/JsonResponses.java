package util;

import java.util.ArrayList;

import entidades.Cliente;
import entidades.Producto;
import entidades.Usuario;
import entidades.Venta;
import entidades.Producto.estado;
import entidades.Usuario.tipoUsuario;
import excepciones.RespuestaServidor;

public class JsonResponses 
{

	public static String devolverMensaje(RespuestaServidor sr, String mensajeExito)
	{
		String tipoMensaje = sr.getErrors().isEmpty() ? mensajeExito : "error";
		String mensajesJson = "{\"mensajes\":[{";
		for(int i = 1; i <= sr.getErrors().size(); i++)
		{
			if(sr.getErrors().size()!=i)
				mensajesJson = mensajesJson + "\"mensaje\":\""+sr.getErrors().get(i-1).getErrorMessage()+"\"},{";
			else
				mensajesJson = mensajesJson + "\"mensaje\":\""+sr.getErrors().get(i-1).getErrorMessage()+"\"";
		}
		mensajesJson = mensajesJson + "}], \"tipoMensaje\":\"" + tipoMensaje +"\"}";
		return mensajesJson;
	}
	
	public static String jsonClientes(ArrayList<Cliente> clientes)
	{
		String rsp = "{\"array\": [\"";
	    for(int i=0;i<clientes.size()-1;i++)
	    {
	    	rsp=rsp.concat(clientes.get(i).getNombreApellido()+"\", \"");
	    }
	    rsp=rsp.concat(clientes.get(clientes.size()-1).getNombreApellido()+"\"]}");
	    return rsp;
	}
	
	public static String arrayTodosClientes(ArrayList<Cliente> clientes)
	{
		String rsp = "{\"clientes\": [";
	    for(int i=0;i<clientes.size()-1;i++)
	    {
	    	rsp= rsp + "{\"nombreApellido\": \"" + clientes.get(i).getNombreApellido()+"\", \"direccion\": \"" + clientes.get(i).getDireccion() + "\", \"id\": \"" + clientes.get(i).getId() + "\", \"telefono\": \"" + clientes.get(i).getTelefono() + "\"},";
	    }
	    rsp= rsp + "{\"nombreApellido\": \"" + clientes.get(clientes.size()-1).getNombreApellido()+"\", \"direccion\": \"" + clientes.get(clientes.size()-1).getDireccion() + "\", \"id\": \"" + clientes.get(clientes.size()-1).getId() + "\", \"telefono\": \"" + clientes.get(clientes.size()-1).getTelefono() +"\"}]}";
	    return rsp;
	}

	public static String arrayTodosProductosVenta(Venta venta)
	{
		ArrayList<Producto> productos = (ArrayList<Producto>)venta.getProductos();
		String rsp = "{\"importe\": \" "+ venta.getImporte() +" \", \"productos\": [";
	    for(int i=0;i<productos.size()-1;i++)
	    {
	    	rsp= rsp + "{\"id\": \"" + productos.get(i).getId()+"\", \"descripcion\": \"" + productos.get(i).getDescripcion() + "\", \"precio\": \"" + productos.get(i).getPrecio().getPrecio() +"\", \"estado\": \"" + estado.values()[productos.get(i).getEstado()].name() + "\"},";
	    }
	    rsp= rsp + "{\"id\": \"" + productos.get(productos.size()-1).getId()+"\", \"descripcion\": \"" + productos.get(productos.size()-1).getDescripcion() + "\", \"precio\": \"" + productos.get(productos.size()-1).getPrecio().getPrecio() +"\", \"estado\": \"" + estado.values()[productos.get(productos.size()-1).getEstado()].name() +"\"}]}";
	    return rsp;
	}
	
	public static String arrayTodosProductos(ArrayList<Producto> productos)
	{
		String rsp = "{\"productos\": [";
	    for(int i=0;i<productos.size()-1;i++)
	    {
	    	rsp= rsp + "{\"id\": \"" + productos.get(i).getId()+"\", \"descripcion\": \"" + productos.get(i).getDescripcion() + "\", \"precio\": \"" + productos.get(i).getPrecio().getPrecio() +"\", \"estado\": \"" + estado.values()[productos.get(i).getEstado()].name() + "\"},";
	    }
	    rsp= rsp + "{\"id\": \"" + productos.get(productos.size()-1).getId()+"\", \"descripcion\": \"" + productos.get(productos.size()-1).getDescripcion() + "\", \"precio\": \"" + productos.get(productos.size()-1).getPrecio().getPrecio() +"\", \"estado\": \"" + estado.values()[productos.get(productos.size()-1).getEstado()].name() +"\"}]}";
	    return rsp;
	}
	
	public static String jsonVentas(ArrayList<Venta> ventas)
	{
		if (!ventas.isEmpty())
		{
			String rsp = "{\"ventas\": [";
		    for(int i=0;i<ventas.size()-1;i++)
		    {
		    	rsp= rsp + "{\"nombreApellido\": \"" + ventas.get(i).getCliente().getNombreApellido()+"\", \"fecha\": \"" + ventas.get(i).getFechaVenta().toString() +"\"},";
		    }
		    rsp= rsp + "{\"nombreApellido\": \"" + ventas.get(ventas.size()-1).getCliente().getNombreApellido()+"\", \"fecha\": \"" +  ventas.get(ventas.size()-1).getFechaVenta().toString() +"\"}]}";
		    return rsp;
		}
		else 
			return "{\"ventas\": []}";
	}

	public static String arrayTodosUsuarios(ArrayList<Usuario> usuarios) 
	{
		String rsp = "{\"usuarios\": [";
	    for(int i=0;i<usuarios.size()-1;i++)
	    {
	    	rsp= rsp + "{\"usuario\": \"" + usuarios.get(i).getUsuario()+"\", \"nombreApellido\": \"" + usuarios.get(i).getNombreYApellido() + "\", \"email\": \"" + usuarios.get(i).getEmail() +"\", \"tipo\": \"" + tipoUsuario.values()[usuarios.get(i).getTipoUsuario()].name() + "\"},";
	    }
	    rsp= rsp + "{\"usuario\": \"" + usuarios.get(usuarios.size()-1).getUsuario()+"\", \"nombreApellido\": \"" + usuarios.get(usuarios.size()-1).getNombreYApellido() + "\", \"email\": \"" + usuarios.get(usuarios.size()-1).getEmail() +"\", \"tipo\": \"" + tipoUsuario.values()[usuarios.get(usuarios.size()-1).getTipoUsuario()].name() + "\"}]}";
	    return rsp;
	}
}
