package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="configuracion")
public class Configuracion 
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "permitirLog")
	private boolean permitirLog;
	
	@Column(name = "tipoLog")
	private String tipoLog;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPermitirLog() {
		return permitirLog;
	}

	public void setPermitirLog(boolean permitirLog) {
		this.permitirLog = permitirLog;
	}

	public String getTipoLog() {
		return tipoLog;
	}

	public void setTipoLog(String tipoLog) {
		this.tipoLog = tipoLog;
	}
}
