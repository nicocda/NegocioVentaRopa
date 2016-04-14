package conexion;
import java.sql.*;

public class DataConnection 
{
	//Singleton: Instanciar una única vez la conexion
	private static DataConnection instancia;
	public static DataConnection getInstancia()
	{
		if(instancia==null)
		{
			instancia=new DataConnection();
		}
		return instancia;
	}
	
	private static String dbUrl="jdbc:mysql://localhost:3306/ropa";
	private static String dbUser="root";
	private static String dbPassword="1234";
	
	//Construtor Default
	private DataConnection()
	{
	}
	
	//Abrir Conexión y manejo de errores
	private Connection conn;
	public Connection getConn()
	{
		try 
		{
			if(conn==null || !conn.isValid(3))
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);	
			}
			
		} catch (InstantiationException e) 
		{
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return conn;
	}
	
	//Cerrar conexió y manejo de errores
	public void CloseConn()
	{
		try {
			if(conn!=null && !conn.isClosed())
			{
				conn.close();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}