package constantes;

public class TipoUsuario 
{
	private int id;
	private String tipoUsuario;
	
	public static final TipoUsuario INACTIVO = new TipoUsuario(1, "Inactivo");
	public static final TipoUsuario NORMAL = new TipoUsuario(2, "Normal");
	public static final TipoUsuario ADMIN = new TipoUsuario(3, "Admin");
	public static final TipoUsuario SUPERUSER = new TipoUsuario(4, "Super Usuario");
	
	public TipoUsuario()
	{
		
	}
	
	public TipoUsuario(int id, String tipoUsuario)
	{
		this.id = id;
		this.tipoUsuario = tipoUsuario;
	}
	
	public int getTipo()
	{
		return id;
	}
	
	public String toString()
	{
		return tipoUsuario;
	}
	
	public TipoUsuario getTipo(int id)
	{
		switch (id) 
		{
		case 1:
			return TipoUsuario.INACTIVO;
		case 2:
			return TipoUsuario.NORMAL;
		case 3:
			return TipoUsuario.ADMIN;
		case 4:
			return TipoUsuario.SUPERUSER;
		default:
			return new TipoUsuario(id, "");
		}
	}
}
