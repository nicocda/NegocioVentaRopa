package entidades;

import java.util.ArrayList;
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

@Entity 
@Table(name = "producto")
public class Producto 
{
	@Id
	@Column(name = "id")
	@Expose
	private String id;
	
	@Expose
	@Column(name = "descripcion")
	private String descripcion;
	
	@Expose
	@Column(name = "estado")
	private int estado;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="idVenta")
	private Venta venta;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="idDevolucion")
	private Devolucion devolucion;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="producto")
	private List<Precio> precios = new ArrayList<Precio>();

	@Expose
	@Transient
	private Precio precio;
	
	@Expose
	@Column(name = "idSucursal")
	private int idSucursal;
	
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public List<Precio> getPrecios()
	{
		return precios;
	}
	public ArrayList<Precio> getPreciosArrayList()
	{
		return new ArrayList<Precio>(precios);
	}
	public void setPrecios(List<Precio> precios)
	{
		this.precios = precios;
	}
	public void setPrecios(ArrayList<Precio> precios)
	{
		this.precios = precios;
	}
	public String getDescripcion() 
	{
		return descripcion;
	}
	public String getId() 
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getEstado() 
	{
		return estado;
	}

	public void setEstado(int estado) 
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
		return precio;
	}
	public void setPrecio(Precio precio) 
	{
		this.precio = precio;
	}
	public void addPrecio(Precio precio)
	{
		this.precios.add(precio);
		precio.setProducto(this);
	}
	
	//Enums
	public static enum estado
	{
		VENDIDO,
		STOCK,
		SEÑADO
	}
}
