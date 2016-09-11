package entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario 
{
	@Id
	@Column(name = "usuario")
	String usuario;
	
	@Column(name = "nombreYApellido")
	String nombreYApellido;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "mail")
	String mail;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="idSucursal")
	private Sucursal sucursal;
	
	
	
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
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
	int tipoUsuario;
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
	
	public static enum tipoUsuario
	{
		INACTIVO,
		USUARIO,
		ADMIN,
		SUPERUSER
	}
}
