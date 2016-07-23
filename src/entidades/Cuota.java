package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cuota")
@IdClass(CuotaId.class)
public class Cuota 
{
	//Fields
	@Id
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "importe")
	private float importe;
	
	@Id
	@ManyToOne(optional=true)
	@JoinColumn(name = "idVenta")
	private Venta venta_cuota;
	
	//Constructores
	public Cuota()
	{
	}
	
	public Cuota(Date fecha, float importe, Venta vta)
	{
		this.setFecha(fecha);
		this.setImporte(importe);
		this.setVenta_cuota(vta);
	}
	
	//Getters-Setters
	public Date getFecha() 
	{
		return fecha;
	}
	
	public void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}
	
	public float getImporte() 
	{
		return importe;
	}
	
	public void setImporte(float importe) 
	{
		this.importe = importe;
	}

	public Venta getVenta_cuota() {
		return venta_cuota;
	}

	public void setVenta_cuota(Venta venta_cuota) {
		this.venta_cuota = venta_cuota;
	}
	
}
