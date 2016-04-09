package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.DataConnection;
import entidades.Precio;
import entidades.Producto;
import entidades.Producto.estado;
import excepciones.RespuestaServidor;

public class CatalogoProductos
{
	
	public static Object[] buscarProductosDisponibles()
	{
		String sql="select * from producto p inner join precio s on p.id=s.idProducto where estado=1 order by id";//estado=1 es en stock
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
				pr.setId(rs.getInt("id"));
				//el setPrecio() lleva como parametro una instancia de precio (new Precio())
				pr.setPrecio(new Precio(rs.getDate("fecha"),rs.getFloat("precio")));
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
		Object[] retorno= { validar(productos), productos }; 
		return retorno;
	}
	
	private static RespuestaServidor validar(ArrayList<Producto> productos) 
	{
		RespuestaServidor rs = new RespuestaServidor();
		
		if (productos.isEmpty())
		{
			ArrayList<String> as = rs.getErrors();
			rs.getErrors().add("No hay productos que mostrar.");
		}
		
		return rs;
	}

	public static ArrayList<Producto> buscarProductosVenta(int idVenta)
	{
		String sql="select * from producto p inner join precio s on p.id=s.idProducto where idVenta=? order by id";
		PreparedStatement sentencia = null;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, idVenta);
			ResultSet rs =sentencia.executeQuery();
			while(rs.next())
			{
				Producto pr = new Producto();
				pr.setId(rs.getInt("id"));
				//el setPrecio() lleva como parametro una instancia de precio (new Precio())
				pr.setPrecio(new Precio(rs.getDate("fecha"),rs.getFloat("precio")));
				pr.setDescripcion(rs.getString("descripcion"));
				pr.setEstado(rs.getInt("estado"));
				productos.add(pr);
			}
		}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
		return productos;
	}
	
	public static ArrayList<Producto> buscarProductosDevolucion(int idDevolucion)
	{
		String sql="select * from producto p inner join precio s on p.id=s.idProducto where idDevolucion=? order by id";
		PreparedStatement sentencia = null;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, idDevolucion);
			ResultSet rs =sentencia.executeQuery();
			while(rs.next())
			{
				Producto pr = new Producto();
				pr.setId(rs.getInt("id"));
				//el setPrecio() lleva como parametro una instancia de precio (new Precio())
				pr.setPrecio(new Precio(rs.getDate("fecha"),rs.getFloat("precio")));
				pr.setDescripcion(rs.getString("descripcion"));
				pr.setEstado(rs.getInt("estado"));
				productos.add(pr);
			}
		}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
		return productos;
	}
	
	public static ArrayList<Producto> buscarProductosPorTipo(int tipo)
	{
		String sql="select * from producto p inner join precio s on s.idProducto=p.id where estado=1 and id like ? order by id";//estado=1 es en stock
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
				pr.setId(rs.getInt("id"));
				//el setPrecio() lleva como parametro una instancia de precio (new Precio())
				pr.setPrecio(new Precio(rs.getDate("fecha"),rs.getFloat("precio")));
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
	
	public static ArrayList<Producto> buscarProductosPorSubTipo(int tipo, int sub)
	{
		String sql="select * from producto p inner join precio s on s.idProducto=p.id where estado=1 and id like ? order by id";//estado=1 es en stock
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
				pr.setId(rs.getInt("id"));
				//el setPrecio() lleva como parametro una instancia de precio (new Precio())
				pr.setPrecio(new Precio(rs.getDate("fecha"),rs.getFloat("precio")));
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
	
	public static Producto buscarProducto(int id)
	{
		String sql="select * from producto p inner join precio pr on p.id = pr.idProducto where id=?";
		PreparedStatement sentencia = null;
		Producto pr = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, id);
			ResultSet rs =sentencia.executeQuery();
			if(rs.next())
			{
				pr = new Producto();
				pr.setId(rs.getInt("id"));
				//el setPrecio() lleva como parametro una instancia de precio (new Precio())
				pr.setPrecio(new Precio(rs.getDate("fecha"),rs.getFloat("precio")));
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

	public static void agregarProducto(Producto pr)
	{
		String sql="insert into producto (id,descripcion,estado) values(?,?,?)";
		PreparedStatement sentencia=null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, pr.getId());
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
	
	public static void modificarProducto(Producto pr)
	{
		String sql="update producto set id=?, descripcion=?, estado=? where id=?";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, pr.getId());
			sentencia.setString(2, pr.getDescripcion());
			sentencia.setInt(3, pr.getEstado());
			sentencia.setInt(4, pr.getId());
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
	
	public static void modificarEstadoProducto(int id, estado valor)
	{
		String sql="update producto set estado=? where id=?"; //estado=0 es vendido 
		PreparedStatement sentencia = null;
		try
		{
			sentencia = DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, valor.ordinal());
			sentencia.setInt(2, id);
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

	public static void venderProducto(int idVenta, ArrayList<Producto> listaProductos)
	{
		String sql="update producto set estado=?, idVenta=? where id=?";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			for(Producto pr : listaProductos)
			{
				sentencia.setInt(1, estado.VENDIDO.ordinal());
				sentencia.setInt(2, idVenta);
				sentencia.setInt(3, pr.getId());
				sentencia.executeUpdate();
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
	}
	
	public static void devolverProducto(int idDevolucion, ArrayList<Producto> listaProductos)
	{
		String sql="update producto set estado=?, idDevolucion=? where id=?";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			for(Producto pr : listaProductos)
			{
				sentencia.setInt(1, estado.STOCK.ordinal());
				sentencia.setInt(2, idDevolucion);
				sentencia.setInt(3, pr.getId());
				sentencia.executeUpdate();
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
	}
	
}
