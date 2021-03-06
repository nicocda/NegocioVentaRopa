package negocio;

import java.util.ArrayList;
import java.util.Calendar;
import datos.*;
import entidades.*;
import excepciones.RespuestaServidor;

public class ControladorABM 
{
	
	
	
	
	
	
	//CLIENTE
	public static Cliente buscarCliente(int idCliente) throws RespuestaServidor
	{
		return CatalogoClientes.buscarCliente(idCliente);
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
	
	//PRODUCTO
	public static void guardarProducto(String idProducto, String descripcion, int estado, float valor, int idSucursal) throws RespuestaServidor
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
		producto.setIdSucursal(idSucursal);
		
		//Busco el producto, si no existe le agrego el precio, si existe y el precio es distinto al actual se lo agrego.
		Producto dbProducto = CatalogoProductos.buscarProducto(idProducto);
		if (dbProducto == null || (dbProducto != null && dbProducto.getPrecio().getPrecio() != valor))
		{
			producto.addPrecio(precio);	
			producto.setPrecio(precio);
		}

		CatalogoProductos.guardarProducto(producto);
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
			//si no existe me da el primero para ese tipo y para ese subtipo.
	public static String obtenerIdCompleto(char tipo, char subTipo) 
	{
		final int cantidadDigitos = 7;
		String id = CatalogoProductos.buscarUltimoIdProducto(tipo, subTipo);
		String idNuevo;
		if(id != null)
			idNuevo = Integer.toString(Integer.parseInt(id.substring(2, id.length()))+1);
		else
			idNuevo = "1";
		int a = cantidadDigitos-2-idNuevo.length(); //-2 porque le saco los 2 primeros digitos que son letras
		for(int i = 1; i<=a; i++)
		{
			idNuevo = "0"+idNuevo;
		}
			
		return Character.toString(tipo).concat(Character.toString(subTipo)).concat(idNuevo);
	}

	public static String obtenerIdCompleto2(char tipo, char subTipo,char tipo2, char subTipo2) 
	{
		final int cantidadDigitos = 7;
		String id =  Character.toString(tipo2) + Character.toString(subTipo2);
		for(int i=1; i<cantidadDigitos-2; i++)
		{
			id = id + "0";
		}
		if(CatalogoProductos.buscarUltimoIdProducto(tipo2, subTipo2) != null)
			{
			id = CatalogoProductos.buscarUltimoIdProducto(tipo2, subTipo2);
			}
		String idNuevo;
		if(id != null)
		{
			if((tipo == tipo2) && (subTipo == subTipo2))
				idNuevo = Integer.toString(Integer.parseInt(id.substring(2, id.length()))+2);
			else
				idNuevo = Integer.toString(Integer.parseInt(id.substring(2, id.length()))+1);
		}
		else
			idNuevo = "1";
		int a = cantidadDigitos-2-idNuevo.length(); //-2 porque le saco los 2 primeros digitos que son letras
		for(int i = 1; i<=a; i++)
		{
			idNuevo = "0"+idNuevo;
		}
			
		return Character.toString(tipo2).concat(Character.toString(subTipo2)).concat(idNuevo);
	}
	//Usuario
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
	public static void guardarUsuario(String nombreUsuario, String password, String nombreApellido, String email, int tipoUsuario, int idSucursal) throws RespuestaServidor
	{
		Usuario usuario = new Usuario();
		
		usuario.setNombreYApellido(nombreApellido);
		usuario.setEmail(email);
		usuario.setTipoUsuario(tipoUsuario);
		usuario.setUsuario(nombreUsuario);
		usuario.setPassword(password);
		
		usuario.setSucursal(CatalogoSucursales.buscarSucursal(idSucursal));
		
		CatalogoUsuarios.guardarUsuario(usuario);
	}
	public static ArrayList<Usuario> buscarTodosUsuarios()
	{
		return CatalogoUsuarios.buscarTodosUsuarios();
	}
	public static void eliminarUsuario(String usuario)
	{
		CatalogoUsuarios.eliminarUsuario(usuario);
	}
	
	public static ArrayList<Sucursal> buscarSucursales()
	{
		return CatalogoSucursales.buscarTodasSucursales();
	}
	//Tarjetas
	public static ArrayList<TipoTarjeta> getTipoTarjetas()
	{
		return CatalogoTarjetas.buscarTipoTarjetas();
	}
	public static TipoTarjeta buscartipoTarjeta(int id)
	{
		return CatalogoTarjetas.buscartipoTarjeta(id);
	}
	
	//Configuracion
	public static ArrayList<EventLog> buscarTodosEventLog()
	{
		return CatalogoEventLog.buscarTodosEventLog();
	}
	
	public static Configuracion buscarConfiguracion()
	{
		return CatalogoConfiguracion.buscarConfiguracion();
	}
}
