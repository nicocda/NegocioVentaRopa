package json;

import java.util.ArrayList;

import entidades.Entidad;

public class Json 
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
}
