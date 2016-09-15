package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import entidades.Cliente;
import entidades.Producto;
import entidades.Usuario;
import entidades.Venta;
import entidades.Producto.estado;
import entidades.Usuario.tipoUsuario;
import entidades.Venta.formaPago;
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
	
	public static String devolverMensaje(RespuestaServidor sr, String mensajeExito, float importe, int estado)
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
		mensajesJson = mensajesJson + "}], \"tipoMensaje\":\"" + tipoMensaje +"\", \"importe\":"+ importe+", \"estado\":\""+estado+"\"}";
		return mensajesJson;
	}
	
	public static String jsonClientes(ArrayList<Cliente> clientes)
	{
		String rsp = "{\"array\": [\"";
	    for(int i=0;i<clientes.size()-1;i++)
	    {
	    	rsp=rsp.concat(clientes.get(i).getNombre()+"\", \"");
	    }
	    rsp=rsp.concat(clientes.get(clientes.size()-1).getNombre()+"\"]}");
	    return rsp;
	}
	
	public static String arrayTodosClientes(ArrayList<Cliente> clientes)
	{
		String rsp = "{\"data\": [";
	    for(int i=0;i<clientes.size()-1;i++)
	    {
	    	rsp= rsp + "{\"nombre\": \"" + clientes.get(i).getNombre()+"\", \"apellido\": \"" + clientes.get(i).getApellido()+"\", \"direccion\": \"" + clientes.get(i).getDireccion() + "\", \"id\": \"" + clientes.get(i).getId() + "\", \"telefono\": \"" + clientes.get(i).getTelefono() + "\"},";
	    }
	    rsp= rsp + "{\"nombre\": \"" + clientes.get(clientes.size()-1).getNombre()+"\", \"apellido\": \"" + clientes.get(clientes.size()-1).getApellido()+"\", \"direccion\": \"" + clientes.get(clientes.size()-1).getDireccion() + "\", \"id\": \"" + clientes.get(clientes.size()-1).getId() + "\", \"telefono\": \"" + clientes.get(clientes.size()-1).getTelefono() +"\"}]}";
	    return rsp;
	}

	public static String arrayTodosProductosVenta(Venta venta)
	{
		if(venta.getProductosArrayList().isEmpty())
			return "[]";
		
		ArrayList<Producto> productos = venta.getProductosArrayList();
		
		String rsp = "[";
	    for(int i=0;i<productos.size()-1;i++)
	    {
	    	rsp= rsp + "{\"id\":\"" + productos.get(i).getId()+"\",\"descripcion\":\"" + productos.get(i).getDescripcion() + "\",\"precio\":\"" + productos.get(i).getPrecio().getPrecio() +"\", \"estado\": \"" + estado.values()[productos.get(i).getEstado()].name() +"\"},";
	    }
	    rsp= rsp + "{\"id\":\"" + productos.get(productos.size()-1).getId()+"\",\"descripcion\":\"" + productos.get(productos.size()-1).getDescripcion() + "\",\"precio\":\"" + productos.get(productos.size()-1).getPrecio().getPrecio() +"\", \"estado\": \"" + estado.values()[productos.get(productos.size()-1).getEstado()].name() +"\"}]";
	    return rsp;
	}
	
	public static String arrayTodosProductos(ArrayList<Producto> productos)
	{
		if(productos.isEmpty())
			return "[]";
		else
		{
		String rsp = "[";
	    for(int i=0;i<productos.size()-1;i++)
	    {
	    	rsp= rsp + "{\"id\": \"" + productos.get(i).getId()+"\", \"descripcion\": \"" + productos.get(i).getDescripcion() + "\", \"precio\": \"" + productos.get(i).getPrecio().getPrecio() +"\", \"estado\": \"" + estado.values()[productos.get(i).getEstado()].name() + "\"},";
	    }
	    rsp= rsp + "{\"id\": \"" + productos.get(productos.size()-1).getId()+"\", \"descripcion\": \"" + productos.get(productos.size()-1).getDescripcion() + "\", \"precio\": \"" + productos.get(productos.size()-1).getPrecio().getPrecio() +"\", \"estado\": \"" + estado.values()[productos.get(productos.size()-1).getEstado()].name() +"\"}]";
	    return rsp;
		}
	}
	
	public static String jsonVentas(ArrayList<Venta> ventas)
	{
		if (!ventas.isEmpty())
		{
			SimpleDateFormat form = new SimpleDateFormat("dd MMM yyyy", new Locale("es", "ES"));
			String rsp = "[";
		    for(int i=0;i<ventas.size()-1;i++)
		    {
		    	rsp= rsp + "{\"nombreApellido\": \"" + ventas.get(i).getCliente().getNombre()+" "+ventas.get(i).getCliente().getApellido()+"\", \"fecha\": \"" + form.format(ventas.get(i).getFechaVenta()) +"\", \"importe\": \"" + ventas.get(i).getImporte() +"\", \"formaPago\": \"" + formaPago.values()[ventas.get(i).getFormaPago()].name() +"\", \"idVenta\": \""+ ventas.get(i).getId()+"\"},";
		    }
		    rsp= rsp + "{\"nombreApellido\": \"" + ventas.get(ventas.size()-1).getCliente().getNombre()+" "+ventas.get(ventas.size()-1).getCliente().getApellido()+"\", \"fecha\": \"" +  form.format(ventas.get(ventas.size()-1).getFechaVenta()) +"\", \"importe\": \"" + ventas.get(ventas.size()-1).getImporte() +"\", \"formaPago\": \"" + formaPago.values()[ventas.get(ventas.size()-1).getFormaPago()].name() +"\", \"idVenta\": \""+ ventas.get(ventas.size()-1).getId()+"\"}]";
		    return rsp;
		}
		else 
			return "[]";
	}

	public static String arrayTodosUsuarios(ArrayList<Usuario> usuarios) 
	{
		String rsp = "[";
	    for(int i=0;i<usuarios.size()-1;i++)
	    {
	    	rsp= rsp + "{\"usuario\": \"" + usuarios.get(i).getUsuario()+"\", \"nombreYApellido\": \"" + usuarios.get(i).getNombreYApellido() + "\", \"email\": \"" + usuarios.get(i).getEmail() +"\", \"tipoUsuario\": \"" + tipoUsuario.values()[usuarios.get(i).getTipoUsuario()].name() + "\"},";
	    }
	    rsp= rsp + "{\"usuario\": \"" + usuarios.get(usuarios.size()-1).getUsuario()+"\", \"nombreYApellido\": \"" + usuarios.get(usuarios.size()-1).getNombreYApellido() + "\", \"email\": \"" + usuarios.get(usuarios.size()-1).getEmail() +"\", \"tipoUsuario\": \"" + tipoUsuario.values()[usuarios.get(usuarios.size()-1).getTipoUsuario()].name() + "\"}]";
	    return rsp;
	}

	public static String arrayVentasMorosas(ArrayList<Venta> ventasMorosas) {
		if(ventasMorosas.isEmpty())
			return "{\"data\": []}";	
		else
		{
			String rsp = "{\"cliente\": \""+ventasMorosas.get(0).getCliente().getNombre()+" "+ventasMorosas.get(0).getCliente().getApellido()+"\", \"data\": [";
		    for(int i=0;i<ventasMorosas.size()-1;i++)
		    {
		    	rsp= rsp + "{\"Id\": \"" + ventasMorosas.get(i).getId()+"\", \"fechaVenta\": \"" + ventasMorosas.get(i).getFechaVenta() + "\", \"importeTotal\": \"" + ventasMorosas.get(i).getImporte() +"\", \"deuda\": \"" + ventasMorosas.get(i).getDeudaPendiente() + "\"},";
		    }
		    rsp= rsp + "{\"Id\": \"" + ventasMorosas.get(ventasMorosas.size()-1).getId()+"\", \"fechaVenta\": \"" + ventasMorosas.get(ventasMorosas.size()-1).getFechaVenta() + "\", \"importeTotal\": \"" + ventasMorosas.get(ventasMorosas.size()-1).getImporte() +"\", \"deuda\": \"" + ventasMorosas.get(ventasMorosas.size()-1).getDeudaPendiente() + "\"}]}";
			return rsp;
		}
	}
	
	public static String ventaEntera(Venta venta)
	{
		if(venta == null)
			return "{\"venta\": []}";
		else
		{
			ArrayList<Producto> productos = venta.getProductosArrayList();
			String rsp = "{\"cliente\": \""+venta.getCliente().getNombre()+" "+venta.getCliente().getApellido()+"\", \"venta\": [";
		    for(int i=0;i<productos.size()-1;i++)
		    {
		    	rsp= rsp + "{\"id\": \"" + productos.get(i).getId()+"\", \"descripcion\": \"" + productos.get(i).getDescripcion() + "\", \"precio\": \"" + productos.get(i).getPrecio().getPrecio() +"\", \"estado\": \"" + estado.values()[productos.get(i).getEstado()].name() + "\"},";
		    }
		    rsp= rsp + "{\"id\": \"" + productos.get(productos.size()-1).getId()+"\", \"descripcion\": \"" + productos.get(productos.size()-1).getDescripcion() + "\", \"precio\": \"" + productos.get(productos.size()-1).getPrecio().getPrecio() +"\", \"estado\": \"" + estado.values()[productos.get(productos.size()-1).getEstado()].name() +"\"}]}";
		    return rsp;
		}
	}
	
	public static String unProducto(Producto producto)
	{
		String json = "";
		
		if (producto != null)
		{
			json = json + "{";
			json = json + "\"codigo\": \"" + producto.getId() + "\", ";
			json = json + "\"descripcion\": \"" + producto.getDescripcion() + "\", ";
			json = json + "\"precioActual\": " + producto.getPrecio().getPrecio() + ", ";
			json = json + "\"fechaCambio\": \"" + producto.getPrecio().getFecha() + "\"";
			json = json + "}";
		}
		
		return json;
	}
}
