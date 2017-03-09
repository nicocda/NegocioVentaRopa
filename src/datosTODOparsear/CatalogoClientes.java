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
	public static Cliente obtenerCliente(int idCliente) throws RespuestaServidor
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;

		RespuestaServidor sr = validarCliente(idCliente);
		
		if (!sr.getStatus())
			throw sr;
			
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
			else
			{
				sr.addError("El cliente que se intenta obtener no existe");
				throw sr;
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
		return cliente;
	}
	
	public static ArrayList<Cliente> obtenerClientesPorPagina(int paginaActual, int porPagina) throws RespuestaServidor
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;

		RespuestaServidor sr = new RespuestaServidor();
		
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
			sr.addError(e);
			throw sr;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return clientes;
	}

	public static ArrayList<Cliente> obtenerClientes() throws RespuestaServidor
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;

		RespuestaServidor sr = new RespuestaServidor();
		
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
			sr.addError(e);
			throw sr;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return clientes;
	}

	public static void guardarCliente(Cliente cliente) throws RespuestaServidor
	{
		PreparedStatement sentencia = null;

		RespuestaServidor sr = validarCliente(cliente);
		
		if (!sr.getStatus())
			throw sr;
		
		String sql;
		
		if (cliente.getId() == 0)
			sql = "INSERT INTO cliente(nombre, apellido, telefono, direccion, activo) VALUES(?, ?, ?, ?, ?)";
		else
		{
			if (obtenerCliente(cliente.getId()) != null)
				sql = "UPDATE cliente SET nombre = ?, apellido = ?, telefono = ?, direccion = ?, activo = ? WHERE id = ?";
			else
			{
				sr.addError("Se está intentando actualizar un cliente con id inexistente");
				throw sr;
			}
		}
			
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
			sr.addError(e);
			throw sr;
		}
		finally
		{
			cerrarStatement(sentencia);
		}
	}
	
	public static int contarClientes()
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
	private static Cliente setCliente(ResultSet rs)
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
	
	//region Validaciones
	private static RespuestaServidor validarCliente(int idCliente)
	{
		RespuestaServidor rs = new RespuestaServidor();
		
		if (idCliente <= 0)
			rs.addError("El cliente que se intenta obtener no es válido.");
		
		return rs;
	}
	
	private static RespuestaServidor validarCliente(Cliente cliente)
	{
		RespuestaServidor rs = new RespuestaServidor();
		
		if (cliente == null)
		{
			rs.addError("Ocurrió un error inesperado. Intentando guardar cliente nulo.");
			return rs;
		}
		
		if (cliente.getId() < 0)
			rs.addError("ID de cliente no válido");
			
		if (cliente.getApellido() == null || cliente.getApellido().isEmpty())
			rs.addError("El campo APELLIDO es requerido");
		
		if (cliente.getNombre() == null || cliente.getNombre().isEmpty())
			rs.addError("El campo NOMBRE es requerido");
		
		return rs;
	}
	//endregion
}
