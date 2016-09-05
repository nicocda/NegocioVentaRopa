package util;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entidades.Cliente;
import entidades.Producto;
import entidades.Venta;
import entidades.Producto.estado;
import excepciones.RespuestaServidor;

public class JsonUtil 
{

	private static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
	
	public static String toJson(Object entidad)
	{
		System.out.println(gson.toJson(entidad));
		
		return gson.toJson(entidad);
	}

}
