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
import util.JsonUtil;
import util.Tipos;

@WebServlet("/ABMClientes")
public class ABMClientes extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ABMClientes() 
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
			request.setAttribute("url","../jspPrincipales/ABMClientes/Index.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}
		else if (action.equals("agregarCliente"))
		{
			agregarCliente(request, response);
		}
		else if (action.equals("recargarTabla"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonUtil.toJson(ControladorABM.buscarTodosClientes()));
		}
	}
	
	private void agregarCliente(HttpServletRequest request, HttpServletResponse response)
	{
		String idString = request.getParameter("id");
		
		int id = Tipos.esEntero(idString) ? Integer.parseInt(idString) : 1;
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");
		
		RespuestaServidor sr = new RespuestaServidor();
		try
		{
			ControladorABM.guardarCliente(id, nombre, apellido, direccion, telefono);
		}
		catch(RespuestaServidor res)
		{
			sr = res;
		}
		
		String mensajesJson = JsonResponses.devolverMensaje(sr, "Se guardo correctamente el cliente");
		
		response.setContentType("json");
	    response.setCharacterEncoding("UTF-8");
	    
	    try 
	    {
			response.getWriter().write(mensajesJson);
		} 
	    catch (IOException e)
	    {
			e.printStackTrace();
		}
	}

}

