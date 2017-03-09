package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Prestamo;
import entidades.Producto;
import entidades.Producto.estado;
import excepciones.RespuestaServidor;
import negocio.ControladorTransaccion;
import util.JsonResponses;
import util.JsonUtil;

/**
 * Servlet implementation class Condicional
 */
@WebServlet("/Condicional")
public class Condicional extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Condicional() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		Prestamo prestamo = (Prestamo) session.getAttribute("condicional");
		String action = request.getParameter("action");	
		
		if (action == null)
		{
			request.setAttribute("url","../jspPrincipales/Condicional/Index.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}
		else if(action.equals("registrarCondicional"))
		{
			//TODO
		}
		else if (action.equals("agregarProducto"))
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
			sr = validarProducto(pro, prestamo);
			
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
				prestamo.addProducto(pro);
				
				float importe= 0;
				for(Producto p : prestamo.getProductos())
				{
					if(p.getEstado() == Producto.estado.STOCK.ordinal())
						importe= importe + p.getPrecio().getPrecio();
					
				}
				prestamo.setImporte(importe);
				session.setAttribute("prestamo", prestamo);
				
				response.setContentType("json");
			    response.setCharacterEncoding("UTF-8");
			    //SobreCarga
			    String json = JsonResponses.devolverMensaje(sr, "");
			    response.getWriter().write(json);
			}
		}
		else if (action.equals("recargarTabla"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
				//Gson g = new Gson();
			    //response.getWriter().write(g.toJson(vta.getProductosArrayList()));
		    String toJson = JsonResponses.arrayTodosProductosVenta(prestamo);
		    System.out.println(toJson);
			   response.getWriter().write(toJson);
			
		}
		else if(action.equals("actualizarTotal"))
		{
			String msg = "{\"tot\":\""+prestamo.getImporte()+"\"}";
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(msg);
		}
		else if(action.equals("borrarProducto"))
		{
			String idProducto = request.getParameter("idProducto");
			Producto pr = ControladorTransaccion.buscarProducto(idProducto);
			Prestamo prestamoActual = (Prestamo) session.getAttribute("prestamo");
			ArrayList<Producto> productosEnPrestamo = prestamoActual.getProductosArrayList();
			prestamoActual.setProductos(removeProducto(productosEnPrestamo, pr));
			session.setAttribute("prestamo", prestamoActual);
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonUtil.toJson(prestamoActual.getProductosArrayList()));
		}
	}
	
	private RespuestaServidor validarProducto(Producto pro, Prestamo prestamo)
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if(pro == null)
			sr.addError("El producto ingresado no existe.");
		
		else
		{
			if(pro.getEstado() == estado.SEÑADO.ordinal())
				sr.addError("El producto ingresado está señado.");
			
			if(pro.getEstado() == estado.CONDICIONAL.ordinal())
				sr.addError("El producto ingresado no se encuentra en el local, ya que esta en modo condicional.");
		
			boolean enLista = false;
			for(Producto p : prestamo.getProductos())
			{
				if(p.getCodigoProducto().equals(pro.getCodigoProducto()))
					enLista = true;
			}
			if(enLista)
				sr.addError("El producto seleccionado ya fue ingresado en esta lista");
		}
		return sr;
	}
	
	private ArrayList<Producto> removeProducto(ArrayList<Producto> productos, Producto p)
	{
		for(Producto pr : productos )
		{
			if(pr.getCodigoProducto().equals(p.getCodigoProducto()))
			{
				productos.remove(pr);
				return productos;
			}
			
		}
		return productos;
	}
}


