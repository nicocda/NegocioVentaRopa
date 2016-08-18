package datos;

import java.util.ArrayList;
import entidades.Sucursal;


public class CatalogoSucursales extends CatalogoBase
{

	@SuppressWarnings("unchecked")
	public static ArrayList<Sucursal> buscarTodasSucursales()
	{
		abrirEntityManager();
		try
		{
			ArrayList<Sucursal> todasSucursales = (ArrayList<Sucursal>)getEm().createQuery("SELECT s FROM Sucursal s").getResultList();
			return todasSucursales;
		}
		finally
		{
			cerrarEntityManager();
		}
	}
	
	public static Sucursal buscarSucursal(int idSucursal)
	{
		abrirEntityManager();
		
		try
		{
			Sucursal sucursal = getEm().find(Sucursal.class, idSucursal);
			
			
			return sucursal;
		}
		finally
		{
			cerrarEntityManager();
		}
	}
}
