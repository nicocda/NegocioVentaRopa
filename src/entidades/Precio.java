package entidades;

import java.sql.Timestamp;

public class Precio implements Entidad
{
	private Timestamp fecha;
	private float precio;
	private Producto producto;
	
	//region Getters y Setters
	public float getPrecio() 
	{
		return precio;
	}

	public void setPrecio(float precio)
	{
		this.precio = precio;
	}

	public Producto getProducto() 
	{
		return producto;
	}

	public void setProducto(Producto producto)
	{
		this.producto = producto;
	}

	public Timestamp getFecha() 
	{
		return fecha;
	}

	public void setFecha(Timestamp fecha)
	{
		this.fecha = fecha;
	}
	//endregion
	
	//region Útil
	public String toJson()
	{
		String precio = "\"precio\": " + this.precio + ","
				+ "\"fecha\": " + "\"" + this.fecha + "\"";
		
		return "{" + precio + "}";
	}
	//endregion
}
