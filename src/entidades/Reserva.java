package entidades;

import java.sql.Timestamp;
import java.util.Date;

public class Reserva extends Venta
{
	//Fields
	private float importe;
	private Timestamp fechaCaducidad;
	
	//Constructores
	public Reserva()
	{
	}
	
	public Reserva(float importe, Timestamp fechaCaducidad)
	{
		super();
		this.setImporte(importe);
		this.setFechaCaducidad(fechaCaducidad);
	}
	
	//Getters - Setters
	public Timestamp getFechaCaducidad() 
	{
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Timestamp fechaCaducidad) 
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
