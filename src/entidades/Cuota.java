package entidades;

import java.sql.Timestamp;

public class Cuota 
{
	private Timestamp fecha;	
	private float importe;
	private Venta venta;
	
	public Cuota()
	{
		
	}
	
	public Cuota(Timestamp fecha, float importe, Venta venta)
	{
		this.setFecha(fecha);
		this.setImporte(importe);
		this.setVenta(venta);
	}
	
	//region Getters y Setters
	public Timestamp getFecha() 
	{
		return fecha;
	}
	
	public void setFecha(Timestamp fecha) 
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

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) 
	{
		this.venta = venta;
	}
	//endregion
	
	//region Útil
	public String toJson()
	{
		String cuota = "\"venta\": " + this.venta.toJson() + ","
				+ "\"importe\": " + this.importe + ","
				+ "\"fecha\": " + "\"" + this.fecha + "\"";
	
		return "{" + cuota + "}";
	}
	//endregion
}
