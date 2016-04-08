package entidades;

import java.util.Date;

public class Reserva extends Venta
{
	//Fields
	private float importe;
	private Date fechaCaducidad;
	
	//Constructores
	public Reserva()
	{
	}
	
	public Reserva(float importe, Date fechaCaducidad)
	{
		super();
		this.setImporte(importe);
		this.setFechaCaducidad(fechaCaducidad);
	}
	
	//Getters - Setters
	public Date getFechaCaducidad() 
	{
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) 
	{
		this.fechaCaducidad = fechaCaducidad;
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
