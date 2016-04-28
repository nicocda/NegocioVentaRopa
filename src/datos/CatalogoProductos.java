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
import entidades.Venta;
import excepciones.RespuestaServidor;

public class CatalogoProductos
{
	
	public static ArrayList<Producto> buscarProductosDisponibles()
	{
		//String sql ="select * from producto p inner join precio s on p.id=s.idProducto where estado=1 order by id";//estado=1 es en stock
		String sql="select * from producto where estado=1 order by id";
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
				pr.setId(rs.getString("id"));
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
		for (Producto prod : productos)
		{
			prod.setPrecio(CatalogoPrecios.buscarPrecioProducto(prod.getId()));
		}
		return productos;
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
				pr.setId(rs.getString("id"));
				pr.setPrecio(CatalogoPrecios.buscarPrecioProducto(pr.getId()));
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
		for (Producto prod : productos)
		{
			prod.setPrecio(CatalogoPrecios.buscarPrecioProducto(prod.getId()));
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
				pr.setId(rs.getString("id"));
				pr.setPrecio(CatalogoPrecios.buscarPrecioProducto(pr.getId()));
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
		for (Producto prod : productos)
		{
			prod.setPrecio(CatalogoPrecios.buscarPrecioProducto(prod.getId()));
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
				pr.setId(rs.getString("id"));
				pr.setPrecio(CatalogoPrecios.buscarPrecioProducto(pr.getId()));
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
		for (Producto prod : productos)
		{
			prod.setPrecio(CatalogoPrecios.buscarPrecioProducto(prod.getId()));
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
				pr.setId(rs.getString("id"));
				pr.setPrecio(CatalogoPrecios.buscarPrecioProducto(pr.getId()));
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
		for (Producto prod : productos)
		{
			prod.setPrecio(CatalogoPrecios.buscarPrecioProducto(prod.getId()));
		}
		return productos;
	}
	
	public static Producto buscarProducto(String id)
	{
		String sql="select * from producto p inner join precio pr on p.id = pr.idProducto where id=?";
		PreparedStatement sentencia = null;
		Producto pr = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, id);
			ResultSet rs =sentencia.executeQuery();
			if(rs.next())
			{
				pr = new Producto();
				pr.setId(rs.getString("id"));
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
		if(pr!=null)
		{
			pr.setPrecio(CatalogoPrecios.buscarPrecioProducto(pr.getId()));
		}
		return pr;
	}

	public static void agregarProducto(Producto pr) throws RespuestaServidor
	{
		RespuestaServidor sr = validarProducto(pr);
		if(!sr.getStatus())
			throw sr;
		String sql="insert into producto (id,descripcion,estado) values(?,?,?)";
		PreparedStatement sentencia=null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, pr.getId());
			sentencia.setString(2, pr.getDescripcion());
			sentencia.setInt(3, pr.getEstado());
			sentencia.executeUpdate();
		}
		catch(SQLException e)
		{
			sr.addError(e);
			throw sr;
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
				sr.addError(sqle);
				throw sr;
			}
		}
			CatalogoPrecios.agregarPrecio(pr.getPrecio().getPrecio(), pr.getId());
	}
	
	private static RespuestaServidor validarProducto(Producto pr) 
	{
		RespuestaServidor sr = new RespuestaServidor();
		if(!(pr.getId() != null && !pr.getId().isEmpty()))
			sr.addError("Ocurrió un error interno. El id es obligatorio.");
		if(!(pr.getDescripcion() != null && !pr.getDescripcion().isEmpty()))
			sr.addError("El producto debe tener una descripción.");
		if(pr.getPrecio().getPrecio() < 0)
			sr.addError("El precio ingresado no es válido.");
		return sr;
	}

	public static void modificarProducto(Producto pr) throws RespuestaServidor
	{
		RespuestaServidor sr = validarProducto(pr);
		if(!sr.getStatus())
			throw sr;
		String sql="update producto set descripcion=?, estado=? where id=?";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, pr.getDescripcion());
			sentencia.setInt(2, pr.getEstado());
			sentencia.setString(3, pr.getId());
			sentencia.executeUpdate();
		}
		catch(SQLException e)
		{
			sr.addError(e);
			throw sr;
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
				sr.addError(sqle);
				throw sr;
			}
		}
		//Saco los rs porque el precio ya se valida apenas se entra a este metodo, por lo tanto despues ya no puede fallar, porque el throw me corta la ejecucion.
		Precio precio = CatalogoPrecios.buscarPrecioProducto(pr.getId());
			CatalogoPrecios.agregarPrecio(pr.getPrecio().getPrecio(), pr.getId());
	}
	
	public static void modificarEstadoProducto(String id, estado valor)
	{
		String sql="update producto set estado=? where id=?"; //estado=0 es vendido 
		PreparedStatement sentencia = null;
		try
		{
			sentencia = DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, valor.ordinal());
			sentencia.setString(2, id);
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
				sentencia.setString(3, pr.getId());
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
				sentencia.setString(3, pr.getId());
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
	
	public static String ultimoIdProducto(char tipo, char subTipo)
	{
		String sql="select id from producto where id like ? order by id desc limit  1";
		PreparedStatement sentencia = null;
		String ultProd= null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, Character.toString(tipo).concat(Character.toString(subTipo) + "%"));
			ResultSet rs = sentencia.executeQuery();
			if(rs.next())
			{
				ultProd=rs.getString("id");
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
		return ultProd;
	}
}
