package entidades;

import java.io.Serializable;
import java.util.Date;

public class PrecioId implements Serializable
{
	private Date fecha;
	private Producto producto;
	
	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (!(other instanceof PrecioId))
			return false;
		PrecioId castOther = (PrecioId) other;
		if(this.producto.equals(castOther.getProducto()) && fecha.equals(castOther.getFecha()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		final int prime = 31;
	    int hash = 17;
	    hash = hash * prime + ((this.producto == null) ? 0 : this.producto.hashCode());
	    hash = hash * prime + this.fecha.hashCode();
	    return hash;
	}
	
	public Date getFecha() 
	{
		return fecha;
	}
	public void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}
	public Producto getProducto() 
	{
		return producto;
	}
	public void setProducto(Producto producto) 
	{
		this.producto = producto;
	}
}
