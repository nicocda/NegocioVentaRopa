package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Producto;
import entidades.Usuario;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import negocio.ControladorTransaccion;
import util.JsonResponses;
import util.JsonUtil;
import util.Tipos;

@WebServlet("/ABMProductos")
public class ABMProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ABMProductos() 
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
		HttpSession session = request.getSession(false);
		
		if (action == null)
		{
			request.setAttribute("url","../jspPrincipales/ABMProductos/Index.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}	
		else if (action.equals("alta"))
		{
			RespuestaServidor sr = new RespuestaServidor();

			//Datos del lado del cliente
			String descr= request.getParameter("descripcion"); 
			String precio=request.getParameter("precio");
			String id = request.getParameter("id");
			String descr2 = request.getParameter("descripcion2");
			String precio2 =request.getParameter("precio2");
			String id2 = request.getParameter("id2");
			
			//Parseo para adaptar a la DB
			String	descripcion = descr.replace('"', '\'');
			String	descripcion2 = descr2.replace('"', '\'');
		
			if(descripcion.length() >=45)
				descripcion = descripcion.substring(0, 141) +"...";
			if(descripcion2.length() >=45)
				descripcion2 = descripcion2.substring(0, 141) +"...";
			
			float precioFloat = Tipos.esFloat(precio) ? Float.parseFloat(precio) : -1;
			float precioFloat2 = Tipos.esFloat(precio2) ? Float.parseFloat(precio2) : -1;
			
			Usuario ususario = (Usuario) session.getAttribute("usuario");
			try
			{
				ControladorABM.guardarProducto(id, descripcion, Producto.estado.STOCK.ordinal(), precioFloat, ususario.getSucursal().getId());
				
				if(!descripcion2.isEmpty())
					ControladorABM.guardarProducto(id2, descripcion2, Producto.estado.STOCK.ordinal(), precioFloat2, ususario.getSucursal().getId());
			}
			catch (RespuestaServidor e)
			{
				sr = e;
			}
			
			String mensajesJson = JsonResponses.devolverMensaje(sr, "Producto exitosamente cargado!");
			
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mensajesJson);
		}
		else if (action.equals("buscarId"))
		{
			String tipo = request.getParameter("tipo"), 
				subTipo=request.getParameter("subTipo"),tipo2 = request.getParameter("tipo2"), 
				subTipo2=request.getParameter("subTipo2");
			
			String ID = ControladorABM.obtenerIdCompleto(tipo.charAt(0), subTipo.charAt(0));
			String ID2 = ControladorABM.obtenerIdCompleto2(tipo.charAt(0), subTipo.charAt(0),tipo2.charAt(0),subTipo2.charAt(0));
			
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("{\"id\": \"" + ID + "\", \"id2\": \""+ ID2 +"\" }"); 
		}
		else if (action.equals("recargarTabla"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    //response.getWriter().write(JsonResponses.arrayTodosProductos(ControladorABM.buscarTodosProductos()));
		    response.getWriter().write(JsonUtil.toJson(ControladorABM.buscarTodosProductos()));
		}
		else if (action.equals("recargarCombo"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    //response.getWriter().write(JsonResponses.arrayTodosProductos(ControladorABM.buscarTodosProductosEnStock()));
		    response.getWriter().write(JsonUtil.toJson(ControladorABM.buscarTodosProductosEnStock()));
		}
		else if (action.equals("buscar"))
		{
			String cadena = request.getParameter("valor");
			ArrayList<Producto> productos = ControladorTransaccion.buscarProductosDescripcion(cadena);
			//String mensajeJson = JsonResponses.arrayTodosProductos(productos);
			String mensajeJson = JsonUtil.toJson(productos);
			
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mensajeJson);
		}
	}

}