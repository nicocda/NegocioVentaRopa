package datosTODOparsear;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import conexion.Conexion;

public class CatalogoBase 
{	
	protected static void cerrarStatement(PreparedStatement sentencia)
	{
		try
		{
			if(sentencia!=null && !sentencia.isClosed())
			{
				sentencia.close();
			}
			Conexion.getInstancia().CloseConn();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
	
	protected static void cerrarStatement(Statement sentencia)
	{
		try
		{
			if(sentencia!=null && !sentencia.isClosed())
			{
				sentencia.close();
			}
			Conexion.getInstancia().CloseConn();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}
	
	
	protected static Connection nuevaCon()
	{
		return Conexion.getInstancia().getConn();
	}
	
	protected static PreparedStatement prepareStatement(String sql) 
	{
		PreparedStatement statement;
		
		try 
		{
			statement = nuevaCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		} 
		catch (SQLException e) 
		{
			statement = null;
		}
		
		return statement;
	}
}