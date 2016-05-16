package consola;

import datos.CatalogoClientes;
import datos.CatalogoConfiguracion;
import datos.CatalogoEventLog;
import datos.CatalogoProductos;
import entidades.EventLog;
import entidades.Producto;

public class PruebaConsola 
{
	public static void main(String[] args) 
	{
		
			System.out.println(CatalogoConfiguracion.buscarConfiguracion().isPermitirLog());
	}
}
