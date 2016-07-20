package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import negocio.ControladorTransaccion;
import util.JsonResponses;


@WebServlet("/ReporteVentas")
public class ReporteVentas extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ReporteVentas()
    {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getParameter("action");
		if (action == null)
		{
			request.setAttribute("url","../jspPrincipales/ReporteVentas/Index.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}	
		else if (action.equals("mostrarVenta"))
		{
			String fechaMinimastr = request.getParameter("fechaMinima");
			String fechaMaximastr = request.getParameter("fechaMaxima");
			String idClientestr = request.getParameter("idCliente");
			String tipoPagostr = request.getParameter("tipoPago");
				
			//Busco las fechas y parseo a Date
			
			DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.US);
			Date fechaMinima = null, fechaMaxima = null;
			
			int tipoPago;
			int idCliente;
			if(!idClientestr.isEmpty() && idClientestr!= null)
			{	
				try
				{
					idCliente = Integer.parseInt(idClientestr);
				}
				catch(NumberFormatException e)
				{
					idCliente = -2;
				}
			}
			else
			{
				idCliente = -1;
			}
			
			if(!tipoPagostr.isEmpty() && tipoPagostr!= null)
			{	
				try
				{
					tipoPago = Integer.parseInt(tipoPagostr);
				}
				catch(NumberFormatException e)
				{
					tipoPago = -2;
				}
			}
			else
			{
				tipoPago = -1;
			}
			
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
				fechaMinima = new Date(1);
				fechaMaxima = new Date();
			}
		
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    try
		    {
				response.getWriter().write(JsonResponses.jsonVentas(ControladorTransaccion.buscarVentasDia(fechaMinima, fechaMaxima, idCliente, tipoPago)));
			}
		    catch (RespuestaServidor e)
		    {
				e.printStackTrace();
			}
		}
		else if (action.equals("detalleVenta"))
		{
			request.setAttribute("idVenta", request.getParameter("idVenta"));
			request.getRequestDispatcher("jspPrincipales/DetalleVenta.jsp").forward(request, response);
		}
	}

}
