package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import util.JsonResponses;

@WebServlet("/ABMProducto")
public class ABMProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ABMProducto() 
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
		
		if (action.equals("alta"))
		{
			String descripcion = request.getParameter("descripcion"), 
					precio=request.getParameter("precio"),
					id = request.getParameter("id"),
					tipo = request.getParameter("tipo"), 
					subTipo=request.getParameter("subTipo");
			float precioFloat;
			try
			{
				precioFloat = Float.parseFloat(precio);
			}
			catch(NumberFormatException e)
			{
				precioFloat = -1;
			}		
			RespuestaServidor sr = new RespuestaServidor();
			try
			{
				ControladorABM.modificarCargarProducto(id,tipo.charAt(0),subTipo.charAt(0), descripcion, 1, precioFloat);
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
					subTipo=request.getParameter("subTipo");
			String ID = ControladorABM.obtenerIdCompleto(tipo.charAt(0), subTipo.charAt(0));
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("{\"id\": \"" + ID + "\"}"); 
		}
	}

}