package constantes;

public class FormaPago 
{
	private int id;
	private String formaPago;
	
	public static final FormaPago EFECTIVO = new FormaPago(1, "Efectivo");
	public static final FormaPago CUENTACORRIENTE = new FormaPago(2, "Cuenta Corriente");
	public static final FormaPago TARJETA = new FormaPago(3, "Tarjeta");
	
	public FormaPago ()
	{
		
	}
	
	public FormaPago(int id, String formaPago)
	{
		this.id = id;
		this.formaPago = formaPago;
	}
	
	public int getFormaPago()
	{
		return id;
	}
	
	public String toString()
	{
		return formaPago;
	}
	
	public FormaPago getFormaPago(int id)
	{
		switch (id) 
		{
		case 1:
			return FormaPago.EFECTIVO;
		case 2:
			return FormaPago.CUENTACORRIENTE;
		case 3:
			return FormaPago.TARJETA;
		default:
			return new FormaPago(id, "");
		}
	}
}
