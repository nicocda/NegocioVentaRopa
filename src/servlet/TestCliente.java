package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.CatalogoClientes;
import datos.CatalogoProductos;
import entidades.Cliente;
import entidades.Precio;
import entidades.Producto;
import excepciones.ErrorServidor;
import excepciones.RespuestaServidor;

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
		if(request.getParameter("btnAceptar")!=null)
		{
			String descripcion = request.getParameter("txtDescripcion"), id = request.getParameter("txtID"), precio=request.getParameter("txtPrecio");
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			Producto producto = new Producto(descripcion, 1, id, new Precio(today.getTime(), Float.parseFloat(precio)));
			RespuestaServidor sr = CatalogoProductos.agregarProducto(producto);
			ArrayList<String> errores = new ArrayList<String>();
			for(ErrorServidor e : sr.getErrors())
				errores.add(e.getErrorMessage());
			request.setAttribute("errores", errores);
		}
		request.setAttribute("servlet", "");
		request.setAttribute("url", "../jspPrincipales/prueba.jsp");
		request.getRequestDispatcher("jspCompartido/MainLayout.jsp").forward(request, response);
	}

}
