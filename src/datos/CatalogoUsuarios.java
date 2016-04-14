package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.DataConnection;
import entidades.Usuario;

public class CatalogoUsuarios {
	
	public static Usuario buscarUsuario(String id, String pass) {
		Usuario usu = null;
		String sql="select * from usuario where usuario=? and password=?";
		PreparedStatement sentencia = null;
		Connection con = DataConnection.getInstancia().getConn();
		try
		{
			sentencia =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, id);
			sentencia.setString(2, pass);
			ResultSet rs =sentencia.executeQuery();
			if(rs.next())
			{
				usu = new Usuario();
				usu.setEmail(rs.getString("mail"));
				usu.setUsuario(rs.getString("usuario"));
				usu.setNombreYApellido(rs.getString("nombreyApellido"));
				usu.setPassword(rs.getString("password"));
				usu.setTipoUsuario(rs.getInt("tipoUsuario"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return usu;
		
		
	}
	
}
