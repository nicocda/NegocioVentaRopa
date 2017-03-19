package entidades;

import java.sql.Timestamp;

public class EventLog implements Entidad 
{
	private int id;	
	private String tipoDeEvento, descripcion, nivelLog;
	private Timestamp fechaAlta;
	
	//region Geters y Setters
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getTipoDeEvento() 
	{
		return tipoDeEvento;
	}

	public void setTipoDeEvento(String tipoDeEvento) 
	{
		this.tipoDeEvento = tipoDeEvento;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}

	public Timestamp getFechaAlta() 
	{
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) 
	{
		this.fechaAlta = fechaAlta;
	}

	public String getNivelLog() 
	{
		return nivelLog;
	}

	public void setNivelLog(String nivelLog) 
	{
		this.nivelLog = nivelLog;
	}
	//endregion

	//region Útil
	public String toJson()
	{
		String eventLog = "\"id\": " + this.id + ","
				+ "\"descripcion\": " + "\"" + this.descripcion + "\","
				+ "\"nivelLog\": " + "\"" + this.nivelLog + "\","
				+ "\"tipoDeEvento\": " + "\"" + this.tipoDeEvento + "\","
				+ "\"fechaAlta\": " + "\"" + this.fechaAlta + "\"";
		
		return "{" + eventLog + "}";
	}
	//endregion
}
