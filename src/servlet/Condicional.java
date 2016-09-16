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

import com.mysql.jdbc.Util;

import entidades.Cliente;
import entidades.Prestamo;
import entidades.Producto;
import entidades.Usuario;
import entidades.Venta;
import entidades.Producto.estado;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
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
		Prestamo prestamo = (Prestamo) session.getAttribute("prestamo");
		String action = request.getParameter("action");	
		
		if (action == null)
		{
			request.setAttribute("url","../jspPrincipales/Condicional/Index.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}
		else if(action.equals("registrarCondicional"))
		{
			int idCliente = util.Tipos.esEntero(request.getParameter("idCliente"))?Integer.parseInt(request.getParameter("idCliente")) : -1; 
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
			Usuario usu = (Usuario) session.getAttribute("usuario");
			prestamo.setSucursal(usu.getSucursal());
			prestamo.setCliente(cli);
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			prestamo.setFechaVenta(today.getTime());
			prestamo.setPrestamo(true);
			for(Producto p : prestamo.getProductos())
				if(p.getEstado() == Producto.estado.VENDIDO.ordinal())
					ControladorTransaccion.devolverProducto(p);
			try{
				ControladorTransaccion.registrarVenta(prestamo);}
			catch(RespuestaServidor e){
				sr = e;}

			session.setAttribute("prestamo", new Prestamo());
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonResponses.devolverMensaje(sr, "La transacción se registró con éxito"));
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
		else if(action.equals("recargarCombo"))
		{
			ArrayList<Producto> productos = ControladorABM.buscarTodosProductosCondicional();
			response.setContentType("json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(JsonResponses.arrayTodosProductos(productos));
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
			if(prestamo.getProductosArrayList() != null)
			{
			for(Producto p : prestamo.getProductos())
			{
				if(p.getId().equals(pro.getId()))
					enLista = true;
			}
			if(enLista)
				sr.addError("El producto seleccionado ya fue ingresado en esta lista");
			}
		}
		return sr;
	}
	
	private ArrayList<Producto> removeProducto(ArrayList<Producto> productos, Producto p)
	{
		for(Producto pr : productos )
		{
			if(pr.getId().equals(p.getId()))
			{
				productos.remove(pr);
				return productos;
			}
			
		}
		return productos;
	}
}


