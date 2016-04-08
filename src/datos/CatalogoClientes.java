package datos;

import java.util.ArrayList;

import java.sql.*;
import conexion.DataConnection;
import entidades.Cliente;

public class CatalogoClientes 
{
	public ArrayList<Cliente> buscarClientes()
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
	
	public ArrayList<Cliente> buscarClienteNombreApellido(String nombreApellido)
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
	
	public void agregarCliente(Cliente cliente)
	{
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

	public void eliminarCliente(int id)
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
	
	public void modificarCliente(Cliente cliente)
	{
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


