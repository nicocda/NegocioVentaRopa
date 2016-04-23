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

import datos.CatalogoClientes;
import datos.CatalogoProductos;
import entidades.Cliente;
import entidades.Precio;
import entidades.Producto;
import entidades.Usuario;
import excepciones.ErrorServidor;
import excepciones.RespuestaServidor;

@WebServlet("/Index")
public class Index extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public Index()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String link = request.getParameter("link");
		request.setAttribute("servlet", "");
		if(link!=null && !link.isEmpty())
		{
			if (link.equals("LogOut"))
			{
				HttpSession session = request.getSession(false);
				if (session != null)
				    session.invalidate();
				request.getRequestDispatcher("jspPrincipales/LogIn.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("url","../jspPrincipales/" +link.trim()+".jsp");
				request.getRequestDispatcher("jspCompartido/MainLayout.jsp").forward(request, response);
			}
		}
		else
			request.getRequestDispatcher("jspPrincipales/LogIn.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
