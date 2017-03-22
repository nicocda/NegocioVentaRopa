package datosTODOparsear;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import constantes.EstadoProducto;
import entidades.Producto;
import entidades.Sucursal;
import excepciones.RespuestaServidor;

public class CatalogoProductos extends CatalogoBase
{
	public Producto obtenerProducto(int idProducto) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
			
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
			throw e;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return producto;
	}
	
	public ArrayList<Producto> obtenerProductos(int paginaActual, int porPagina) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		
		ArrayList<Producto> productos = new ArrayList<Producto>();	
		
		String sql = "SELECT * FROM producto ORDER BY id LIMIT ?, ?";
		
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
			throw e;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return productos;
	}

	public ArrayList<Producto> obtenerProductos() throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		
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
			throw e;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return productos;
	}

	public boolean guardarProducto(Producto producto) throws SQLException
	{
		PreparedStatement sentencia = null;
		
		String sql;
		
		if (producto.getId() == 0)
			sql = "INSERT INTO producto(descripcion, estado, codigoProducto, idSucursal) VALUES(?, ?, ?, ?)";
		else
			sql = "UPDATE producto SET descripcion = ?, estado = ?, codigoProducto = ?, idSucursal = ? WHERE id = ?";
			
		try
		{
			sentencia = prepareStatement(sql);
			
			sentencia.setString(1, producto.getDescripcion());
			sentencia.setInt(2, producto.getEstado().getEstado());
			sentencia.setString(3, producto.getCodigoProducto());
			sentencia.setInt(4, producto.getSucursal().getId());
			
			if (producto.getId() != 0)
				sentencia.setInt(5, producto.getId());
			
			return sentencia.executeUpdate() != 0;
		}
		catch (SQLException e) 
		{
			throw e;
		}
		finally
		{
			cerrarStatement(sentencia);
		}
	}
	
	public int contarproductos()
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		
		int nroProductos = 0;
		
		String sql = "SELECT COUNT(*) FROM producto";
		
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
	private Producto setProducto(ResultSet rs)
	{
		Producto producto = new Producto();
		
		try 
		{
			producto.setId(rs.getInt("id"));
			producto.setDescripcion(rs.getString("descripcion"));
			producto.setEstado(new EstadoProducto().getEstado(rs.getInt("estado")));
			
			Sucursal sucursal = new Sucursal();
			sucursal.setId(rs.getInt("idSucursal"));
			
			producto.setSucursal(sucursal);
		} 
		catch (SQLException e) 
		{
			producto = null;
		}

		return producto;
	}
	//endregion
}
