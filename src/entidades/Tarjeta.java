package entidades;

public class Tarjeta implements Entidad
{	
	private int nroTarjeta, cuotas, nroCupon;
	private TipoTarjeta tipoTarjeta;

	//region Getters y Setters
	public int getNroTarjeta() 
	{
		return nroTarjeta;
	}
	
	public void setNroTarjeta(int nroTarjeta)
	{
		this.nroTarjeta = nroTarjeta;
	}
	
	public int getCuotas() 
	{
		return cuotas;
	}
	
	public void setCuotas(int cuotas) 
	{
		this.cuotas = cuotas;
	}
	
	public int getNroCupon() 
	{
		return nroCupon;
	}
	
	public void setNroCupon(int nroCupon) 
	{
		this.nroCupon = nroCupon;
	}
	
	public TipoTarjeta getTipoTarjeta() 
	{
		return tipoTarjeta;
	}
	
	public void setTipoTarjeta(TipoTarjeta tipoTarjeta)
	{
		this.tipoTarjeta = tipoTarjeta;
	}
	//endregion
	
	//region Util
	public String toJson()
	{
		String tarjeta = "\"nroTarjeta\": " + this.nroTarjeta + ","
				+ "\"nroCupon\": " + this.nroCupon + ","
				+ "\"cuotas\": " + this.cuotas + ","
				+ "\"tipoTarjeta\": " + this.tipoTarjeta.toJson();
		
		return "{" + tarjeta + "}";
	}
	//endregion
}
