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
		Usuario u = new Usuario();
		u.setTipoUsuario(1);
		HttpSession session = request.getSession();
		session.setAttribute("usuarioActual", u);
		String link = request.getParameter("link");
		if(link!=null && !link.isEmpty())
			request.setAttribute("url","../jspPrincipales/" +link.trim()+".jsp");
		else
			request.setAttribute("url", "../jspPrincipales/AbmClientes.jsp");
		request.setAttribute("servlet", "");
		request.getRequestDispatcher("jspCompartido/MainLayout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}