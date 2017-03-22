package entidades;

import java.util.ArrayList;

public class Cliente implements Entidad
{
	private int id;
	private String nombre, apellido, direccion, telefono;
	private float deudaTotal;
	private boolean activo;
	private ArrayList<Tarjeta> tarjetas;

	//region Ctor
	public Cliente(){ }
	
	public Cliente(int id, String nombre, String apellido, String direccion, String telefono, boolean activo)
	{
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.activo = activo;
	}
	//endregion
	
	//region Setters y Getters
	public float getDeudaTotal() 
	{
		return deudaTotal;
	}
	
	public void setDeudaTotal(float deudaTotal) 
	{
		this.deudaTotal = deudaTotal;
	}
	
	public ArrayList<Tarjeta> getTarjetas() 
	{
		return tarjetas;
	}
	
	public void setTarjetas(ArrayList<Tarjeta> tarjetas) 
	{
		this.tarjetas = tarjetas;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getApellido() 
	{
		return apellido;
	}
	
	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
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

	public boolean isActivo() 
	{
		return activo;
	}
	
	public void setActivo(boolean activo) 
	{
		this.activo = activo;
	}
	
	//endregion

	//region Útiles
	
	public String toJson()
	{		
		String cliente = "\"id\": " + this.id + ","
				+ "\"nombre\": " + "\"" + this.nombre + "\","
				+ "\"apellido\": " + "\"" + this.apellido + "\","
				+ "\"direccion\": " + "\"" + this.direccion + "\","
				+ "\"telefono\": " + "\"" + this.telefono + "\","
				+ "\"deudaTotal\": " + this.deudaTotal + ","
				+ "\"activo\": " + this.activo;
		
		if (this.tarjetas != null && !this.tarjetas.isEmpty())
		{
			String tarjetas = ", \"tarjetas\": [";
		
			for (Tarjeta tarjeta : this.tarjetas)
			{
				tarjetas = tarjetas + tarjeta.toJson();
			}
			
			cliente = cliente + tarjetas + "]";
		}
		
		return "{" + cliente + "}";
	}
	
	//endregion
}