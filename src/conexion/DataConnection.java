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
	private static String dbPassword="123peretto";
	
	private Connection conn;
	private int cantCon;
	
	//Construtor Default
	private DataConnection()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn=null;
			cantCon=0;
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	//Abrir Conexión y manejo de errores
	
	public Connection getConn()
	{
		try 
		{
			if(conn==null || !conn.isValid(3))
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
				cantCon++;
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
		try
		{
			cantCon--;
			if(cantCon==0)
			{
				conn.close();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}