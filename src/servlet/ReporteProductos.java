package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Venta;
import excepciones.RespuestaServidor;
import negocio.ControladorTransaccion;
import util.JsonResponses;

@WebServlet("/ReporteProductos")
public class ReporteProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ReporteProductos() {
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
			request.setAttribute("url","../jspPrincipales/ReporteProductos/Index.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}
		else if(action.equals("recargarTabla"))
		{
			String fechaMinimastr = request.getParameter("fechaMinima");
			String fechaMaximastr = request.getParameter("fechaMaxima");
			DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.US);
			Date fechaMinima = null, fechaMaxima = null;
			try 
			{
				if(fechaMinimastr != null && fechaMaximastr != null)
				{
					fechaMinima = df.parse(fechaMinimastr);
					fechaMaxima =  df.parse(fechaMaximastr);  
				}
			} 
			catch (ParseException e) 
			{
				fechaMinima = new Date();
				fechaMaxima = new Date();
			}
			ArrayList<Venta> ventas = null;
			try {
				ventas = ControladorTransaccion.buscarVentasDia(fechaMinima, fechaMaxima);
			} catch (RespuestaServidor e) {
				
				e.printStackTrace();
			}
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
			response.getWriter().write(JsonResponses.jsonReporteProductos(ventas));
			
		}
	}

}
