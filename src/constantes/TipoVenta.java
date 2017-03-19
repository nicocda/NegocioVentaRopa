package constantes;

public class TipoVenta 
{
	private int tipoVenta;
	
	public static final TipoVenta PRESTAMO = new TipoVenta(1);
	public static final TipoVenta RESERVA = new TipoVenta(2);
	
	public TipoVenta(int tipoVenta)
	{
		this.tipoVenta = tipoVenta;
	}
	
	public int getTipo()
	{
		return tipoVenta;
	}
}
