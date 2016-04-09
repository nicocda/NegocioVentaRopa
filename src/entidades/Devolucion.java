package entidades;

import java.util.ArrayList;
import java.util.Date;

public class Devolucion
{
	//Fields
	private int id;
	private Date fecha;
	private Venta venta;
	private ArrayList<Producto> productos;
	
	//Constructores
	public Devolucion()
	{
	}
	
	public Devolucion(int id, Date fecha, Venta venta, ArrayList<Producto> productos)
	{
		this.setFecha(fecha);
		this.setId(id);
		this.setVenta(venta);
		this.setProductos(productos);
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
		return fecha;
	}
	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}
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
}
