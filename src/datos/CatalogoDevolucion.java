package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.DataConnection;
import entidades.Devolucion;
import entidades.Producto;
import entidades.Producto.estado;


public class CatalogoDevolucion
{
	public static ArrayList<Devolucion> buscarDevoluciones()
	{
		String sql="select * from devolucion d order by fechaDevolucion desc";
		PreparedStatement sentencia = null;
		ArrayList<Devolucion> devoluciones = new ArrayList<Devolucion>();
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs =sentencia.executeQuery();
			
			while(rs.next())
			{
				Devolucion d = new Devolucion();
				d.setFecha(rs.getDate("fechaDevolucion"));
				d.setId(rs.getInt("id"));
				d.setVenta(CatalogoVentas.buscarVenta(rs.getInt("idVenta")));
				//busco los productos en el catalogo de productos y los seteo en la devolucion
				d.setProductos(CatalogoProductos.buscarProductosDevolucion(d.getId()));
				devoluciones.add(d);
			}
			
		}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
		finally
			{
				try
				{
					if(sentencia!=null && !sentencia.isClosed())
					{
						sentencia.close();
					}
					DataConnection.getInstancia().CloseConn();
				}
				catch (SQLException sqle)
				{
					sqle.printStackTrace();
				}
			}
		return devoluciones;
	}
	
	public static void registrarDevolucion(Devolucion dev)
	{
		String sql="insert into devolucion (fechaDevolucion,idVenta) values (?,?)";
		PreparedStatement sentencia = null;
		int idDevolucion = -1;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setDate(1, new java.sql.Date(dev.getFecha().getTime()));
			sentencia.setInt(2, dev.getVenta().getId());
			int rowsAfectadas = sentencia.executeUpdate();
			 if (rowsAfectadas == 0) 
				 throw new SQLException("Creating user failed, no rows affected.");
	        try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) 
	        {
	            if (generatedKeys.next()) 
	            {
	                idDevolucion = generatedKeys.getInt(1);
	            }
	            else 
	            {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			
		}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
		finally
			{
				try
				{
					if(sentencia!=null && !sentencia.isClosed())
					{
						sentencia.close();
					}
					DataConnection.getInstancia().CloseConn();
				}
				catch (SQLException sqle)
				{
					sqle.printStackTrace();
				}
				CatalogoProductos.devolverProducto(idDevolucion, dev.getProductos());
			}
	}
}
