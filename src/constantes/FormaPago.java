package constantes;

public class FormaPago 
{
	private String formaPago;
	
	public static final FormaPago EFECTIVO = new FormaPago("efectivo");
	public static final FormaPago CUENTACORRIENTE = new FormaPago("ctaCorriente");
	public static final FormaPago TARJETA = new FormaPago("tarjeta");
	
	public FormaPago(String formaPago)
	{
		this.formaPago = formaPago;
	}
	
	public String toString()
	{
		return formaPago;
	}
}
