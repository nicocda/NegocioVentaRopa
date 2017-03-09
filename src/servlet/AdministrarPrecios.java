package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Producto;
import excepciones.RespuestaServidor;
import negocio.ControladorTransaccion;
import util.JsonResponses;
import util.Tipos;

@WebServlet("/AdministrarPrecios")
public class AdministrarPrecios extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public AdministrarPrecios()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		
		if (action == null)
		{
			request.setAttribute("url","../jspPrincipales/AdministrarPrecios/Index.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}
		else if (action.equals("guardarPrecios"))
		{
			RespuestaServidor sr = new RespuestaServidor();
			
			String productosRequest = request.getParameter("productos");
			String fechaInicioRequest = request.getParameter("fechaInicio");
			String fechaFinRequest = request.getParameter("fechaFin");
			String porcentajeRequest = request.getParameter("porcentaje");
			
			//Parámetros que van al servidor
			float porcentaje = Tipos.esFloat(porcentajeRequest) ? Float.parseFloat(porcentajeRequest)/100 : 0; 			
			String[] productos = stringToArray(productosRequest);
			
			DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.US);
			Date fechaInicio = null, fechaFin = null;
			try 
			{
				if(fechaInicioRequest != null)
				{
					fechaInicio = df.parse(fechaInicioRequest);
				}
			} 
			catch (ParseException e) 
			{
				fechaInicio = null;
			}
			
			try 
			{
				if(fechaFinRequest != null)
				{
					fechaFin = df.parse(fechaFinRequest);
				}
			} 
			catch (ParseException e) 
			{
				fechaFin = null;
			}
			
			try 
			{
				ControladorTransaccion.guardarPrecios(porcentaje, productos, fechaInicio, fechaFin);
			} 
			catch (RespuestaServidor e) 
			{
				sr = e;
			}
			
			String mensajesJson = JsonResponses.devolverMensaje(sr, "Precios guardados!");
			
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mensajesJson);
		}
		else if (action.equals("buscarProducto"))
		{
			String productoRequest = request.getParameter("producto");
			
			Producto producto = ControladorTransaccion.buscarProducto(productoRequest);
			
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonResponses.unProducto(producto));
		}
	}
	
	private String[] stringToArray(String input)
	{
		if(input!= null && !input.isEmpty())
		{
			if (input.equals("[]"))
				return new String[0];
			
			input = input.split("\\[")[1];
			input = input.split("\\]")[0];
			input = input.replace("\"", "");
			
			return input.split(",");
		}
		
		return new String[0];
	}
}
