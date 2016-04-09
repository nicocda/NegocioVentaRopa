package entidades;

public class Producto 
{
	//Fields
	private String descripcion,id;
	private int estado;
	private Precio precio;
	
	//Constructores
	public Producto()
	{
	}
	
	public Producto(String descripcion, int estado, String id, Precio precio)
	{
		this.setDescripcion(descripcion);
		this.setEstado(estado);
		this.setId(id);
		this.setPrecio(precio);
		
	}
	
	//Getters - Setters
	public String getDescripcion() 
	{
		return descripcion;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getEstado() 
	{
		return estado;
	}
	/**
	 * @param estado
	 */
	public void setEstado(int estado) 
	{
		this.estado = estado;
	}
	
	public Precio getPrecio() 
	{
		return precio;
	}
	public void setPrecio(Precio precio)
	{
		this.precio = precio;
	}
	
	//Enums
	public static enum estado
	{
		VENDIDO,
		STOCK,
		SEÑADO
	}
}
