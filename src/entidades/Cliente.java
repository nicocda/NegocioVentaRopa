package entidades;

public class Cliente 
{
	//Fields
	private int id;
	private String nombreApellido, direccion, telefono;
	
	//Constructores
	public Cliente()
	{
	}
	
	public Cliente(int id, String nombreApellido, String direccion, String telefono)
	{
		this.setId(id);
		this.setDireccion(direccion);
		this.setNombreApellido(nombreApellido);
		this.setTelefono(telefono);
	}
	
	//Getter-Setter
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getNombreApellido() 
	{
		return nombreApellido;
	}
	public void setNombreApellido(String nombreApellido) 
	{
		this.nombreApellido = nombreApellido;
	}
	public String getDireccion() 
	{
		return direccion;
	}
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	public String getTelefono()
	{
		return telefono;
	}
	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	
	
	
	
}
