package entidades;

public class Sucursal implements Entidad 
{
	private int id;
	private String localidad, direccion;
	
	//region Getters y setters
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getLocalidad() 
	{
		return localidad;
	}
	
	public void setLocalidad(String localidad) 
	{
		this.localidad = localidad;
	}
	
	public String getDireccion()
	{
		return direccion;
	}
	
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	//endregion
	
	//region Útil
	public String toJson()
	{
		String sucursal = "\"id\": " + this.id + ","
				+ "\"direccion\": " + "\"" + this.direccion + "\","
				+ "\"localidad\": " + "\"" + this.localidad + "\"";
		
		return "{" + sucursal + "}";
	}
	//endregion
}
