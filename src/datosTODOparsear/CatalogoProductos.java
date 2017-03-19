package datosTODOparsear;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Producto;
import excepciones.RespuestaServidor;

public class CatalogoProductos extends CatalogoBase
{
	public static Producto obtenerProducto(int idProducto) throws RespuestaServidor
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;

		RespuestaServidor sr = validarProducto(idProducto);
		
		if (!sr.getStatus())
			throw sr;
			
		Producto producto = null;
		String sql = "SELECT * FROM producto WHERE id = ?";
		
		try
		{
			sentencia = prepareStatement(sql);
			sentencia.setInt(1, idProducto);
			
			rs = sentencia.executeQuery();
			
			if(rs.next())
			{
				producto = setProducto(rs);			
			}
		}
		catch(SQLException e)
		{
			sr.addError(e);
			throw sr;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		return producto;
	}
	
	public static ArrayList<Producto> obtenerProductosPorPagina(int paginaActual, int porPagina) throws RespuestaServidor
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;

		RespuestaServidor sr = new RespuestaServidor();
		
		ArrayList<Producto> productos = new ArrayList<Producto>();	
		
		String sql = "SELECT * FROM producto WHERE activo = TRUE ORDER BY id LIMIT ?, ?";
		
		try
		{
			sentencia = prepareStatement(sql);
			sentencia.setInt(1, paginaActual);
			sentencia.setInt(2, porPagina);
			
			rs = sentencia.executeQuery();
			
			while(rs.next())
			{
				productos.add(setProducto(rs));
			}
		}
		catch(SQLException e)
		{
			sr.addError(e);
			throw sr;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return productos;
	}

	public static ArrayList<Producto> obtenerProductos() throws RespuestaServidor
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;

		RespuestaServidor sr = new RespuestaServidor();
		
		ArrayList<Producto> productos = new ArrayList<Producto>();	
		
		String sql = "SELECT * FROM producto ORDER BY id";
		
		try
		{
			sentencia = prepareStatement(sql);
			
			rs = sentencia.executeQuery();
			
			while(rs.next())
			{
				productos.add(setProducto(rs));
			}
		}
		catch(SQLException e)
		{
			sr.addError(e);
			throw sr;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return productos;
	}

	public static void guardarProducto(Producto producto) throws RespuestaServidor
	{
		PreparedStatement sentencia = null;

		RespuestaServidor sr = validarProducto(producto);
		
		if (!sr.getStatus())
			throw sr;
		
		String sql;
		
		if (producto.getId() == 0)
			sql = "INSERT INTO producto(descripcion, estado, codigoProducto, idSucursal) VALUES(?, ?, ?, ?)";
		else
		{
			if (obtenerProducto(producto.getId()) != null)
				sql = "UPDATE producto SET descripcion = ?, estado = ?, codigoProducto = ?, idSucursal = ? WHERE id = ?";
			else
			{
				sr.addError("Se está intentando actualizar un producto con id inexistente");
				throw sr;
			}
		}
			
		try
		{
			sentencia = prepareStatement(sql);
			
			sentencia.setString(1, producto.getDescripcion());
			sentencia.setInt(2, producto.getEstado());
			sentencia.setString(3, producto.getCodigoProducto());
			sentencia.setInt(4, producto.getSucursal().getId());
			
			if (producto.getId() != 0)
				sentencia.setInt(5, producto.getId());
			
			if(sentencia.executeUpdate() == 0)
			{
	            throw new SQLException("No hubo rows afectadas al intentar guardar producto");
	        }
			else
			{
				try (ResultSet generatedKeys = sentencia.getGeneratedKeys())
				{
					if (generatedKeys.next())
						producto.getNuevoPrecio().getProducto().setId(generatedKeys.getInt(1));
					else
						throw new SQLException("No se pudo obtener el id del producto guardado");
		        }
			}
			
			if (producto.getNuevoPrecio() != null)
				CatalogoPrecios.guardarPrecio(producto.getNuevoPrecio());
		}
		catch (SQLException e) 
		{
			sr.addError(e);
			throw sr;
		}
		finally
		{
			cerrarStatement(sentencia);
		}
	}
	
	public static int contarproductos()
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		
		int nroProductos = 0;
		
		String sql = "SELECT COUNT(*) FROM producto WHERE activo = TRUE";
		
		try
		{
			sentencia = prepareStatement(sql);
			
			rs = sentencia.executeQuery();
			
			if(rs.next())
			{
				nroProductos = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return nroProductos;
	}
	//endregion
	
	//region Privados
	private static Producto setProducto(ResultSet rs) throws RespuestaServidor
	{
		Producto producto = new Producto();
		
		try 
		{
			producto.setId(rs.getInt("id"));
			producto.setDescripcion(rs.getString("descripcion"));
			producto.setEstado(rs.getInt("estado"));
			
			producto.setSucursal(CatalogoSucursal.obtenerSucursal(rs.getInt("idSucursal")));
			producto.setPrecios(CatalogoPrecios.buscarPrecio(producto.getId()));
		} 
		catch (SQLException e) 
		{
			producto = null;
		}

		return producto;
	}
	//endregion
	
	//region Validaciones
	private static RespuestaServidor validarProducto(int idProducto)
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if (idProducto == 0)
			sr.addError("Error, no se provee id para la búsqueda.");
		
		return sr;
	}
	
	private static RespuestaServidor validarProducto(Producto producto)
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if(!(producto.getCodigoProducto() != null && !producto.getCodigoProducto().isEmpty()))
			sr.addError("Ocurrió un error interno. El id es obligatorio.");
		
		if(!(producto.getDescripcion() != null && !producto.getDescripcion().isEmpty()))
			sr.addError("El producto debe tener una descripción.");
		
		if(producto.getPrecio() != null && producto.getPrecio().getPrecio() < 0)
			sr.addError("El precio ingresado no es válido.");
		
		return sr;
	}
	//endregion
}
