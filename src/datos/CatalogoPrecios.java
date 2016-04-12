package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import conexion.DataConnection;
import entidades.Precio;

public class CatalogoPrecios {

	public static Precio buscarPrecioProducto(String idProducto)
	{
		//Creo tabla temporal, busco mayor fecha que no sobrepase la de hoy(para ese producto)  y joineo con precio para el precio
		String sql="drop temporary table if exists precio2; create temporary table precio2{select max(fecha) as maxFecha from precio where idProducto=? and fecha <= NOW()}; "
		+ "select * from precio p inner join precio2 p2 on p.fecha=p2.maxFecha where idProducto = ?";
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
		//No cierro la conexion porque son metodos que se usan en otro catalogo
		return precio;
	}
	
	public static void agregarPrecio(float precio, String idProducto)
	{
		String sql="insert into precios values (NOW(),?,?)";
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
