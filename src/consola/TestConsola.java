package consola;

import java.sql.Timestamp;
import java.util.ArrayList;

import constantes.EstadoProducto;
import constantes.FormaPago;
import constantes.TipoUsuario;
import datosTODOparsear.CatalogoClientes;
import datosTODOparsear.CatalogoProductos;
import datosTODOparsear.CatalogoSucursales;
import datosTODOparsear.CatalogoUsuarios;
import datosTODOparsear.CatalogoVentas;
import entidades.Cliente;
import entidades.Precio;
import entidades.Producto;
import entidades.Sucursal;
import entidades.Usuario;
import entidades.Venta;
import excepciones.RespuestaServidor;
import json.Json;
import negocio.ControladorClientes;

public class TestConsola 
{
	private static ControladorClientes cc = new ControladorClientes();
	
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
		//testListarProductos();
		//testListarProductosPorPagina(1,2);
		//testContarProdutos();
		//testGuardarProducto(0);
		
		//testRegistrarVenta();
	}	
	
	//region Privados
	private static void testBuscarCliente(int idCliente)
	{
		try
		{			
			System.out.println(cc.obtenerCliente(idCliente).toJson());
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
			System.out.println(Json.listar(cc.obtenerClientes()));
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
			System.out.println(Json.listar(cc.obtenerClientes(paginaActual, porPagina)));
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
			cc.guardarCliente(cliente);
		}
		catch (RespuestaServidor e)
		{
			System.out.println(e.toString());
		}
	}
	
	private static void testContarClientes()
	{
		System.out.println(cc.contarClientes());
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
		usuario.setTipoUsuario(TipoUsuario.ADMIN);
		
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
	
	private static void testListarProductos()
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
	
	private static void testListarProductosPorPagina(int paginaActual, int porPagina)
	{
		try
		{
			System.out.println(Json.listar(CatalogoProductos.obtenerProductosPorPagina(paginaActual, porPagina)));
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
	
	private static void testContarProdutos()
	{
		System.out.println(CatalogoProductos.contarproductos());
	}
	
	private static void testRegistrarVenta()
	{
		Venta venta = new Venta();
		
		try
		{
			ArrayList<Producto> listaProductos = new ArrayList<Producto>();
			
			listaProductos.add(CatalogoProductos.obtenerProducto(1));
			listaProductos.add(CatalogoProductos.obtenerProducto(5));

			venta.setCliente(cc.obtenerCliente(67));
			venta.setFechaVenta(new Timestamp(System.currentTimeMillis()));
			venta.setFormaPago(FormaPago.EFECTIVO);
			venta.setProductos(listaProductos);
			venta.setSucursal(CatalogoSucursales.obtenerSucursal(1));
			
			CatalogoVentas.registrarVenta(venta);
		}
		catch (RespuestaServidor e) 
		{
			System.out.println(e.toString());
		}		
	}
	//endregion
}
