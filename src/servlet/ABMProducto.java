package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import datos.CatalogoClientes;
import datos.CatalogoDevolucion;
import datos.CatalogoProductos;
import datos.CatalogoVentas;
import entidades.Producto.estado;
import entidades.Cliente;
import entidades.Devolucion;
import entidades.Precio;
import entidades.Producto;
import entidades.Venta;
import entidades.Venta.formaPago;
import excepciones.ErrorServidor;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import util.JsonResponses;

@WebServlet("/ABMProducto")
public class ABMProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ABMProducto() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getParameter("action");
		
		if (action.equals("alta"))
		{
			String descripcion = request.getParameter("descripcion"), 
					precio=request.getParameter("precio");
			float precioFloat;
			try
			{
				precioFloat = Float.parseFloat(precio);
			}
			catch(NumberFormatException e)
			{
				precioFloat = -1;
			}		
			RespuestaServidor sr = ControladorABM.agregarProducto('R', 'H', descripcion, 1, precioFloat);
	
			String mensajesJson = JsonResponses.devolverMensaje(sr, "Producto exitosamente cargado!");
			
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mensajesJson);
		}
		else if (action.equals("buscarId"))
		{
			String tipo = request.getParameter("tipo"), 
					subTipo=request.getParameter("subTipo");
			String ID = ControladorABM.obtenerIdCompleto(tipo.charAt(0), subTipo.charAt(0));
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("{\"id\": \"" + ID + "\"}"); 
		}
	}

}
