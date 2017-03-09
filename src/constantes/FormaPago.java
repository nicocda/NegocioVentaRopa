package constantes;

public class FormaPago 
{
	private int formaPago;
	
	public static final FormaPago EFECTIVO = new FormaPago(1);
	public static final FormaPago CUENTACORRIENTE = new FormaPago(2);
	public static final FormaPago TARJETA = new FormaPago(3);
	
	public FormaPago(int formaPago)
	{
		this.formaPago = formaPago;
	}
	
	public int getFormaPago()
	{
		return formaPago;
	}
}
