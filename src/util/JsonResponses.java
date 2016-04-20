package util;

import java.util.ArrayList;

import entidades.Cliente;
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
	
	public static String jsonClientes(ArrayList<Cliente> clientes)
	{
		String rsp = "{\"array\": [\"";
	    for(int i=0;i<clientes.size()-1;i++)
	    {
	    	rsp=rsp.concat(clientes.get(i).getNombreApellido()+"\", \"");
	    }
	    rsp=rsp.concat(clientes.get(clientes.size()-1).getNombreApellido()+"\"]}");
	    return rsp;
	}
	
	public static String arrayTodosClientes(ArrayList<Cliente> clientes)
	{
		String rsp = "{\"clientes\": [";
	    for(int i=0;i<clientes.size()-1;i++)
	    {
	    	rsp= rsp + "{\"nombreApellido\": \"" + clientes.get(i).getNombreApellido()+"\", \"direccion\": \"" + clientes.get(i).getDireccion() + "\", \"id\": \"" + clientes.get(i).getId() + "\", \"telefono\": \"" + clientes.get(i).getTelefono() + "\"},";
	    }
	    rsp= rsp + "{\"nombreApellido\": \"" + clientes.get(clientes.size()-1).getNombreApellido()+"\", \"direccion\": \"" + clientes.get(clientes.size()-1).getDireccion() + "\", \"id\": \"" + clientes.get(clientes.size()-1).getId() + "\", \"telefono\": \"" + clientes.get(clientes.size()-1).getTelefono() +"\"}]}";
	    return rsp;
	}
}
