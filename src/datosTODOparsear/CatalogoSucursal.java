package datosTODOparsear;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Sucursal;
import excepciones.RespuestaServidor;

public class CatalogoSucursal extends CatalogoBase 
{
	//region Públicos
	public static Sucursal obtenerSucursal(int idSucursal) throws RespuestaServidor
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;

		RespuestaServidor sr = validarSucursal(idSucursal);
		
		if (!sr.getStatus())
			throw sr;
			
		Sucursal sucursal = null;
		String sql = "SELECT * FROM sucursal WHERE id = ?";
		
		try
		{
			sentencia = prepareStatement(sql);
			sentencia.setInt(1, idSucursal);
			
			rs = sentencia.executeQuery();
			
			if(rs.next())
			{
				sucursal = setSucursal(rs);			
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
		
		return sucursal;
	}
	
	//endregion

	//region Validaciones
	private static RespuestaServidor validarSucursal(int idSucursal)
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if(idSucursal <= 0)
			sr.addError("El id de sucursal que se intenta buscar es inváildo.");
		
		return sr;
	}
	//endregion

	//region Privados
	private static Sucursal setSucursal(ResultSet rs)
	{
		Sucursal sucursal = new Sucursal();
		
		try
		{
			sucursal.setId(rs.getInt("id"));
			sucursal.setDireccion(rs.getString("direccion"));
			sucursal.setLocalidad(rs.getString("localidad"));
		}
		catch(SQLException e)
		{
			sucursal = null;
		}
		
		return sucursal;
	}
	//endregion
}
