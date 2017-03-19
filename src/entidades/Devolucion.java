package entidades;

import java.util.ArrayList;
import java.util.Date;

public class Devolucion implements Entidad
{
	private int id;
	private Date fechaDevolucion;
	private Venta venta;
	private ArrayList<Producto> productos;
	
	//region Getters y Setters
	public Venta getVenta()
	{
		return venta;
	}
	
	public void setVenta(Venta venta)
	{
		this.venta = venta;
	}
	
	public ArrayList<Producto> getProductos()
	{
		return productos;
	}
	
	public void setProductos(ArrayList<Producto> productos)
	{
		this.productos = productos;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public Date getFecha()
	{
		return fechaDevolucion;
	}
	
	public void setFecha(Date fecha)
	{
		this.fechaDevolucion = fecha;
	}
	//endregion
	
	//region Útil
	public String toJson()
	{
		String devolucion = "";
		
		return devolucion;
	}
	//endregion	
}
