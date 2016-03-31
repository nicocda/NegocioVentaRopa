package datos;

import java.util.ArrayList;

import java.sql.*;
import conexion.DataConnection;
import entidades.Cliente;

public class CatalogoClientes {

	public ArrayList<Cliente> buscarTodosClientes()
	{
		String sql="select * from cliente order by nombreApellido";
		PreparedStatement sentencia = null;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs =sentencia.executeQuery();
			while(rs.next())
			{
				Cliente cl = new Cliente();
				cl.setDireccion(rs.getString("direccion"));
				cl.setNombre(rs.getString("nombreApellido"));
				cl.setTelefono(rs.getInt("telefono"));
				cl.setNroCliente(rs.getInt("nroCliente"));
				cl.setDeuda(rs.getFloat("deuda"));
				clientes.add(cl);
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
				Cliente cl = new Cliente();
				cl.setDireccion(rs.getString("direccion"));
				cl.setNombre(rs.getString("nombreApellido"));
				cl.setTelefono(rs.getInt("telefono"));
				cl.setNroCliente(rs.getInt("nroCliente"));
				cl.setDeuda(rs.getFloat("deuda"));
				clientes.add(cl);
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
	
	public void agregarCliente(Cliente cl)
	{
		String sql = "insert into cliente (nrCliente,nombreApellido,direccion,telefono,deuda) values (?,?,?,?,?)";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, cl.getNroCliente());
			sentencia.setString(2, cl.getNombre());
			sentencia.setString(4, cl.getDireccion());
			sentencia.setInt(5, cl.getTelefono());
			sentencia.setFloat(6, cl.getDeuda());
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

	public void eliminarCliente(int nroCliente)
	{
		String sql = "delete from cliente where nroCliente=?";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, nroCliente);
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
	
	public void modificarCliente(Cliente cl)
	{
		String sql = "update cliente set nrCliente=? ,nombreApellido=? ,direccion=? ,telefono=? ,deuda=? where nroCliente=?";
		PreparedStatement sentencia = null;
		try
		{
			sentencia=DataConnection.getInstancia().getConn().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, cl.getNroCliente());
			sentencia.setString(2, cl.getNombre());
			sentencia.setString(4, cl.getDireccion());
			sentencia.setInt(5, cl.getTelefono());
			sentencia.setFloat(6, cl.getDeuda());
			sentencia.setInt(7, cl.getNroCliente());
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


