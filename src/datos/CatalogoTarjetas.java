package datos;

import java.util.ArrayList;

import entidades.Tarjeta;
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
	
	public static TipoTarjeta buscartipoTarjeta(int id)
	{
		abrirEntityManager();
		try
		{
			return getEm().find(TipoTarjeta.class, id);
		}
		finally
		{
			cerrarEntityManager();
		}
	}

	public static Tarjeta buscarTarjeta(Tarjeta tarjeta) {
		abrirEntityManager();
		try
		{
			return getEm().find(Tarjeta.class, tarjeta.getNroTarjeta());
		}
		finally
		{
			cerrarEntityManager();
		}
		
	}

	public static void registrarTarjeta(Tarjeta tarjeta) {
		abrirEntityManager();
		getEm().getTransaction().begin();
		getEm().persist(tarjeta);
		getEm().getTransaction().commit();
		
	}
}
