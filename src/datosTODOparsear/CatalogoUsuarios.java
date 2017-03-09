package datosTODOparsear;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Usuario;
import excepciones.RespuestaServidor;

public class CatalogoUsuarios extends CatalogoBase
{
	//region Públicos
	public static Usuario obtenerUsuario(String username) throws RespuestaServidor
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;

		RespuestaServidor sr = validarUsuario(username);
		
		if (!sr.getStatus())
			throw sr;
			
		Usuario usuario = null;
		String sql = "SELECT * FROM usuario WHERE usuario = ?";
		
		try
		{
			sentencia = prepareStatement(sql);
			sentencia.setString(1, username);
			
			rs = sentencia.executeQuery();
			
			if(rs.next())
			{
				usuario = setUsuario(rs);			
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
		return usuario;
	}
	
	public static ArrayList<Usuario> obtenerUsuariosPorPagina(int paginaActual, int porPagina) throws RespuestaServidor
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;

		RespuestaServidor sr = new RespuestaServidor();
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();	
		
		String sql = "SELECT * FROM usuario LIMIT ?, ?";
		
		try
		{
			sentencia = prepareStatement(sql);
			sentencia.setInt(1, paginaActual);
			sentencia.setInt(2, porPagina);
			
			rs = sentencia.executeQuery();
			
			while(rs.next())
			{
				usuarios.add(setUsuario(rs));
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
		
		return usuarios;
	}

	public static ArrayList<Usuario> obtenerUsuarios() throws RespuestaServidor
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;

		RespuestaServidor sr = new RespuestaServidor();
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();	
		
		String sql = "SELECT * FROM usuario";
		
		try
		{
			sentencia = prepareStatement(sql);
			
			rs = sentencia.executeQuery();
			
			while(rs.next())
			{
				usuarios.add(setUsuario(rs));
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
		
		return usuarios;
	}

	public static void guardarUsuario(Usuario usuario) throws RespuestaServidor
	{
		PreparedStatement sentencia = null;

		RespuestaServidor sr = validarUsuario(usuario);
		
		if (!sr.getStatus())
			throw sr;
		
		String sql;
		
		Usuario usuarioDB = obtenerUsuario(usuario.getUsuario());
		
		if (usuarioDB != null)
			sql = "UPDATE usuario SET usuario = ?, password = ?, nombreyApellido = ?, mail = ?, tipoUsuario	= ? WHERE usuario = ?";
		else
			sql = "INSERT INTO usuario(usuario, password, nombreyApellido, mail, tipoUsuario) VALUES(?, ?, ?, ?, ?)";
		
		try
		{
			sentencia = prepareStatement(sql);
			
			sentencia.setString(1, usuario.getUsuario());
			sentencia.setString(2, usuario.getPassword());
			sentencia.setString(3, usuario.getNombreYApellido());
			sentencia.setString(4, usuario.getEmail());
			sentencia.setInt(5, usuario.getTipoUsuario());
			
			if (usuarioDB != null)
				sentencia.setString(6, usuario.getUsuario());
			
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
	
	public static int contarUsuarios()
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		
		int nroUsuarios = 0;
		
		String sql = "SELECT COUNT(*) FROM usuario";
		
		try
		{
			sentencia = prepareStatement(sql);
			
			rs = sentencia.executeQuery();
			
			if(rs.next())
			{
				nroUsuarios = rs.getInt(1);
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
		
		return nroUsuarios;
	}
	
	//endregion
	
	//region Privados
	private static Usuario setUsuario(ResultSet rs)
	{
		Usuario usuario = new Usuario();
		
		try 
		{
			usuario.setNombreYApellido(rs.getString("nombreyApellido"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setPassword(rs.getString("password"));
			usuario.setEmail(rs.getString("mail"));
			usuario.setTipoUsuario(rs.getInt("tipoUsuario"));
		} 
		catch (SQLException e) 
		{
			usuario = null;
		}
		
		return usuario;
	}
	//endregion
	
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
	
	public static Usuario validarLogueo(String username, String pass) {
		
		ResultSet rs = null;
		PreparedStatement sentencia = null;
		
		Usuario usuario = null;
		String sql = "SELECT * FROM usuario WHERE usuario = ? AND password = ?";
		try
		{
			sentencia = prepareStatement(sql);
			sentencia.setString(1, username);
			sentencia.setString(2, pass);
			
			rs = sentencia.executeQuery();
			
			if(rs.next())
			{
				usuario = setUsuario(rs);			
			}
		}
		catch(SQLException ex)
		{
			
		}
		finally
		{
			cerrarStatement(sentencia);
		}	
		return usuario;
	}
	//endregion

	
}
