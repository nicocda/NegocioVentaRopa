package consola;

import java.sql.Timestamp;

import constantes.EstadoProducto;
import datosTODOparsear.CatalogoClientes;
import datosTODOparsear.CatalogoProductos;
import datosTODOparsear.CatalogoUsuarios;
import entidades.Cliente;
import entidades.Precio;
import entidades.Producto;
import entidades.Sucursal;
import entidades.Usuario;
import excepciones.RespuestaServidor;
import json.Json;

public class TestConsola 
{
	public static void main(String[] args) 
	{
		//testBuscarCliente(67);
		//testListarClientes();
		//testListarClientesPorPagina(1, 2);
		//testGuardarCliente(0);
		//testContarClientes();
		
		//testBuscarUsuario("juan");
		//testListarUsuarios();
		//testListarUsuariosPorPagina(1, 2);
		//testGuardarUsuario("test");
		//testContarUsuarios();
		
		//testBuscarProducto(1);
		//testBuscarProductos();
		testGuardarProducto(0);
	}	
	
	//region Privados
	private static void testBuscarCliente(int idCliente)
	{
		try
		{			
			System.out.println(CatalogoClientes.obtenerCliente(idCliente).toJson());
		}
		catch (RespuestaServidor e)
		{
			System.out.println(e.toString());
		}		
	}
	
	private static void testListarClientes()
	{
		try
		{			
			System.out.println(Json.listar(CatalogoClientes.obtenerClientes()));
		}
		catch (RespuestaServidor e)
		{
			System.out.println(e.toString());
		}		
	}
	
	private static void testListarClientesPorPagina(int paginaActual, int porPagina)
	{
		try
		{			
			System.out.println(Json.listar(CatalogoClientes.obtenerClientesPorPagina(paginaActual, porPagina)));
		}
		catch (RespuestaServidor e)
		{
			System.out.println(e.toString());
		}		
	}
	
	
	private static void testGuardarCliente(int idCliente)
	{
		Cliente cliente = new Cliente();
		
		if (idCliente != 0)
			cliente.setId(idCliente);
		
		cliente.setActivo(true);
		cliente.setApellido("test");
		cliente.setNombre("test");
		cliente.setDireccion("testAddress");
		cliente.setTelefono("1234567890");
		
		try
		{
			CatalogoClientes.guardarCliente(cliente);
		}
		catch (RespuestaServidor e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static void testContarClientes()
	{
		System.out.println(CatalogoClientes.contarClientes());
	}
	
	private static void testBuscarUsuario(String username)
	{
		try 
		{
			System.out.println(CatalogoUsuarios.obtenerUsuario(username).toJson());
		} 
		catch (RespuestaServidor e) 
		{
			System.out.println(e.toString());
		}
	}
	
	private static void testListarUsuarios()
	{
		try 
		{
			System.out.println(Json.listar(CatalogoUsuarios.obtenerUsuarios()));
		} 
		catch (RespuestaServidor e) 
		{
			System.out.println(e.toString());
		}
	}
	
	private static void testListarUsuariosPorPagina(int paginaActual, int porPagina)
	{
		try 
		{
			System.out.println(Json.listar(CatalogoUsuarios.obtenerUsuariosPorPagina(paginaActual, porPagina)));
		} 
		catch (RespuestaServidor e) 
		{
			System.out.println(e.toString());
		}
	}
	
	private static void testGuardarUsuario(String username)
	{
		Usuario usuario = new Usuario();
		
		usuario.setUsuario(username);
		usuario.setEmail("test@test.test");
		usuario.setNombreYApellido("test test");
		usuario.setPassword("1234");
		usuario.setTipoUsuario(1);
		
		try
		{
			CatalogoUsuarios.guardarUsuario(usuario);
		}
		catch(RespuestaServidor e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static void testContarUsuarios()
	{
		System.out.println(CatalogoUsuarios.contarUsuarios());
	}
	
	private static void testBuscarProducto(int idProducto)
	{
		try
		{
			System.out.println(CatalogoProductos.obtenerProducto(idProducto).toJson());
		}
		catch (RespuestaServidor e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static void testBuscarProductos()
	{
		try
		{
			System.out.println(Json.listar(CatalogoProductos.obtenerProductos()));
		}
		catch (RespuestaServidor e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static void testGuardarProducto(int idProducto)
	{
		Producto producto = new Producto();
		Precio precio = new Precio();
		Sucursal sucursal = new Sucursal();
		
		sucursal.setId(1);
		
		precio.setFecha(new Timestamp(System.currentTimeMillis()));
		precio.setPrecio(200);
		precio.setProducto(producto);
		
		producto.setCodigoProducto("12345678");
		producto.setDescripcion("test");
		producto.setEstado(EstadoProducto.STOCK);
		producto.setId(idProducto);
		
		producto.setNuevoPrecio(precio);
		producto.setSucursal(sucursal);
		
		try
		{
			CatalogoProductos.guardarProducto(producto);
		}
		catch (RespuestaServidor e)
		{
			System.out.println(e.toString());
		}
	}
	//endregion
}
