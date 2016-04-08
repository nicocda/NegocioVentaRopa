package entidades;

import java.util.Date;

public class Precio 
{
	//Fields
	private Date fecha;
	private float precio;
	
	//Constructores
	public Precio()
	{
	}
	
	public Precio(Date fecha, float precio)
	{
		this.setFecha(fecha);
		this.setPrecio(precio);
	}
	
	//Getters - Setters
	public Date getFecha() 
	{
		return fecha;
	}
	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}
	public float getPrecio() 
	{
		return precio;
	}
	public void setPrecio(float precio) 
	{
		this.precio = precio;
	}

}
