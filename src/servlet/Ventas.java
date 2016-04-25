package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Producto;
import entidades.Venta;
import negocio.ControladorTransaccion;

@WebServlet("/Ventas")
public class Ventas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 ArrayList<Producto> productosVenta;
 
    public Ventas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("agregarProducto"))
		{
			String id = request.getParameter("idProducto");
			Producto pr = ControladorTransaccion.buscarProducto(id);
			productosVenta.add(pr);
			request.setAttribute("productosVenta", productosVenta);
			request.setAttribute("url","../jspPrincipales/Ventas.jsp");
			request.getRequestDispatcher("jspCompartido/MainLayout.jsp").forward(request, response);
		}
		
	}

}
