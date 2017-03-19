package entidades;

import java.sql.Timestamp;

public class Reserva extends Venta implements Entidad
{
	private float importe;
	private Timestamp fechaCaducidad;
	
	public Reserva()
	{
		
	}
	
	public Reserva(float importe, Timestamp fechaCaducidad)
	{
		super();
		this.setImporte(importe);
		this.setFechaCaducidad(fechaCaducidad);
	}
	
	//region Getters y Setters
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
	//endregion
	
	//region Útil
	public String toJson()
	{
		String reserva = "\"venta\": " + super.toJson()
			+ "\"importe\": " + this.importe + ","
			+ "\"fechaCaducidad\": " + "\"" + this.fechaCaducidad + "\"";
		
		return "{" + reserva + "}";
	}
	//endregion
}
