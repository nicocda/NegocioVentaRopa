package entidades;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

public class Venta 
{
	private int id;
		
	private Timestamp fechaVenta;
	
	private boolean isReserva;
	
	private boolean isPrestamo;
	
	private float seña;
	
	private Timestamp fechaCaducidad;
	
	private int formaPago;
	
	private float importe;
	
	private boolean pagada;
	
	private float deudaPendiente;
	
	private Sucursal sucursal;
	
	private Tarjeta tarjeta;

	private Cliente cliente;
	
	private List<Devolucion> devoluciones;
	
	private List<Cuota> cuotas = new ArrayList<Cuota>();
	
	private List<Producto> productos = new ArrayList<Producto>();
	
	public float getDeudaPendiente() {
		return deudaPendiente;
	}
	public void setDeudaPendiente(float deudaPendiente) {
		this.deudaPendiente = deudaPendiente;
	}
	public boolean isPagada() {
		return pagada;
	}
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(Tarjeta tarjet) {
		tarjeta = tarjet;
	}
	
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
	public void addProducto(Producto pr)
	{
		this.productos.add(pr);
		pr.setVenta(this);
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
	public float getImporte() 
	{
		return importe;
	}
	public void setImporte(float importe) 
	{
		this.importe = importe;
	}
	public List<Cuota> getCuotas() {
		return cuotas;
	}
	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
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
