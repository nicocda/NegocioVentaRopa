package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import entidades.Cliente;
import entidades.Producto;
import entidades.Venta;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import util.JsonResponses;
import util.JsonUtil;

@WebServlet("/ABMClientes")
public class ABMClientes extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public ABMClientes() 
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
		
		if (action == null)
		{
			request.setAttribute("url","../jspPrincipales/ABMClientes/Index.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}
		else if (action.equals("agregarCliente"))
		{
			int id;

			String idString = request.getParameter("id");
			if(idString == null || idString.isEmpty())  
				id=0; 
			else
				id = Integer.parseInt(idString);
			
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");
			
			RespuestaServidor sr = new RespuestaServidor();
			try
			{
				ControladorABM.guardarCliente(id, nombre, apellido, direccion, telefono);
			}
			catch(RespuestaServidor res)
			{
				sr = res;
			}
			String mensajesJson = JsonResponses.devolverMensaje(sr, "Se guardo correctamente el cliente");
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(mensajesJson);
		}
		else if (action.equals("recargarTabla"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    
		    ArrayList<Cliente> listaClientes = ControladorABM.buscarTodosClientes();
		    	
		    JsonObject jsonResponse = new JsonObject();
		    		    
		    for(Cliente cliente : listaClientes)
		    {
			    JsonObject jsonCliente = new JsonObject();
		    	
		    	jsonCliente.addProperty("apellido", cliente.getApellido());
		    	jsonCliente.addProperty("direccion", cliente.getDireccion());
		    	jsonCliente.addProperty("id", cliente.getId());
		    	jsonCliente.addProperty("nombre", cliente.getNombre());
		    	jsonCliente.addProperty("telefono", cliente.getTelefono());
		    			    	
			    JsonArray arrayVenta = new JsonArray();
		    	
		    	for(Venta venta : cliente.getVentasArrayList())
		    	{
				    JsonObject jsonVenta = new JsonObject();

				    jsonVenta.addProperty("fecha", venta.getFechaVenta().toString());
				    jsonVenta.addProperty("formaPago", venta.getFormaPago());
				    jsonVenta.addProperty("importe", venta.getImporte());
		    		
				    arrayVenta.add(jsonVenta);
				    
				    JsonArray arrayProducto = new JsonArray();
				    
		    		for(Producto producto : venta.getProductosArrayList())
		    		{
		    		    JsonObject jsonProducto = new JsonObject();

		    		    jsonProducto.addProperty("descripcion", producto.getDescripcion());
		    		    jsonProducto.addProperty("estado", producto.getEstado());
		    		    jsonProducto.addProperty("id", producto.getId());
		    		    jsonProducto.addProperty("precio", producto.getPrecio().getPrecio());
		    		    
					    arrayProducto.add(jsonProducto);
		    		}
		    	}
		    	
		    	jsonCliente.add("ventas", arrayVenta);
		    }
		    
		    response.getWriter().write(JsonUtil.toJson(ControladorABM.buscarTodosClientes()));
		}
	}

}

