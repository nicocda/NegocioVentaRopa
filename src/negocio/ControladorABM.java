package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import datos.CatalogoClientes;
import datos.CatalogoConfiguracion;
import datos.CatalogoEventLog;
import datos.CatalogoProductos;
import datos.CatalogoUsuarios;
import entidades.Cliente;
import entidades.Configuracion;
import entidades.EventLog;
import entidades.Precio;
import entidades.Producto;
import entidades.Usuario;
import excepciones.RespuestaServidor;

public class ControladorABM 
{
	public static void guardarProducto(String idProducto, String descripcion, int estado, float valor) throws RespuestaServidor
	{		
		Calendar hoy = Calendar.getInstance();
		hoy.set(Calendar.HOUR_OF_DAY, 0);
		
		Precio precio = new Precio();
		
		precio.setFecha(hoy.getTime());
		precio.setPrecio(valor);
		
		Producto producto = new Producto();
		
		producto.setDescripcion(descripcion);
		producto.setId(idProducto);
		producto.setEstado(estado);
		
		//Busco el producto, si no existe le agrego el precio, si existe y el precio es distinto al actual se lo agrego.
		Producto dbProducto = CatalogoProductos.buscarProducto(idProducto);
		if (dbProducto == null || (dbProducto != null && dbProducto.getPrecio().getPrecio() != valor))
		{
			producto.addPrecio(precio);	
			producto.setPrecio(precio);
		}

		CatalogoProductos.guardarProducto(producto);
	}
	
	public static ArrayList<Cliente> buscarTodosClientes()
	{
		return CatalogoClientes.buscarTodosClientes();
	}
	
	public static void guardarCliente(int id, String nombre, String apellido, String direccion, String telefono) throws RespuestaServidor
	{
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setTelefono(telefono);
		cliente.setDireccion(direccion);
		CatalogoClientes.guardarCliente(cliente);
	}
	
	public static String buscarUltimoIdProducto(char tipo, char subTipo)
	{
		return CatalogoProductos.buscarUltimoIdProducto(tipo, subTipo);
	}

	public static ArrayList<Producto> buscarTodosProductos()
	{
		return CatalogoProductos.buscarTodosProductos();
	}
	
	public static ArrayList<Producto> buscarTodosProductosEnStock()
	{
		return CatalogoProductos.buscarTodosProductosEnStock();
	}
	
	public static Cliente buscarCliente(int idCliente) throws RespuestaServidor
	{
		return CatalogoClientes.buscarCliente(idCliente);
	}
	
	public static Usuario validarUsuario(String nombreUsuario, String password)
	{
		Usuario usuario = CatalogoUsuarios.buscarUsuario(nombreUsuario);
		if(usuario != null)
		{
			if(usuario.getPassword().equals(password))
					return usuario;
		}
		return null;
	}
	
	public static ArrayList<Usuario> buscarTodosUsuarios()
	{
		return CatalogoUsuarios.buscarTodosUsuarios();
	}
	
	public static ArrayList<EventLog> buscarTodosEventLog()
	{
		return CatalogoEventLog.buscarTodosEventLog();
	}
	
	public static Configuracion buscarConfiguracion()
	{
		return CatalogoConfiguracion.buscarConfiguracion();
	}
	
	//si no existe me da el primero para ese tipo y para ese subtipo.
	public static String obtenerIdCompleto(char tipo, char subTipo) 
	{
		final int cantidadDigitos = 3;
		String id = CatalogoProductos.buscarUltimoIdProducto(tipo, subTipo);
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

	public static void guardarUsuario(String nombreUsuario, String password, String nombreApellido, String email, int tipoUsuario) throws RespuestaServidor
	{
		Usuario usuario = new Usuario();
		
		usuario.setNombreYApellido(nombreApellido);
		usuario.setEmail(email);
		usuario.setTipoUsuario(tipoUsuario);
		usuario.setUsuario(nombreUsuario);
		usuario.setPassword(password);
		
		CatalogoUsuarios.guardarUsuario(usuario);
	}

	public static void eliminarUsuario(String usuario)
	{
		CatalogoUsuarios.eliminarUsuario(usuario);
	}
}
