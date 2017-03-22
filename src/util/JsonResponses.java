package util;

import java.util.ArrayList;
import entidades.Entidad;

public class JsonResponses 
{
	public static String listar(ArrayList<? extends Entidad> lista) 
	{
		String output = "{[";
		
		int count = 0;
		
		for (Entidad entidad : lista)
		{
			output = output + entidad.toJson();
			
			count ++;
			
			if (count < lista.size())
				output = output + ", ";
		}
		
		output = output + "]}";
		
		return output;
	}
	
	public static String listarDataTable(ArrayList<? extends Entidad> lista) 
	{
		String output = "[";
		int count = 0;
		
		for (Entidad entidad : lista)
		{
			output = output + entidad.toJson();
			
			count ++;
			
			if (count < lista.size())
				output = output + ", ";
		}
		
		output = output + "]";
		
		return output;
	}
}
