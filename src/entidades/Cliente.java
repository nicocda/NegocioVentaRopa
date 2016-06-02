package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente 
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "telefono")
	private String telefono;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="cliente")
	private List<Venta> ventas;
	
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
}