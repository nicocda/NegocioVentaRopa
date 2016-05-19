package datos;

import java.util.ArrayList;

import entidades.Cuota;
import entidades.Venta;

public class CatalogoCuotas extends CatalogoBase
{
	@SuppressWarnings("unchecked")
	public static ArrayList<Cuota> buscarCuotas(Venta vta) {
		ArrayList<Cuota> cuotas = new ArrayList<Cuota>();
		abrirEntityManager();
		getEm().getTransaction().begin();
		 cuotas = (ArrayList<Cuota>) getEm().createQuery("FROM Cuota c where c.venta_cuota = :idV ").setParameter("idV", vta).getResultList();
		getEm().getTransaction().commit();
		cerrarEntityManager();
		return cuotas;
	}
	

}
