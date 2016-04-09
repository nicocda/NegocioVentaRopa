package negocio;

import java.util.Calendar;

import datos.CatalogoProductos;
import entidades.Precio;
import entidades.Producto;

public class ControladorABM 
{
	
	public void agregarProducto(char tipo, char subTipo, String descripcion, int estado, int idVenta, int idDevolucion, float precio)
	{
		String id = CatalogoProductos.ultimoIdProducto(tipo, subTipo);
		//Agrego un valor
		String idNuevo = Integer.toString(Integer.parseInt(id.substring(2, 11))+1);
		String idNuevoCompleto = Character.toString(tipo).concat(Character.toString(subTipo)).concat(idNuevo);
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		Producto pr = new Producto(descripcion,estado,idNuevoCompleto,new Precio(today.getTime(), precio));
		CatalogoProductos.agregarProducto(pr);
	}
	
	public void modificarProducto(String id, String newDescripcion,int estado, float newPrecio)
	{
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		CatalogoProductos.modificarProducto(new Producto(newDescripcion,estado, id,new Precio(today.getTime(), newPrecio)));
	}
	

}
