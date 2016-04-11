package consola;

import datos.CatalogoProductos;

public class PruebaConsola 
{

	public static void main(String[] args) 
	{
		String id=  CatalogoProductos.ultimoIdProducto('R', 'H');
		System.out.println(id);
		String idNuevo = Integer.toString(Integer.parseInt(id.substring(2, 7))+1);
		
		int a = 5 - idNuevo.length();
		for(int i = 1; i<=a; i++)
		{
			idNuevo = "0"+idNuevo;
		}
		System.out.println(idNuevo);
		String idNuevoCompleto = Character.toString('R').concat(Character.toString('H')).concat(idNuevo);
		System.out.println(idNuevoCompleto);

	}

}
