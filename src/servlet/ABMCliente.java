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
		
		
		
			int id;
			if(request.getParameter("id").isEmpty())  id=0; else
			id = Integer.parseInt(request.getParameter("id"));
			String nya = request.getParameter("nombreApellido");
			String dire = request.getParameter("direccion");
			String tel = request.getParameter("telefono");
			RespuestaServidor sr = ControladorABM.agregarCliente(new Cliente(id,nya,dire,tel));
			String mensajesJson = JsonResponses.devolverMensaje(sr, "Se guardo correctamente el cliente");
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mensajesJson);
		
	}

}
