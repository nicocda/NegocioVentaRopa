package constantes;

public class TipoVenta 
{
	private int id;
	private String tipoVenta;
	
	public static final TipoVenta PRESTAMO = new TipoVenta(1, "Préstamo");
	public static final TipoVenta RESERVA = new TipoVenta(2, "Reserva");
	
	public TipoVenta()
	{
		
	}
	
	public TipoVenta(int id, String tipoVenta)
	{
		this.id = id;
		this.tipoVenta = tipoVenta;
	}
	
	public int getTipo()
	{
		return id;
	}
	
	public String toString()
	{
		return tipoVenta;
	}
	
	public TipoVenta getTipo (int id)
	{
		switch (id) 
		{
		case 1:
			return TipoVenta.PRESTAMO;
		case 2:
			return TipoVenta.RESERVA;
		default:
			return new TipoVenta(id, "");
		}
	}
}
