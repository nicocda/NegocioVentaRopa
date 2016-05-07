package entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "precio")
@IdClass(PrecioId.class)
public class Precio
{
	//Fields
	@Id
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "precio")
	private float precio;
	
	@Id
	@ManyToOne(optional=true)
	@JoinColumn(name="idProducto")
	private Producto producto;
	
	public float getPrecio() 
	{
		return precio;
	}

	public void setPrecio(float precio)
	{
		this.precio = precio;
	}

	public Producto getProducto() 
	{
		return producto;
	}

	public void setProducto(Producto producto)
	{
		this.producto = producto;
	}

	public Date getFecha() 
	{
		return fecha;
	}


	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}
}
