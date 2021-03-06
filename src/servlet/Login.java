package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Prestamo;
import entidades.Usuario;
import entidades.Venta;
import negocio.ControladorABM;

@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String action = request.getParameter("action");
		if (action == null)
		{
			request.setAttribute("servlet", "");
			request.getRequestDispatcher("jspPrincipales/Login/LogIn.jsp").forward(request, response);
		}
		else if (action.equals("login"))
		{
			String usuario = (String) request.getParameter("usuario");
			String pass = (String) request.getParameter("password");
			Usuario usu = ControladorABM.validarUsuario(usuario, pass);
			if (usu != null)
			{
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usu);
				//Agrego esto para probar lo de venta nomas, despues hay que sacarlo
				Venta vta = new Venta();
				Prestamo prestamo = new Prestamo();
				session.setAttribute("venta", vta);
				session.setAttribute("prestamo", prestamo);
				response.setContentType("json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write("{\"exito\":true,\"mensaje\":\"Conectado\"}");			
			}
			else
			{
				response.setContentType("json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write("{\"exito\":false,\"mensaje\":\"*Los datos ingresados no son correctos\"}");
			}
		}
		else if (action.equals("registro"))
		{
			//c�digo del alta de usuario.
		}

	}

}
