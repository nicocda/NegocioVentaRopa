package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sucursal")
public class Sucursal {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	@Column(name = "localidad")
private String localidad;
	@Column(name = "direccion")
private String direccion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String toString() {
		return "Localidad: "+localidad+" Direccion: "+direccion;
	}
	
}
