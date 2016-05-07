package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import util.JsonResponses;

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
		if (action.equals("agregarCliente"))
		{
			int id;

			String idString = request.getParameter("id");
			if(idString == null || idString.isEmpty())  
				id=0; 
			else
				id = Integer.parseInt(idString);
			
			String nombreApellido = request.getParameter("nombreApellido");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");
			
			RespuestaServidor sr = new RespuestaServidor();
			try
			{
				ControladorABM.guardarCliente(id, nombreApellido, direccion, telefono);
			}
			catch(RespuestaServidor res)
			{
				sr = res;
			}
			String mensajesJson = JsonResponses.devolverMensaje(sr, "Se guardo correctamente el cliente");
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mensajesJson);
		}
		else if (action.equals("recargarTabla"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonResponses.arrayTodosClientes(ControladorABM.buscarTodosClientes()));
		}
	}

}
