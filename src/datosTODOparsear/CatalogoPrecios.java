package datosTODOparsear;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Precio;
import excepciones.RespuestaServidor;

public class CatalogoPrecios extends CatalogoBase
{
	public static ArrayList<Precio> buscarPrecio(int idProducto) throws RespuestaServidor
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		ResultSet rs = null;
		PreparedStatement sentencia = null;
			
		ArrayList<Precio> precios = new ArrayList<Precio>();
		
		String sql = "SELECT * FROM precio WHERE idProducto = ?";
		
		try
		{
			sentencia = prepareStatement(sql);
			sentencia.setInt(1, idProducto);
			
			rs = sentencia.executeQuery();
			
			while(rs.next())
			{
				precios.add(setPrecio(rs));			
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
		
		return precios;
	}

	public static void guardarPrecio(Precio precio) throws RespuestaServidor
	{
		RespuestaServidor sr = validarPrecio(precio);
		
		if (!sr.getStatus())
			throw sr;
		
		PreparedStatement sentencia = null;
					
		String sql = "INSERT INTO Precio (precio, idProducto) VALUES (?, ?)";
		
		try
		{
			sentencia = prepareStatement(sql);
			
			sentencia.setFloat(1, precio.getPrecio());
			sentencia.setInt(2, precio.getProducto().getId());	
			
			sentencia.executeUpdate();
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
	
	//region Privados
	private static Precio setPrecio(ResultSet rs)
	{
		Precio precio = new Precio();
		
		try 
		{
			precio.setPrecio(rs.getFloat("precio"));
			precio.setFecha(rs.getTimestamp("fecha"));
		} 
		catch (SQLException e) 
		{
			precio = null;
		}
		
		return precio;
	}
	//endregion
	
	//region Validaciones
	private static RespuestaServidor validarPrecio(Precio precio)
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		return sr;
	}
	//endregion
}
