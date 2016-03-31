package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.DataConnection;
import entidades.Producto;

public class CatalogoProductos {
	
	public ArrayList<Producto> buscarTodosProductos()
	{
		String sql="select * from producto p where estado=1 order by codProducto";//estado=1 es en stock
		PreparedStatement sentencia = null;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs =sentencia.executeQuery();
			while(rs.next())
			{
				Producto pr = new Producto();
				pr.setCodProducto(rs.getString("codProducto"));
				pr.setDescripcion(rs.getString("descripcion"));
				pr.setEstado(rs.getInt("estado"));
				productos.add(pr);
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
		return productos;
	}
	
	public ArrayList<Producto> buscarProductosPorTipo(char tipo)
	{
		String sql="select * from producto p where estado=1 and codProducto like ? order by codProducto";//estado=1 es en stock
		PreparedStatement sentencia = null;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, tipo+"%");
			ResultSet rs =sentencia.executeQuery();
			while(rs.next())
			{
				Producto pr = new Producto();
				pr.setCodProducto(rs.getString("codProducto"));
				pr.setDescripcion(rs.getString("descripcion"));
				pr.setEstado(rs.getInt("estado"));
				productos.add(pr);
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
		return productos;
	}
	
	public ArrayList<Producto> buscarProductosPorSubTipo(char tipo, char sub)
	{
		String sql="select * from producto p where estado=1 and codProducto like ? order by codProducto";//estado=1 es en stock
		PreparedStatement sentencia = null;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, tipo+sub+"%");
			ResultSet rs =sentencia.executeQuery();
			while(rs.next())
			{
				Producto pr = new Producto();
				pr.setCodProducto(rs.getString("codProducto"));
				pr.setDescripcion(rs.getString("descripcion"));
				pr.setEstado(rs.getInt("estado"));
				productos.add(pr);
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
		return productos;
	}
	
	public Producto buscarProducto(String codProducto)
	{
		String sql="select * from producto p where estado=1 and codProcuto=?";//estado=1 es en stock
		PreparedStatement sentencia = null;
		Producto pr = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, codProducto);
			ResultSet rs =sentencia.executeQuery();
			while(rs.next())
			{
				pr = new Producto();
				pr.setCodProducto(rs.getString("codProducto"));
				pr.setDescripcion(rs.getString("descripcion"));
				pr.setEstado(rs.getInt("estado"));
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

	public void agregarProducto(Producto pr)
	{
		String sql="insert into producto (codProducto,descripcion,estado) values(?,?,?)";
		PreparedStatement sentencia=null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, pr.getCodProducto());
			sentencia.setString(2, pr.getDescripcion());
			sentencia.setInt(3, pr.getEstado());
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
	
	public void modificarProducto(Producto pr)
	{
		String sql="update producto set codProducto=?, descripcion=?, estado=? where codProducto=?";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, pr.getCodProducto());
			sentencia.setString(2, pr.getDescripcion());
			sentencia.setInt(3, pr.getEstado());
			sentencia.setString(4, pr.getCodProducto());
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
	
	public void eliminarProducto(String codProducto)
	{
		String sql="update producto estado=0 where codProducto=?"; //estado=0 es vendido 
		PreparedStatement sentencia = null;
		try
		{
			sentencia = DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, codProducto);
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
	
	public void señaProducto(String codProducto)
	{
		String sql="update producto estado=2 where codProducto=?"; //estado=2 es producto señado
		PreparedStatement sentencia = null;
		try
		{
			sentencia = DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, codProducto);
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
