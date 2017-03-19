package entidades;

import java.sql.Timestamp;
import java.util.ArrayList;

import constantes.FormaPago;
import constantes.TipoVenta;

public class Venta implements Entidad
{
	private int id, formaPago, tipoVenta;		
	private Timestamp fechaVenta, fechaCaducidad;
	private boolean isReserva, isPrestamo, pagada;
	private float seña, deudaPendiente, importe;
	private Sucursal sucursal;
	private Tarjeta tarjeta;
	private Cliente cliente;
	private ArrayList<Devolucion> devoluciones;
	private ArrayList<Cuota> cuotas = new ArrayList<Cuota>();
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	
	//region Getters y Setters
	public float getDeudaPendiente() 
	{
		return deudaPendiente;
	}
	
	public void setDeudaPendiente(float deudaPendiente) 
	{
		this.deudaPendiente = deudaPendiente;
	}
	
	public boolean isPagada() 
	{
		return pagada;
	}
	
	public void setPagada(boolean pagada) 
	{
		this.pagada = pagada;
	}

	public Sucursal getSucursal() 
	{
		return sucursal;
	}
	
	public void setSucursal(Sucursal sucursal) 
	{
		this.sucursal = sucursal;
	}
	
	public Tarjeta getTarjeta() 
	{
		return tarjeta;
	}
	
	public void setTarjeta(Tarjeta tarjeta) 
	{
		this.tarjeta = tarjeta;
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
		this.productos.add(pr);
		pr.setVenta(this);
	}
	
	public ArrayList<Devolucion> getDevoluciones()
	{
		return devoluciones;
	}
	
	public void setDevoluciones(ArrayList<Devolucion> devoluciones)
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
	
	public Timestamp getFechaVenta() 
	{
		return fechaVenta;
	}
	
	public void setFechaVenta(Timestamp fechaVenta) 
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
	
	public float getSeña() 
	{
		return seña;
	}
	
	public void setSeña(float seña)
	{
		this.seña = seña;
	}
	
	public Timestamp getFechaCaducidad() 
	{
		return fechaCaducidad;
	}
	
	public void setFechaCaducidad(Timestamp fechaCaducidad) 
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
	
	public void setFormaPago (FormaPago formaPago)
	{
		this.formaPago = formaPago.getFormaPago();
	}
	
	public float getImporte() 
	{
		return importe;
	}
	
	public void setImporte(float importe) 
	{
		this.importe = importe;
	}
	
	public ArrayList<Cuota> getCuotas() 
	{
		return cuotas;
	}
	
	public void setCuotas(ArrayList<Cuota> cuotas)
	{
		this.cuotas = cuotas;
	}
	
	public int getTipoVenta() 
	{
		return tipoVenta;
	}
	
	public void setTipoVenta(int tipoVenta)
	{
		this.tipoVenta = tipoVenta;
	}
	
	public void setTipoVenta(TipoVenta tipoVenta)
	{
		this.tipoVenta = tipoVenta.getTipo();
	}
	//endregion
	
	//region Útil
	public String toJson()
	{
		String venta = "\"id\": " + this.id + ","
				+ "\"formaPago\": " + this.formaPago + ","
				+ "\"deudaPendiente\": " + this.deudaPendiente + ","
				+ "\"importe\": " + this.importe + ","
				+ "\"seña\": " + this.seña + ","
				+ "\"cliente\": " + this.cliente.toJson() + ","
				+ "\"fechaCaducidad\": " + "\"" + this.fechaCaducidad + "\","
				+ "\"fechaVenta\": " + "\"" + this.fechaVenta + "\","
				+ "\"isPrestamo\": " + this.isPrestamo + ","
				+ "\"isReserva\": " + this.isReserva + ","
				+ "\"pagada\": " + this.pagada + ","
				+ "\"sucursal\": " + this.sucursal.toJson() + ","
				+ "\"tarjeta\": " + this.tarjeta.toJson();
		
		if (this.cuotas != null && !this.cuotas.isEmpty())
		{
			String cuotas = ", \"cuotas\": [";
			
			for (Cuota cuota : this.cuotas)
			{
				cuotas = cuotas + cuota.toJson();
			}
			
			venta = venta + cuotas + "]";
		}
		
		if (this.devoluciones != null && !this.devoluciones.isEmpty())
		{
			String devoluciones = ", \"devoluciones\": [";
			
			for (Devolucion devolucion : this.devoluciones)
			{
				devoluciones = devoluciones + devolucion.toJson();
			}
			
			venta = venta + devoluciones + "]";
		}
		
		if (this.productos != null && !this.productos.isEmpty())
		{
			String productos = ", \"productos\": [";
			
			for (Producto producto : this.productos)
			{
				productos = productos + producto.toJson();
			}
			
			venta = venta + productos + "]";
		}
		
		return "{" + venta + "}";
	}
	//endregion
}
