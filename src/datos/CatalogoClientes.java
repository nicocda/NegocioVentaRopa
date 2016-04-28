package datos;

import java.util.ArrayList;

import java.sql.*;
import conexion.DataConnection;
import entidades.Cliente;
import excepciones.RespuestaServidor;

public class CatalogoClientes 
{
	public static ArrayList<Cliente> buscarClientes()
	{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String sql="select * from cliente order by nombreApellido";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs =sentencia.executeQuery();
			while(rs.next())
			{
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNombreApellido(rs.getString("nombreApellido"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(rs.getString("telefono"));
				clientes.add(cliente);
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
		return clientes;
	}
	
	public static ArrayList<Cliente> buscarClientes(String nombreApellido)
	{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();  
		PreparedStatement sentencia = null;
		String sql= "select * from cliente where nombreApellido like ? order by nombreApellido";
		try
		{
			sentencia = DataConnection.getInstancia().getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, nombreApellido+"%");
			ResultSet rs = sentencia.executeQuery();
			while(rs.next())
			{
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNombreApellido(rs.getString("nombreApellido"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(rs.getString("telefono"));
				clientes.add(cliente);
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
		
		return clientes;
	}
	
	public static void agregarCliente(Cliente cliente) throws RespuestaServidor
	{
		RespuestaServidor sr = new RespuestaServidor();
		sr=validarClienteAlta(cliente);
		if(!sr.getStatus())
		{
			throw sr;
		}
		String sql = "insert into cliente (nombreApellido, direccion, telefono) values (?,?,?)";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, cliente.getNombreApellido());
			sentencia.setString(2, cliente.getDireccion());
			sentencia.setString(3, cliente.getTelefono());
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
	}
	
	private static RespuestaServidor validarClienteAlta(Cliente cl)
	{
		RespuestaServidor sr = new RespuestaServidor();
		if(!(cl.getNombreApellido() != null && !cl.getNombreApellido().isEmpty()))
			sr.addError("El cliente debe tener un nombre.");
		return sr;
	}
	private static RespuestaServidor validarClienteUpdate(Cliente cl)
	{
		RespuestaServidor sr = new RespuestaServidor();
		if(cl.getId()<0)
			sr.addError("Ocurrió un error interno. El id no es válido");
		if(!(cl.getNombreApellido() != null && !cl.getNombreApellido().isEmpty()))
			sr.addError("El cliente debe tener un nombre.");
		return sr;
	}

	public static void eliminarCliente(int id)
	{
		String sql = "delete from cliente where id=?";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, id);
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
	
	public static void modificarCliente(Cliente cliente) throws RespuestaServidor
	{
		RespuestaServidor sr = new RespuestaServidor();
		sr=validarClienteUpdate(cliente);
		if(!sr.getStatus())
		{
			throw sr;
		}
		String sql = "update cliente set id=? ,nombreApellido=? ,direccion=? ,telefono=? where id=?";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, cliente.getId());
			sentencia.setString(2, cliente.getNombreApellido());
			sentencia.setString(3, cliente.getDireccion());
			sentencia.setString(4, cliente.getTelefono());
			sentencia.setInt(5, cliente.getId());
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
	}

	public static Cliente buscarCliente(int id) throws RespuestaServidor
	{
		RespuestaServidor sr = new RespuestaServidor();
		sr = validarCliente(id);
		if(!sr.getStatus())
		{
			throw sr;
		}
		Cliente cliente = null;
		String sql="select * from cliente where id=?";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, id);
			ResultSet rs =sentencia.executeQuery();
			if(rs.next())
			{
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNombreApellido(rs.getString("nombreApellido"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(rs.getString("telefono"));
				
			}
		}
		catch(SQLException e)
		{
			sr.addError(e);
			throw sr;
		}
		return cliente;
	}
	
	private static RespuestaServidor validarCliente(int id)
	{
		RespuestaServidor sr = new RespuestaServidor();
		if (id <= 0)
		{
			sr.addError("El id de usuario ingresado es inválido.");
		}
		return sr;
	}
	public static Cliente buscarCliente(String nombreCliente) { 
		PreparedStatement sentencia = null;
		Cliente cliente = null;
		String sql= "select * from cliente where nombreApellido like ? order by nombreApellido";
		try
		{
			sentencia = DataConnection.getInstancia().getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, nombreCliente+"%");
			ResultSet rs = sentencia.executeQuery();
			if(rs.next())
			{
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNombreApellido(rs.getString("nombreApellido"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(rs.getString("telefono"));
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
		
		return cliente;
	
	}
}


