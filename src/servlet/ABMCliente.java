package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.RespuestaServidor;
import util.JsonResponses;

@WebServlet("/ABMCliente")
public class ABMCliente extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ABMCliente() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RespuestaServidor sr = new RespuestaServidor();
		sr.addError("Soy un error");
		String mensajesJson = JsonResponses.devolverMensaje(sr, "EXITO");
		response.setContentType("json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(mensajesJson);
	}

}
