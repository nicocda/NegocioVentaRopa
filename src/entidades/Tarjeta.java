package entidades;

import java.util.List;

import javax.persistence.*;
import com.google.gson.annotations.Expose;

@Entity
@Table(name="tarjeta")
public class Tarjeta {
	
	@Id
	@Column(name = "id")
	@Expose
	private int nroTarjeta;
	
	@Column(name = "cuotas")
	@Expose
	private int cuotas;
	
	@Column(name = "nroCupon")
	@Expose
	private int nroCupon;
	
	@Expose
	@ManyToOne(optional=true)
	@JoinColumn(name="tipoTarjeta")
	private TipoTarjeta tipoTarjeta;
	
	@Expose
	@ManyToOne(optional=true)
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	//@OneToMany(cascade = CascadeType.REMOVE, mappedBy="venta")
	//private List<Venta> venta;
	
	public int getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(int nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	public int getNroCupon() {
		return nroCupon;
	}
	public void setNroCupon(int nroCupon) {
		this.nroCupon = nroCupon;
	}
	public TipoTarjeta getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cl) {
		cliente = cl;
	}
}
