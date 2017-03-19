package entidades;

import constantes.TipoUsuario;

public class Usuario implements Entidad
{
	private String usuario, nombreYApellido, password, mail;
	private Sucursal sucursal;
	int tipoUsuario;
	
	//region Getters y Setters
	public Sucursal getSucursal() 
	{
		return sucursal;
	}
	
	public void setSucursal(Sucursal sucursal)
	{
		this.sucursal = sucursal;
	}
	
	public String getUsuario() 
	{
		return usuario;
	}
	
	public void setUsuario(String usuario) 
	{
		this.usuario = usuario;
	}
	
	public String getNombreYApellido() 
	{
		return nombreYApellido;
	}
	
	public void setNombreYApellido(String nombreYApellido) 
	{
		this.nombreYApellido = nombreYApellido;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getEmail() 
	{
		return mail;
	}
	
	public void setEmail(String email) 
	{
		this.mail = email;
	}
	
	public int getTipoUsuario() 
	{
		return tipoUsuario;
	}
	
	public void setTipoUsuario(int tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario;
	}
	
	public void setTipoUsuario(TipoUsuario tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario.getTipo();
	}
	//endregion
	
	//region Útil
	public String toJson()
	{
		String usuario = "\"usuario\": " + "\"" + this.usuario + "\","
				+ "\"nombreYApellido\": " + "\"" + this.nombreYApellido + "\","
				+ "\"mail\": " + "\"" + this.mail + "\","
				+ "\"password\": " + "\"" + this.password + "\","
				+ "\"tipoUsuario\": " + this.tipoUsuario;
		
		if (this.sucursal != null)
		{
			usuario = usuario + ",\"sucursal\": " + "\"" + this.sucursal.toJson() + "\"";
		}
		
		return "{" + usuario + "}";
	}
	//endreion
}
