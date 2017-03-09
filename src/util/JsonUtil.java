package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
