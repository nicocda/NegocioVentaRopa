package constantes;

public class EstadoProducto 
{
	private int id;
	private String estadoProducto;
	
	public static final EstadoProducto VENDIDO = new EstadoProducto(1, "Vendido");
	public static final EstadoProducto STOCK = new EstadoProducto(2, "Stock");
	public static final EstadoProducto SE�ADO = new EstadoProducto(3, "Se�ado");
	public static final EstadoProducto CONDICIONAL = new EstadoProducto(4, "Condicional");
	
	public EstadoProducto()
	{
		
	}
	
	public EstadoProducto(int id, String estadoProducto)
	{
		this.id = id;
		this.estadoProducto = estadoProducto;
	}
	
	public int getEstado()
	{
		return id;
	}
	
	public String toString()
	{
		return estadoProducto;
	}
	
	public EstadoProducto getEstado(int id)
	{
		switch (id)
		{
		case 1:
			return EstadoProducto.VENDIDO;
		case 2:
			return EstadoProducto.STOCK;
		case 3:
			return EstadoProducto.SE�ADO;
		case 4:
			return EstadoProducto.CONDICIONAL;
		default:
			return new EstadoProducto(id, "");
		}
	}
}
