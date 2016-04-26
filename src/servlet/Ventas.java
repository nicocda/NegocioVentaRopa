package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Producto;
import entidades.Venta;
import negocio.ControladorABM;
import negocio.ControladorTransaccion;

@WebServlet("/Ventas")
public class Ventas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Venta vta;
    public Ventas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String action = null;
		String action = null;
		//hago esta validacion para saber si entra del boton del header o de otro lado
		if (action == null ) 
			{
			action  = request.getParameter("action");	
				vta = new Venta();
			}
		else if (action.equals("agregarProducto"))
		{
		
			String id = request.getParameter("id");
			Producto pro = ControladorTransaccion.buscarProducto(id);
			String mensaje;
			if(pro != null)
			{
				vta.getProductos().add(pro);
				mensaje = "{\"error\":false}";//"{\"error\":false, \"id\":\""+ pro.getId() +"\", \"descripcion\":\""+pro.getDescripcion()+"\", \"precio\":\""+pro.getPrecio().getPrecio()+"\"}";
				float importe= 0;
				for(Producto p : vta.getProductos())
				{
					importe= importe + p.getPrecio().getPrecio();
				}
				vta.setImporte(importe);
				request.setAttribute("productosVenta", vta.getProductos());
				request.setAttribute("importe", importe);
			}
			else
			{
				mensaje = "{\"error\":true, \"mensaje\":\"no existe producto\"}";
				
			}
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mensaje);
		}
		else if(action.equals("realizarVenta"))
		{
			String nombreCliente = request.getParameter("nombreCliente");
			//TODO VALIDAR QUE NO SEA NULO
			vta.setCliente(ControladorTransaccion.buscarCliente(nombreCliente));
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			vta.setFechaVenta(today.getTime());
			vta.setFormaPago(Integer.parseInt(request.getParameter("formaPago")));
			//TODO redirect a exito
		}
		
	}

}
