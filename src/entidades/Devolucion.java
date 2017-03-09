package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class Devolucion
{
	private int id;
	
	private Date fechaDevolucion;
	
	private Venta venta;
	
	private List<Producto> productos;
	
	public Venta getVenta()
	{
		return venta;
	}
	public void setVenta(Venta venta)
	{
		this.venta = venta;
	}
	public List<Producto> getProductos()
	{
		return productos;
	}
	public void setProductos(List<Producto> productos)
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
}
