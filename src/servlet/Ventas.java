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
import entidades.Venta;
import excepciones.ErrorServidor;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import negocio.ControladorTransaccion;

@WebServlet("/Ventas")
public class Ventas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Ventas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Venta vta = (Venta) session.getAttribute("venta");
		String action = request.getParameter("action");	
		String mensaje=null;
		//hago esta validacion para saber si entra del boton del header o de otro lado
		if (action.equals("agregarProducto"))
		{
			String id = request.getParameter("id");
			Producto pro = ControladorTransaccion.buscarProducto(id);
			
			if(pro != null)
			{
				vta.addProducto(pro);
				session.setAttribute("venta", vta);
				float importe= 0;
				for(Producto p : vta.getProductos())
				{
					importe= importe + p.getPrecio().getPrecio();
				}
				vta.setImporte(importe);
				mensaje = "{\"error\":false, \"id\":\""+ pro.getId() +"\", \"descripcion\":\""+pro.getDescripcion()+"\", \"precio\":\""+pro.getPrecio().getPrecio()+"\", \"importe\":\""+importe+"\"}";
				request.setAttribute("productosVenta", vta.getProductos());
			}
			else
			{
				mensaje = "{\"error\":true, \"mensajeError\":\"no existe producto\"}";
			}
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mensaje);
		}
		else if (action.equals("recargarTabla"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonResponses.arrayTodosProductosVenta(vta.getProductos()));
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

			Cliente cli = null;
			try
			{
				cli=ControladorABM.buscarCliente(idCliente);
			}
			catch(RespuestaServidor res)
			{
				
			}
			String msj;
			vta.setCliente(cli);
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			vta.setFechaVenta(today.getTime());
			String formaPago = request.getParameter("formaPago");
			vta.setFormaPago(Integer.parseInt(formaPago));
			try
			{
				ControladorTransaccion.registrarVenta(vta);
			}
			catch(RespuestaServidor e)
			{
				sr = e;
			}
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonResponses.devolverMensaje(sr, "Éxito(?)"));
		}
	}
}
