package util;

import excepciones.RespuestaServidor;

public class JsonResponses 
{

	public static String devolverMensaje(RespuestaServidor sr, String mensajeExito)
	{
		String tipoMensaje = sr.getErrors().isEmpty() ? mensajeExito : "error";
		String mensajesJson = "{\"mensajes\":[{";
		for(int i = 1; i <= sr.getErrors().size(); i++)
		{
			if(sr.getErrors().size()!=i)
				mensajesJson = mensajesJson + "\"mensaje\":\""+sr.getErrors().get(i-1).getErrorMessage()+"\"},{";
			else
				mensajesJson = mensajesJson + "\"mensaje\":\""+sr.getErrors().get(i-1).getErrorMessage()+"\"";
		}
		mensajesJson = mensajesJson + "}], \"tipoMensaje\":\"" + tipoMensaje +"\"}";
		return mensajesJson;
	}
}
