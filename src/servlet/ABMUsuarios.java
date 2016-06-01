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

@WebServlet("/ABMUsuarios")
public class ABMUsuarios extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ABMUsuarios() 
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
			request.setAttribute("url","../jspPrincipales/ABMUsuarios.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}	
		else if (action.equals("guardarUsuario"))
		{
			String usuario = request.getParameter("usuario");			
			String nombreApellido = request.getParameter("nombreApellido");
			String email = request.getParameter("email");
			String tipoUsuariostr = request.getParameter("tipo");
			String password = request.getParameter("password");
			
			int tipoUsuario;
			try
			{
				tipoUsuario = Integer.parseInt(tipoUsuariostr);
			}
			catch(NumberFormatException e)
			{
				tipoUsuario = -1;
			}
			
			RespuestaServidor sr = new RespuestaServidor();
			try
			{
				ControladorABM.guardarUsuario(usuario, password, nombreApellido, email, tipoUsuario);
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
		    response.getWriter().write(JsonResponses.arrayTodosUsuarios(ControladorABM.buscarTodosUsuarios()));
		}
	}

}
