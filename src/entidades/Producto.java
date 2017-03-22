package entidades;

import java.util.ArrayList;
import constantes.EstadoProducto;

public class Producto implements Entidad
{
	private int id;
	private EstadoProducto estado;
	private String codigoProducto, descripcion;
	private Venta venta;
	private Devolucion devolucion;
	private ArrayList<Precio> precios = new ArrayList<Precio>();
	private Precio precio;
	private Sucursal sucursal;
	
	//region Getters y Setters
	public Sucursal getSucursal() 
	{
		return sucursal;
	}
	
	public void setSucursal(Sucursal sucursal) 
	{
		this.sucursal = sucursal;
	}
	
	public ArrayList<Precio> getPrecios()
	{
		return precios;
	}

	public void setPrecios(ArrayList<Precio> precios)
	{
		this.precios = precios;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}
	public String getCodigoProducto() 
	{
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto)
	{
		this.codigoProducto = codigoProducto;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	public EstadoProducto getEstado() 
	{
		return estado;
	}
	
	public void setEstado(EstadoProducto estado)
	{
		this.estado = estado;
	}
	
	public Venta getVenta() 
	{
		return venta;
	}

	public void setVenta(Venta venta) 
	{
		this.venta = venta;
	}
	
	public Devolucion getDevolucion()
	{
		return devolucion;
	}

	public void setDevolucion(Devolucion devolucion)
	{
		this.devolucion = devolucion;
	}
	
	public Precio getPrecio() 
	{
		if (this.precios == null || this.precios.isEmpty())
			return null;
		
		Precio maxPrecio = new Precio();
		
		for (Precio precio : this.precios)
		{
			if (maxPrecio.getFecha() == null || precio.getFecha().after(maxPrecio.getFecha()))
				maxPrecio = precio;
		}
		
		return maxPrecio;
	}

	public void addPrecio(Precio precio)
	{
		this.precios.add(precio);
		precio.setProducto(this);
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId ()
	{
		return this.id;
	}
	
	public void setNuevoPrecio(Precio precio)
	{
		this.precio = precio;
	}
	
	public Precio getNuevoPrecio()
	{
		return precio;
	}
	//endregion
	
	//region Útil
	public String toJson()
	{
		String producto = "\"id\": " + this.id + ","
				+ "\"codigoProducto\": " + "\"" + this.codigoProducto + "\","
				+ "\"descripcion\": " + "\"" + this.descripcion + "\","
				+ "\"estado\": " + this.estado + ","
				+ "\"sucursal\": " + this.sucursal.toJson();
		
		if (this.precios != null && !this.precios.isEmpty())
		{
			String precios = ", \"precios\": [";
			
			for (Precio precio : this.precios)
			{
				precios = precios + precio.toJson();
			}
			
			producto = producto + precios + "]";
		}
		
		return "{" + producto + "}";
	}
	//endregion
}
