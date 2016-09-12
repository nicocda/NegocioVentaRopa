package consola;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import datos.CatalogoClientes;
import datos.CatalogoProductos;
import datos.CatalogoVentas;
import excepciones.RespuestaServidor;

public class PruebaConsola 
{
	public static void main(String[] args) throws RespuestaServidor 
	{
		Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
		
		System.out.println(gson.toJson(CatalogoVentas.buscarVenta(1).getTarjeta().getTipoTarjeta()));
		
	}
}
