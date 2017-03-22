package negocio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datosTODOparsear.CatalogoUsuarios;
import entidades.Usuario;
import excepciones.RespuestaServidor;

public class ControladorUsuarios 
{
	public Usuario obtenerUsuario(String username) throws RespuestaServidor
	{
		CatalogoUsuarios cc = new CatalogoUsuarios();
		RespuestaServidor sr = validarUsuario(username);
		Usuario usuario = null;
		
		if (!sr.getStatus())
			throw sr;
		
		try
		{
			usuario = cc.obtenerUsuario(username);
		}
		catch (SQLException e) 
		{
			sr.addError(e);
			throw sr;
		}
		
		return usuario;
	}
	
	public ArrayList<Usuario> obtenerUsuarios() throws RespuestaServidor
	{
		CatalogoUsuarios cc = new CatalogoUsuarios();
		RespuestaServidor sr = new RespuestaServidor();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try
		{
			usuarios = cc.obtenerUsuarios();
		}
		catch (SQLException e)
		{
			sr.addError(e);
			throw sr;
		}
		
		return usuarios;
	}
	
	public ArrayList<Usuario> obtenerUsuarios(int nroPagina, int porPagina) throws RespuestaServidor
	{
		CatalogoUsuarios cc = new CatalogoUsuarios();
		RespuestaServidor sr = new RespuestaServidor();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try
		{
			usuarios = cc.obtenerUsuarios(nroPagina, porPagina);
		}
		catch (SQLException e)
		{
			sr.addError(e);
			throw sr;
		}
		
		return usuarios;
	}
	
	
	public void guardarUsuario(Usuario usuario) throws RespuestaServidor
	{
		CatalogoUsuarios cc = new CatalogoUsuarios();
		RespuestaServidor sr = validarUsuario(usuario);
		
		if (!sr.getStatus())
			throw sr;
		
		try
		{
			cc.guardarUsuario(usuario);
		}
		catch (SQLException e) 
		{
			sr.addError(e);
			throw sr;
		}
	}
	
	public Usuario validarLogueo(String username, String password) throws RespuestaServidor
	{
		RespuestaServidor sr = new RespuestaServidor();
		Usuario usuario = this.obtenerUsuario(username);
		
		if (usuario == null)
		{
			sr.addError("El usuario ingresado no existe.");
			throw sr;
		}
		
		if(password.equals(usuario.getPassword()))
		{
			usuario.setPassword(null);
			return usuario;
		}
		else
		{
			sr.addError("La contraseña ingresada no coincide.");
			throw sr;
		}
	}
	
	//region Validaciones
	private static RespuestaServidor validarUsuario(String username)
	{
		RespuestaServidor rs = new RespuestaServidor();
		
		if (username == null)
			rs.addError("El usuario que se intenta obtener no es válido.");
		
		return rs;
	}
	
	private static RespuestaServidor validarUsuario(Usuario usuario)
	{
		RespuestaServidor rs = new RespuestaServidor();
		
		if (usuario == null)
		{
			rs.addError("Ocurrió un error inesperado. Intentando guardar usuario nulo.");
			return rs;
		}
		
		if (usuario.getUsuario() == null || usuario.getUsuario().isEmpty())
			rs.addError("Username del usuario no válido");
			
		if (usuario.getPassword() == null || usuario.getPassword().isEmpty())
			rs.addError("El campo PASSWORD es requerido");
		
		if (usuario.getNombreYApellido() == null || usuario.getNombreYApellido().isEmpty())
			rs.addError("El campo NOMBRE COMPLETO es requerido");
		
		return rs;
	}
	//endregion
}
