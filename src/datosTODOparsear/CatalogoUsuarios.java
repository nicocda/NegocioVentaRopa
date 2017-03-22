package datosTODOparsear;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import constantes.TipoUsuario;
import entidades.Usuario;
import excepciones.RespuestaServidor;

public class CatalogoUsuarios extends CatalogoBase
{
	//region Públicos
	public Usuario obtenerUsuario(String username) throws SQLException
	{
		ResultSet rs = null;
		PreparedStatement sentencia = null;
			
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
			throw e;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return usuario;
	}
	
	public ArrayList<Usuario> obtenerUsuarios(int paginaActual, int porPagina) throws SQLException
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
			throw e;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return usuarios;
	}

	public ArrayList<Usuario> obtenerUsuarios() throws SQLException
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
			throw e;
		} 
		finally
		{
			cerrarStatement(sentencia);
		}	
		
		return usuarios;
	}

	public void guardarUsuario(Usuario usuario) throws SQLException
	{
		PreparedStatement sentencia = null;
		
		String sql;
				
		if (usuario.getUsuario() == null)
			sql = "INSERT INTO usuario(usuario, password, nombreyApellido, mail, tipoUsuario) VALUES(?, ?, ?, ?, ?)";
		else
			sql = "UPDATE usuario SET usuario = ?, password = ?, nombreyApellido = ?, mail = ?, tipoUsuario	= ? WHERE usuario = ?";

		try
		{
			sentencia = prepareStatement(sql);
			
			sentencia.setString(1, usuario.getUsuario());
			sentencia.setString(2, usuario.getPassword());
			sentencia.setString(3, usuario.getNombreYApellido());
			sentencia.setString(4, usuario.getEmail());
			sentencia.setInt(5, usuario.getTipoUsuario().getTipo());
			
			if (usuario.getUsuario() != null)
				sentencia.setString(6, usuario.getUsuario());
			
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
	
	public int contarUsuarios()
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
	private Usuario setUsuario(ResultSet rs)
	{
		Usuario usuario = new Usuario();
		
		try 
		{
			usuario.setNombreYApellido(rs.getString("nombreyApellido"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setPassword(rs.getString("password"));
			usuario.setEmail(rs.getString("mail"));
			usuario.setTipoUsuario(new TipoUsuario().getTipo(rs.getInt("tipoUsuario")));
		} 
		catch (SQLException e) 
		{
			usuario = null;
		}
		
		return usuario;
	}
	//endregion
	
}
