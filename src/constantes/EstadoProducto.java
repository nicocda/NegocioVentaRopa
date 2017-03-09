package constantes;

public class EstadoProducto 
{
	private int estadoProducto;
	
	public static final EstadoProducto VENDIDO = new EstadoProducto(1);
	public static final EstadoProducto STOCK = new EstadoProducto(2);
	public static final EstadoProducto SEÑADO = new EstadoProducto(3);
	public static final EstadoProducto CONDICIONAL = new EstadoProducto(4);
	
	public EstadoProducto(int estadoProducto)
	{
		this.estadoProducto = estadoProducto;
	}
	
	public int getEstado()
	{
		return estadoProducto;
	}
}
