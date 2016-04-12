package consola;

import datos.CatalogoProductos;

public class PruebaConsola 
{

	public static void main(String[] args) 
	{
		String[] arreglo1;
		String[] arreglo2 = new String[4];
		
		int i = -1;
		for(String s : arreglo2)
		{
			i++;
			arreglo2[i] = Integer.toString(i);
		}
		
		arreglo1 = arreglo2;
		
		for(String s : arreglo1)
		{
			System.out.println(s);
		}

	}

}
