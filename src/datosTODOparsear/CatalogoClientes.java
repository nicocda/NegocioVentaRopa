package datosTODOparsear;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cliente;
import excepciones.RespuestaServidor;

public class CatalogoClientes extends CatalogoBase
{
	//region Públicos
	public Cliente obtenerCliente(int idCliente) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
			
		Cliente cliente = null;
		String sql = "SELECT * FROM cliente WHERE id = ?";
		
		try
		{
			sentencia = prepareStatement(sql);
			sentencia.setInt(1, idCliente);
			
			rs = sentencia.executeQuery();
			
			if(rs.next())
			{
				cliente = setCliente(rs);			
			}
		}
		catch(SQLException e)
		{
			throw e;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return cliente;
	}
	
	public ArrayList<Cliente> obtenerClientes(int paginaActual, int porPagina) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();	
		
		String sql = "SELECT * FROM cliente WHERE activo = TRUE ORDER BY id LIMIT ?, ?";
		
		try
		{
			sentencia = prepareStatement(sql);
			sentencia.setInt(1, paginaActual);
			sentencia.setInt(2, porPagina);
			
			rs = sentencia.executeQuery();
			
			while(rs.next())
			{
				clientes.add(setCliente(rs));
			}
		}
		catch(SQLException e)
		{
			throw e;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return clientes;
	}

	public ArrayList<Cliente> obtenerClientes() throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();	
		
		String sql = "SELECT * FROM cliente WHERE activo = TRUE ORDER BY id";
		
		try
		{
			sentencia = prepareStatement(sql);
			
			rs = sentencia.executeQuery();
			
			while(rs.next())
			{
				clientes.add(setCliente(rs));
			}
		}
		catch(SQLException e)
		{
			throw e;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return clientes;
	}

	public void guardarCliente(Cliente cliente) throws SQLException
	{
		PreparedStatement sentencia = null;
		
		String sql;
		
		if (cliente.getId() == 0)
			sql = "INSERT INTO cliente(nombre, apellido, telefono, direccion, activo) VALUES(?, ?, ?, ?, ?)";
		else
			sql = "UPDATE cliente SET nombre = ?, apellido = ?, telefono = ?, direccion = ?, activo = ? WHERE id = ?";
			
		try
		{
			sentencia = prepareStatement(sql);
			
			sentencia.setString(1, cliente.getNombre());
			sentencia.setString(2, cliente.getApellido());
			sentencia.setString(3, cliente.getTelefono());
			sentencia.setString(4, cliente.getDireccion());
			sentencia.setBoolean(5, cliente.isActivo());
			
			if (cliente.getId() != 0)
				sentencia.setInt(6, cliente.getId());
			
			sentencia.executeUpdate();
		}
		catch (SQLException e) 
		{
			throw e;
		}
		finally
		{
			cerrarStatement(sentencia);
		}
	}
	
	public int contarClientes()
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		
		int nroClientes = 0;
		
		String sql = "SELECT COUNT(*) FROM cliente WHERE activo = TRUE";
		
		try
		{
			sentencia = prepareStatement(sql);
			
			rs = sentencia.executeQuery();
			
			if(rs.next())
			{
				nroClientes = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return nroClientes;
	}
	
	//endregion
	
	//region Privados
	private Cliente setCliente(ResultSet rs)
	{
		Cliente cliente = new Cliente();
		
		try 
		{
			cliente.setNombre(rs.getString("nombre"));
			cliente.setApellido(rs.getString("apellido"));
			cliente.setDireccion(rs.getString("direccion"));
			cliente.setId(rs.getInt("id"));
			cliente.setTelefono(rs.getString("telefono"));
		} 
		catch (SQLException e) 
		{
			cliente = null;
		}
		
		return cliente;
	}
	//endregion
}
