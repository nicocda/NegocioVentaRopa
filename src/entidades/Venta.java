package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="venta")
public class Venta 
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
		
	private Date fechaVenta;
	private boolean isReserva;
	private boolean isPrestamo;
	private float se�a;
	private Date fechaCaducidad;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy="venta")
	private List<Devolucion> devoluciones;
	
	@OneToMany(mappedBy="venta")
	private List<Producto> productos;
	
	public List<Producto> getProductos() 
	{
		return productos;
	}
	public ArrayList<Producto> getProductosArrayList() 
	{
		return new ArrayList<Producto>(productos);
	}
	public void setProductos(List<Producto> productos) 
	{
		this.productos = productos;
	}
	public void setProductos(ArrayList<Producto> productos) 
	{
		this.productos = productos;
	}
	public List<Devolucion> getDevoluciones()
	{
		return devoluciones;
	}
	public void setDevoluciones(List<Devolucion> devoluciones)
	{
		this.devoluciones = devoluciones;
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public Date getFechaVenta() 
	{
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) 
	{
		this.fechaVenta = fechaVenta;
	}
	public boolean isReserva()
	{
		return isReserva;
	}
	public void setReserva(boolean isReserva) 
	{
		this.isReserva = isReserva;
	}
	public boolean isPrestamo() 
	{
		return isPrestamo;
	}
	public void setPrestamo(boolean isPrestamo) 
	{
		this.isPrestamo = isPrestamo;
	}
	public float getSe�a() 
	{
		return se�a;
	}
	public void setSe�a(float se�a)
	{
		this.se�a = se�a;
	}
	public Date getFechaCaducidad() 
	{
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) 
	{
		this.fechaCaducidad = fechaCaducidad;
	}
	public Cliente getCliente()
	{
		return cliente;
	}
	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
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
	int formaPago;
	float importe;
	
	//Enum
	public static enum formaPago
	{
		NULL,
		EFECTIVO,
		CTACORRIENTE,
		TARJETA
	}
}
