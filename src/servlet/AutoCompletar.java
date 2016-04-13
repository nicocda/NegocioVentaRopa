package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CatalogoClientes;
import entidades.Cliente;
import util.JsonResponses;

@WebServlet("/AutoCompletar")
public class AutoCompletar extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public AutoCompletar()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("json");
	    response.setCharacterEncoding("UTF-8");
	    ArrayList<Cliente> clientes = CatalogoClientes.buscarClientes();
	    response.getWriter().write(JsonResponses.jsonClientes(clientes)); 
	}

}
