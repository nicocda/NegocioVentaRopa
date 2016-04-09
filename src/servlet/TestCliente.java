package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CatalogoClientes;
import entidades.Cliente;

@WebServlet("/TestCliente")
public class TestCliente extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public TestCliente()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		request.setAttribute("servlet", "");
		request.setAttribute("url", "../jspPrincipales/Ventas.jsp");
		request.getRequestDispatcher("jspCompartido/MainLayout.jsp").forward(request, response);
	}

}
