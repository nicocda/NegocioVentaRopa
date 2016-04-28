package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import conexion.DataConnection;
import entidades.Precio;
import excepciones.RespuestaServidor;

public class CatalogoPrecios {

	public static Precio buscarPrecioProducto(String idProducto)
	{
		String sql="select * from precio p inner join "
				//hago un inner join con una consulta
		+ "(select max(fecha) as maxFecha from precio where idProducto=? and fecha <= NOW()) p2 "
		+ " on p.fecha=p2.maxFecha where idProducto = ?";
		PreparedStatement sentencia = null;
		Precio precio = null;
		try
		{
			sentencia = DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, idProducto);
			sentencia.setString(2, idProducto);
			ResultSet rs =sentencia.executeQuery();
			if(rs.next())
			{
				precio = new Precio();
				precio.setFecha(rs.getDate("fecha"));
				precio.setPrecio(rs.getFloat("precio"));
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
		return precio;
	}
	
	public static void agregarPrecio(float precio, String idProducto)
	{
		RespuestaServidor sr = new RespuestaServidor();
		String sql="insert into precio values (NOW(),?,?)";
		PreparedStatement sentencia = null;
		try
		{
			sentencia = DataConnection.getInstancia().getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setFloat(1, precio);
			sentencia.setString(2, idProducto);
			sentencia.executeUpdate();
		}
		catch(SQLException e)
		{	
			//e.printStackTrace();
			sr.addError(e);
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

