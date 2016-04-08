package entidades;

public class Producto 
{
	//Fields
	private String descripcion;
	private int estado,id,tipo,subTipo;
	private Precio precio;
	
	//Constructores
	public Producto()
	{
	}
	
	public Producto(String descripcion, int estado, int id, int tipo, int subtipo, Precio precio)
	{
		this.setDescripcion(descripcion);
		this.setEstado(estado);
		this.setId(id);
		this.setPrecio(precio);
		this.setSubTipo(subtipo);
		this.setTipo(tipo);
	}
	
	//Getters - Setters
	public String getDescripcion() 
	{
		return descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getEstado() 
	{
		return estado;
	}
	public void setEstado(int estado) 
	{
		this.estado = estado;
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public int getTipo()
	{
		return tipo;
	}
	public void setTipo(int tipo)
	{
		this.tipo = tipo;
	}
	public int getSubTipo() 
	{
		return subTipo;
	}
	public void setSubTipo(int subTipo) 
	{
		this.subTipo = subTipo;
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
	public static enum tipo
	{
		ROPA,
		MARROQUINERIA,
		BIJOUTERIE,
		CALZADO
	}
	public static enum subTipo
	{
		GENERAL,
		HOMBRE,
		MUJER,
		NIÑO,
		BLANCO
	}
}
