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

import entidades.Venta;
import excepciones.RespuestaServidor;
import negocio.ControladorTransaccion;
import util.JsonResponses;
import util.Tipos;


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
			mostrarVenta(request, response);
		
		else if (action.equals("detalleVenta"))
			detalleVenta(request, response);
	}
	
	private void mostrarVenta(HttpServletRequest request, HttpServletResponse response)
	{
		String fechaMinimastr = request.getParameter("fechaMinima");
		String fechaMaximastr = request.getParameter("fechaMaxima");
		String idClientestr = request.getParameter("idCliente");
		String tipoPagostr = request.getParameter("tipoPago");
			
		//Busco las fechas y parseo a Date
		
		DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.US);
		Date fechaMinima = null, fechaMaxima = null;
		
		int idCliente = Tipos.esEntero(idClientestr) ? Integer.parseInt(idClientestr) : 0;
		
		int tipoPago = Tipos.esEntero(tipoPagostr) ? Integer.parseInt(tipoPagostr) : -1;
		
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
	    catch (RespuestaServidor | IOException e)
	    {
			e.printStackTrace();
		}
	}
	
	private void detalleVenta(HttpServletRequest request, HttpServletResponse response)
	{
		int idVenta = Integer.parseInt(request.getParameter("idVenta"));
		Venta vta = ControladorTransaccion.buscarVenta(idVenta);
		response.setContentType("json");
	    response.setCharacterEncoding("UTF-8");
		if (vta != null)
		{
			String mensaje = JsonResponses.ventaEntera(vta);
		    try 
		    {
				response.getWriter().write(mensaje);
			} 
		    catch (IOException e) 
		    {
				e.printStackTrace();
			}
		}
	}

}
