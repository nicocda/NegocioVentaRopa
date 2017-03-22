package negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import datosTODOparsear.CatalogoClientes;
import entidades.Cliente;
import excepciones.RespuestaServidor;

public class ControladorClientes 
{	
	public Cliente obtenerCliente(int id) throws RespuestaServidor
	{
		CatalogoClientes cc = new CatalogoClientes();
		RespuestaServidor sr = validarCliente(id);
		Cliente cliente = null;
		
		if (!sr.getStatus())
			throw sr;
		
		try
		{
			cliente = cc.obtenerCliente(id);	
		}
		catch(SQLException e)
		{
			sr.addError(e);
			throw sr;
		}
		
		return cliente;
	}
	
	public ArrayList<Cliente> obtenerClientes() throws RespuestaServidor
	{
		CatalogoClientes cc = new CatalogoClientes();
		RespuestaServidor sr = new RespuestaServidor();
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try
		{
			clientes = cc.obtenerClientes();
		}
		catch (SQLException e)
		{
			sr.addError(e);
			throw sr;
		}
		
		return clientes;
	}
	
	public ArrayList<Cliente> obtenerClientes(int paginaActual, int porPagina) throws RespuestaServidor
	{
		CatalogoClientes cc = new CatalogoClientes();
		RespuestaServidor sr = new RespuestaServidor();
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try
		{
			clientes = cc.obtenerClientes(paginaActual, porPagina);
		}
		catch (SQLException e)
		{
			sr.addError(e);
			throw sr;
		}
		
		return clientes;
	}
	
	public void guardarCliente(Cliente cliente) throws RespuestaServidor
	{
		CatalogoClientes cc = new CatalogoClientes();
		RespuestaServidor sr = validarCliente(cliente);
		
		if (!sr.getStatus())
			throw sr;
		
		try
		{
			cc.guardarCliente(cliente);
		}
		catch (SQLException e)
		{
			sr.addError(e);
			throw sr;
		}
	}
	
	public int contarClientes()
	{
		CatalogoClientes cc = new CatalogoClientes();
		
		return cc.contarClientes();
	}
	
	//region Validaciones
	private RespuestaServidor validarCliente(int idCliente)
	{
		RespuestaServidor rs = new RespuestaServidor();
		
		if (idCliente <= 0)
			rs.addError("El cliente que se intenta obtener no es válido.");
		
		return rs;
	}
	
	private RespuestaServidor validarCliente(Cliente cliente)
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
