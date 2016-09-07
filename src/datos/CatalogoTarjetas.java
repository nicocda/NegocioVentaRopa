package datos;

import java.util.ArrayList;
import entidades.TipoTarjeta;



public class CatalogoTarjetas extends CatalogoBase
{
	@SuppressWarnings("unchecked")
	public static ArrayList<TipoTarjeta> buscarTipoTarjetas()
	{
		abrirEntityManager();
		try
		{
			ArrayList<TipoTarjeta> tipostarjetas = (ArrayList<TipoTarjeta>)getEm().createQuery("SELECT tp FROM TipoTarjeta tp").getResultList();
			return tipostarjetas;
		}
		finally
		{
			cerrarEntityManager();
		}
	}
}
