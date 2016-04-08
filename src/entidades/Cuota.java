package entidades;

import java.util.Date;

public class Cuota 
{
	//Fields
	private Date fecha;
	private float importe;
	
	//Constructores
	public Cuota()
	{
	}
	
	public Cuota(Date fecha, float importe)
	{
		this.setFecha(fecha);
		this.setImporte(importe);
	}
	
	//Getters-Setters
	public Date getFecha() 
	{
		return fecha;
	}
	public void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}
	public float getImporte() 
	{
		return importe;
	}
	public void setImporte(float importe) 
	{
		this.importe = importe;
	}
}
