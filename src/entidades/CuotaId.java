package entidades;

import java.io.Serializable;
import java.util.Date;

public class CuotaId implements Serializable
{
	private Date fecha;
	private Venta venta_cuota;
	
	public boolean equals(Object other)
	{
		if (this == other)
			return true;
		if (!(other instanceof CuotaId))
			return false;
		CuotaId castOther = (CuotaId) other;
		if(this.venta_cuota.equals(castOther.getVenta_cuota())&& fecha.equals(castOther.getFecha()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int hashCode()
	{
		final int prime = 31;
	    int hash = 17;
	    hash = hash * prime + ((this.venta_cuota == null) ? 0 : this.venta_cuota.hashCode());
	    hash = hash * prime + this.fecha.hashCode();
	    return hash;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Venta getVenta_cuota() {
		return venta_cuota;
	}

	public void setVenta_cuota(Venta venta_cuota) {
		this.venta_cuota = venta_cuota;
	}
	
	
}
