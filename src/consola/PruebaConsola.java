package consola;

import java.util.Date;

import datos.CatalogoClientes;
import datos.CatalogoConfiguracion;
import datos.CatalogoEventLog;
import datos.CatalogoProductos;
import entidades.EventLog;
import entidades.Precio;
import entidades.Producto;
import excepciones.RespuestaServidor;

public class PruebaConsola 
{
	public static void main(String[] args) throws RespuestaServidor 
	{
		
			for(int i = 0; i<=100; i++)
			{
				Producto p = new Producto();
				p.setDescripcion("descrip:"+i);
				p.setId("RH"+i);
				p.setEstado(1);
				Precio precio = new Precio();
				precio.setFecha(new Date());
				precio.setPrecio(222);
				precio.setProducto(p);
				p.addPrecio(precio);
				CatalogoProductos.guardarProducto(p);
			}
	}
}
