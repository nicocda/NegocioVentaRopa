package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Usuario;
import negocio.ControladorTransaccion;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("txtLogin");
		String pass = (String) request.getParameter("txtPass");
		ControladorTransaccion ct = new ControladorTransaccion();
		Usuario usu = ct.buscarUsuario(id, pass);
		request.setAttribute("usuario", usu);
	/*	response.setContentType("json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write("{\"usuario\":\"leo\",\"mensaje\":\"contraseña mal puesta\"}");*/
	    request.getRequestDispatcher("AbmClientes.jsp").forward(request, response);

	}

}
