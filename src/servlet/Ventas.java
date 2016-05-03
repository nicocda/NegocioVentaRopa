package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.JsonResponses;
import entidades.Cliente;
import entidades.Producto;
import entidades.Producto.estado;
import entidades.Venta;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import negocio.ControladorTransaccion;

@WebServlet("/Ventas")
public class Ventas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Ventas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		Venta vta = (Venta) session.getAttribute("venta");
		String action = request.getParameter("action");	
		
		if (action.equals("agregarProducto"))
		{	
			//Primero intento buscar y validar el producto
			String id = request.getParameter("id");
			Producto pro;
			
			try
			{
				pro= ControladorTransaccion.buscarProducto(id);
			}
			catch(Exception sr)
			{
				pro = null;
			}
			
			RespuestaServidor sr = new RespuestaServidor();
			sr = validarProducto(pro, vta);
			
			//Si el producto ingresado no es correcto, muestro el mensaje
			if (!sr.getStatus())
			{
				response.setContentType("json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(JsonResponses.devolverMensaje(sr, ""));
			}
			//Sino lo agrego a la lista de la venta, y actualizo el importe.
			else	
			{
				vta.addProducto(pro);
				
				float importe= 0;
				for(Producto p : vta.getProductos())
				{
					importe= importe + p.getPrecio().getPrecio();
				}
				vta.setImporte(importe);
				
				session.setAttribute("venta", vta);
				
				response.setContentType("json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(JsonResponses.devolverMensaje(sr, ""));
			}
		}
		else if (action.equals("recargarTabla"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonResponses.arrayTodosProductosVenta(vta));
		}
		else if(action.equals("realizarVenta"))
		{
			int idCliente; 
			try
			{
				idCliente = Integer.parseInt(request.getParameter("idCliente"));
			}
			catch(NumberFormatException nfe)
			{
				idCliente = -1;
			}
			RespuestaServidor sr = new RespuestaServidor();

			Cliente cli;
			try
			{
				cli=ControladorABM.buscarCliente(idCliente);
			}
			catch(RespuestaServidor res)
			{
				cli = null;
			}
			vta.setCliente(cli);
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			vta.setFechaVenta(today.getTime());
			String formaPago = request.getParameter("formaPago");
			vta.setFormaPago(Integer.parseInt(formaPago));
			try
			{
				ControladorTransaccion.registrarVenta(vta);
				session.setAttribute("venta", new Venta());
			}
			catch(RespuestaServidor e)
			{
				sr = e;
			}
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonResponses.devolverMensaje(sr, "La venta se registró con éxito"));
		}
	}
	
	private RespuestaServidor validarProducto(Producto pro, Venta venta)
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if(pro == null)
			sr.addError("El producto ingresado no existe.");
		
		if (pro != null)
		{
			if(pro.getEstado() == estado.VENDIDO.ordinal())
				sr.addError("El producto ingresado ya fue vendido.");
			
			if(pro.getEstado() == estado.SEÑADO.ordinal())
				sr.addError("El producto ingresado está señado.");
		
			boolean enLista = false;
			for(Producto p : venta.getProductos())
			{
				if(p.getId().equals(pro.getId()))
					enLista = true;
			}
			if(enLista)
				sr.addError("El producto seleccionado ya fue ingresado en esta venta");
		}
		return sr;
	}
}
