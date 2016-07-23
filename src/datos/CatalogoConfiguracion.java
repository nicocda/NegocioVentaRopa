package datos;

import entidades.Configuracion;

public class CatalogoConfiguracion extends CatalogoBase
{	
	public static Configuracion buscarConfiguracion()
	{
		abrirEntityManager();
		try
		{
			return getEm().find(Configuracion.class, 1);
		}
		finally
		{
			cerrarEntityManager();
		}
	}

}