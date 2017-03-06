package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
	//Singleton: Instanciar una única vez la conexion
	private static Conexion instancia;
	public static Conexion getInstancia()
	{
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}
	
	private static String dbUrl="jdbc:mysql://localhost:3306/ropa";
	private static String dbUser="root";
	private static String dbPassword="root";
	
	private Connection conn;
	static int cantCon=0;
	
	//Construtor Default
	private Conexion()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=null;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public Connection getConn()
	{
		try 
		{
			if(conn==null || !conn.isValid(3))
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			}		
			
			cantCon++;
			System.out.println("Nueva conexión abierta -> cantidad de conexiones abiertas: " + cantCon);
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	//Cerrar conexión y manejo de errores
	public void CloseConn()
	{
		try
		{
			cantCon--;
			System.out.println("Se cerró una conexión -> cantidad de conexiones abiertas: " + cantCon);
			if(cantCon==0)
			{
				conn.close();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void startTransaction() throws SQLException
	{
		conn.setAutoCommit(true);
	}
	
	public void commit() throws SQLException
	{
		conn.commit();
		conn.setAutoCommit(false);
	}
	
	public void rollback() throws SQLException
	{
		conn.rollback();
		conn.setAutoCommit(false);
	}
}
