package entidades;

import java.util.ArrayList;
import java.util.Date;

public class Venta 
{
	//Fields
	private int id, formaPago;
	private float importe;
	private Date fechaVenta;
	private Cliente cliente;
	private ArrayList<Producto> productos;
	
	//Constructores
	public Venta()
	{
		this.productos= new ArrayList<Producto>();
	}
	
	public Venta(int id, int formaPago, float importe, Date fechaVenta, Cliente cliente, ArrayList<Producto> productos)
	{
		this.setCliente(cliente);
		this.setFechaVenta(fechaVenta);
		this.setFormaPago(formaPago);
		this.setId(id);
		this.setImporte(importe);
		this.setProductos(productos);
	}
	
	//Getters - Setters
	public int getId()
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public int getFormaPago()
	{
		return formaPago;
	}
	public void setFormaPago(int formaPago) 
	{
		this.formaPago = formaPago;
	}
	public float getImporte() 
	{
		return importe;
	}
	public void setImporte(float importe) 
	{
		this.importe = importe;
	}
	public Date getFechaVenta() 
	{
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta)
	{
		this.fechaVenta = fechaVenta;
	}
	public Cliente getCliente() 
	{
		return cliente;
	}
	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}
	public ArrayList<Producto> getProductos()
	{
		return productos;
	}
	public void setProductos(ArrayList<Producto> productos)
	{
		this.productos = productos;
	}
	public void addProducto(Producto pr)
	{
		if(!productos.contains(pr))
		{
			productos.add(pr);
		}
	}
	//Enum
	public static enum formaPago
	{
		NULL,
		EFECTIVO,
		CTACORRIENTE,
		TARJETA
	}
}
