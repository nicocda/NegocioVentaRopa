package constantes;

public class TipoUsuario 
{
	private int tipoUsuario;
	
	public static final TipoUsuario INACTIVO = new TipoUsuario(1);
	public static final TipoUsuario NORMAL = new TipoUsuario(2);
	public static final TipoUsuario ADMIN = new TipoUsuario(3);
	public static final TipoUsuario SUPERUSER = new TipoUsuario(4);
	
	public TipoUsuario(int tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario;
	}
	
	public int getTipo()
	{
		return tipoUsuario;
	}
}
