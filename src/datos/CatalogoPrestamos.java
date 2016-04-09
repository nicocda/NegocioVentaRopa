package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import conexion.DataConnection;
import entidades.Prestamo;
import entidades.Venta.formaPago;

public class CatalogoPrestamos {

	public static ArrayList<Prestamo> buscarPrestamos()
	{
		String sql="select * from venta where isPrestamo = True";
		ArrayList<Prestamo> prestamos = null;
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = sentencia.executeQuery();
			while(rs.next())
			{
				Prestamo pr = new Prestamo();
				pr.setCliente(CatalogoClientes.buscarCliente(rs.getInt("idCliente")));
				pr.setFechaVenta(rs.getDate("fechaVenta"));
				pr.setFormaPago(formaPago.NULL.ordinal());
				pr.setId(rs.getInt("id"));
				pr.setImporte(0);
				pr.setProductos(CatalogoProductos.buscarProductosVenta(pr.getId()));
				prestamos.add(pr);
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
		return prestamos;
	}
	
	public static ArrayList<Prestamo> buscarPrestamos(String nombApe)
	{
		String sql="select * from venta v inner join cliente cl on v.idCliente=cl.id where isPrestamo = True and nombreApellido like ?";
		ArrayList<Prestamo> prestamos = null;
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, nombApe+'%');
			ResultSet rs = sentencia.executeQuery();
			while(rs.next())
			{
				Prestamo pr = new Prestamo();
				pr.setCliente(CatalogoClientes.buscarCliente(rs.getInt("idCliente")));
				pr.setFechaVenta(rs.getDate("fechaVenta"));
				pr.setFormaPago(formaPago.NULL.ordinal());
				pr.setId(rs.getInt("v.id"));
				pr.setImporte(0);
				pr.setProductos(CatalogoProductos.buscarProductosVenta(pr.getId()));
				prestamos.add(pr);
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
		return prestamos;
	}
	
	public static Prestamo buscarPrestamo(int id)
	{
		String sql="select * from venta where isPrestamo = True and id=?";
		Prestamo pr = null;
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, id);
			ResultSet rs = sentencia.executeQuery();
			while(rs.next())
			{
				pr = new Prestamo();
				pr.setCliente(CatalogoClientes.buscarCliente(rs.getInt("idCliente")));
				pr.setFechaVenta(rs.getDate("fechaVenta"));
				pr.setFormaPago(formaPago.NULL.ordinal());
				pr.setId(rs.getInt("id"));
				pr.setImporte(0);
				pr.setProductos(CatalogoProductos.buscarProductosVenta(pr.getId()));
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
		return pr;
	}
	public static void registrarPrestamo(Prestamo pr)
	{
		String sql="insert into venta (idCliente, fechaVenta,isPrestamo) values (?,?,True)";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, pr.getCliente().getId());
			sentencia.setDate(2, new java.sql.Date(pr.getFechaVenta().getTime()));
			sentencia.executeUpdate();
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
	}
}
