package entidades;

public class TipoTarjeta implements Entidad
{
	private int id;
	private String descripcion;
	
	//region Getters y Setters
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
	//endregion
	
	//region Útil
	public String toJson()
	{
		String tipoTarjeta = "\"id\": " + this.id + ","
				+ "\"descripcion\": " + "\"" + this.descripcion + "\"";
		
		return "{" + tipoTarjeta + "}";
	}
	//endregion
}
