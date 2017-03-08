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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="cliente")
public class Cliente 
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private int id;
	
	@Column(name = "nombre")
	@Expose
	private String nombre;
	
	@Column(name = "apellido")
	@Expose
	private String apellido;
	
	@Column(name = "direccion")
	@Expose
	private String direccion;
	
	@Column(name = "telefono")
	@Expose
	private String telefono;
	
	@Expose
	private float deudaTotal;
	
	private boolean activo;
	
	public float getDeudaTotal() {
		return deudaTotal;
	}
	public void setDeudaTotal(float deudaTotal) {
		this.deudaTotal = this.deudaTotal + deudaTotal;
	}
	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}
	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}
	@OneToMany(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER, mappedBy="cliente")
	private List<Venta> ventas;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="cliente")
	private List<Tarjeta> tarjetas;
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getDireccion() 
	{
		return direccion;
	}
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	public String getTelefono() 
	{
		return telefono;
	}
	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	} 
	public List<Venta> getVentas() 
	{
		return ventas;
	}
	public void setVentas(List<Venta> ventas)
	{
		this.ventas = ventas;
	}
	public void setVentas(ArrayList<Venta> ventas)
	{
		this.ventas = ventas;
	}
	public ArrayList<Venta> getVentasArrayList()
	{
		return new ArrayList<Venta>(ventas);
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}