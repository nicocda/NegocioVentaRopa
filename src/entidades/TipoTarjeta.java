package entidades;

import java.util.List;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="tipotarjeta")
public class TipoTarjeta
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private int id;
	
	@Column(name = "descripcion")
	@Expose
	private String descripcion;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="tipoTarjeta")
	private List<Tarjeta> tarjetas;
	
	public int getId() {
		return id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
