package consola;

import datos.CatalogoClientes;
import datos.CatalogoProductos;
import entidades.Producto;

public class PruebaConsola 
{

	public static void main(String[] args) 
	{
		System.out.println(CatalogoClientes.buscarCliente(2).getNombreApellido());
	}

}
