package entidades;

public class Configuracion implements Entidad
{
	private int id;
	private boolean permitirLog;
	private String tipoLog;

	//region Getters y Setters
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public boolean isPermitirLog() 
	{
		return permitirLog;
	}

	public void setPermitirLog(boolean permitirLog) 
	{
		this.permitirLog = permitirLog;
	}

	public String getTipoLog() 
	{
		return tipoLog;
	}

	public void setTipoLog(String tipoLog) 
	{
		this.tipoLog = tipoLog;
	}
	//endregion

	//region Útil
	public String toJson()
	{
		String configuracion = "\"id\": " + this.id + ","
				+ "\"tipoLog\": " + "\"" + this.tipoLog + "\","
				+ "\"permitirLog\": " + this.permitirLog;
	
		return "{" + configuracion + "}";
	}
	//endregion
}
