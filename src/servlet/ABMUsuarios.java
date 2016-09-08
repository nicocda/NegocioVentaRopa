package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import entidades.Usuario;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import util.JsonResponses;
import util.JsonUtil;

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
		HttpSession session = request.getSession(false);
		if (action == null)
		{
			request.setAttribute("url","../jspPrincipales/ABMUsuarios/Index.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}	
		else if (action.equals("guardarUsuario"))
		{
			String usuario = request.getParameter("usuario");			
			String nombreApellido = request.getParameter("nombreApellido");
			String email = request.getParameter("email");
			String tipoUsuariostr = request.getParameter("tipo");
			String password = request.getParameter("password");
			String sucursalstr = request.getParameter("sucursal");
			
			int idSucursal;
			try
			{
				idSucursal = Integer.parseInt(sucursalstr);
			}
			catch(NumberFormatException e)
			{
				idSucursal = -1;
			}
			
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
			 String mensaje="";
			try
			{
				
				 Usuario user = (Usuario) session.getAttribute("usuario");
				 if(validaUsuario(sr,usuario,password, tipoUsuario,user))
				 {
					 ControladorABM.guardarUsuario(usuario, password, nombreApellido, email, tipoUsuario,idSucursal);
					 mensaje="Se guardo correctamente el usuario";
				 }
				 else
				 {
					 sr.addError("No tiene permisos para crear este tipo de usuario.");
				 }
			}
			catch(RespuestaServidor res)
			{
				sr = res;
			}
			String mensajesJson = JsonResponses.devolverMensaje(sr, mensaje);
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mensajesJson);
		}
		else if (action.equals("eliminarUsuario"))
		{
			String usuario = request.getParameter("usuario");
			ControladorABM.eliminarUsuario(usuario);
		}
		else if (action.equals("recargarTabla"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    
		   // response.getWriter().write(JsonUtil.toJson(ControladorABM.buscarTodosUsuarios()));		    
		    
		    response.getWriter().write(JsonResponses.arrayTodosUsuarios(ControladorABM.buscarTodosUsuarios()));
		}
	}

	private boolean validaUsuario(RespuestaServidor sr, String usuario, String password, int tipoUsuario, Usuario user) 
	{
		boolean validaUsuario=true;
		if(usuario=="" || usuario== null)
		{
			 sr.addError("Ingrese un usuario");
			 validaUsuario=false;
		}
		if(password=="" || password == null)
		{
			 sr.addError("Ingrese un password");
			 validaUsuario=false;
		}
		if(user.getTipoUsuario()<=tipoUsuario)
		{
			sr.addError("No tiene permisos para crear este tipo de usuario.");
			validaUsuario=false;
		}
		return validaUsuario;
	}

}
