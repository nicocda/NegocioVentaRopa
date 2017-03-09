package consola;

import datosTODOparsear.CatalogoClientes;
import datosTODOparsear.CatalogoProductos;
import datosTODOparsear.CatalogoUsuarios;
import entidades.Cliente;
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
		
		testBuscarProducto(1);
		testBuscarProductos();
	}	
	
	//region Privados
	private static void testBuscarCliente(int idCliente)
	{
		try
		{			
			System.out.println(Json.cliente(CatalogoClientes.obtenerCliente(idCliente)));
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
			System.out.println(Json.listarClientes(CatalogoClientes.obtenerClientes()));
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
			System.out.println(Json.listarClientes(CatalogoClientes.obtenerClientesPorPagina(paginaActual, porPagina)));
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
			System.out.println(Json.usuario(CatalogoUsuarios.obtenerUsuario(username)));
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
			System.out.println(Json.listarUsuarios(CatalogoUsuarios.obtenerUsuarios()));
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
			System.out.println(Json.listarUsuarios(CatalogoUsuarios.obtenerUsuariosPorPagina(paginaActual, porPagina)));
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
			System.out.println(Json.producto(CatalogoProductos.obtenerProducto(idProducto)));
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
			System.out.println(Json.listarProductos(CatalogoProductos.obtenerProductos()));
		}
		catch (RespuestaServidor e)
		{
			System.out.println(e.toString());
		}
	}
	//endregion
}
