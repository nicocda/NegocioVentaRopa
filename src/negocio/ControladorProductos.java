package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import datosTODOparsear.CatalogoProductos;
import entidades.Producto;
import excepciones.RespuestaServidor;

public class ControladorProductos 
{
	public Producto obtenerProducto(int id) throws RespuestaServidor
	{
		CatalogoProductos cc = new CatalogoProductos();
		RespuestaServidor sr = validarProducto(id);
		Producto producto = null;
		
		if (!sr.getStatus())
			throw sr;
		
		try
		{
			producto = setProducto(cc.obtenerProducto(id));
		}
		catch(SQLException e)
		{
			sr.addError(e);
			throw sr;
		}
		
		return producto;
	}
	
	public ArrayList<Producto> obtenerProductos() throws RespuestaServidor
	{
		CatalogoProductos cc = new CatalogoProductos();
		RespuestaServidor sr = new RespuestaServidor();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		try
		{
			productos = setProductos(cc.obtenerProductos());
		}
		catch (SQLException e) 
		{
			sr.addError(e);
			throw sr;
		}
		
		return productos;
	}
	
	public ArrayList<Producto> obtenerProductos(int paginaActual, int porPagina) throws RespuestaServidor
	{	
		CatalogoProductos cc = new CatalogoProductos();
		RespuestaServidor sr = new RespuestaServidor();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		try
		{
			productos = setProductos(cc.obtenerProductos(paginaActual, porPagina));
		}
		catch (SQLException e) 
		{
			sr.addError(e);
			throw sr;
		}
		
		return productos;
	}
	
	public void guardarProducto(Producto producto) throws RespuestaServidor
	{
		ControladorPrecios cp = new ControladorPrecios();
		
		CatalogoProductos cc = new CatalogoProductos();
		RespuestaServidor sr = validarProducto(producto);
		
		if (!sr.getStatus())
			throw sr;
		
		try
		{
			if(cc.guardarProducto(producto))
			{
				if(producto.getNuevoPrecio() != null)
				{
					//cp.guardarPrecio(producto.getNuevoPrecio());
				}
			}
		}
		catch (SQLException e) 
		{
			sr.addError(e);
			throw sr;
		}
	}
	
	//region Validaciones
	private RespuestaServidor validarProducto(int idProducto)
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if (idProducto == 0)
			sr.addError("Error, no se provee id para la búsqueda.");
		
		return sr;
	}
	
	private RespuestaServidor validarProducto(Producto producto)
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if(producto.getId() == 0)
			sr.addError("Ocurrió un error interno. El id de producto es obligatorio.");
		
		if(!(producto.getDescripcion() != null && !producto.getDescripcion().isEmpty()))
			sr.addError("El producto debe tener una descripción.");
		
		if(producto.getPrecio() != null && producto.getPrecio().getPrecio() < 0)
			sr.addError("El precio ingresado no es válido.");
		
		return sr;
	}
	//endregion
	
	//region Privados
	private Producto setProducto(Producto producto)
	{
		ControladorSucursales cs = new ControladorSucursales();
		
		//El id de sucursal se había seteado en una instancia de sucursal vacía cuando se busca el producto
		//producto.setSucursal(cs.obtenerSucursal(producto.getSucursal().getId());
		//producto.setPrecios(cs.obtenerPrecios(producto.getId()));
		
		return producto;
	}
	
	private ArrayList<Producto> setProductos(ArrayList<Producto> productos)
	{
		for(Producto producto : productos)
		{
			producto = setProducto(producto);
		}
		
		return productos;
	}
	//endregion
}
