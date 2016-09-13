package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Venta;
import negocio.ControladorTransaccion;
import util.JsonResponses;

/**
 * Servlet implementation class Deudas
 */
@WebServlet("/Deudas")
public class Deudas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deudas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

			int idClie = Integer.parseInt(request.getParameter("idCliente"));
			ArrayList<Venta> ventasMorosas = new ArrayList<Venta>();
			
			ventasMorosas = ControladorTransaccion.buscarVentasCliente(idClie);		
			
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    
			if (ventasMorosas != null)
			{
				String mensaje = JsonResponses.arrayVentasMorosas(ventasMorosas);
			    response.getWriter().write(mensaje);
			}
			else
			
				response.getWriter().write("[]");
			
			
		}

}
