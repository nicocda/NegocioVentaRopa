package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import excepciones.RespuestaServidor;
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
		//esta sr viene del controlador. Si está vacia muestra mensaje de exito.
		RespuestaServidor sr = new RespuestaServidor();
		sr.addError("Soy un error");
		sr.addError("Soy otro error");
		sr.addError("Yo también soy un error");
		String mensajesJson = JsonResponses.devolverMensaje(sr);
		
		response.setContentType("json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(mensajesJson);
	}

}
