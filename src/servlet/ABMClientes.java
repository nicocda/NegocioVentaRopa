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
import negocio.ControladorTransaccion;
import util.JsonResponses;
import util.JsonUtil;
import util.Tipos;

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
		    response.getWriter().write(JsonUtil.toJson(ControladorABM.buscarTodosClientes()));
		}
		else if (action.equals("saldarDeuda"))
		{
			float monto = Float.parseFloat(request.getParameter("monto"));
			int idCliente = Tipos.esEntero(request.getParameter("idCliente")) ? Integer.parseInt(request.getParameter("idCliente")) : -1;
			Cliente clie = null;
			try
			{
				clie = ControladorABM.buscarCliente(idCliente);
				float deuda = clie.getDeudaTotal();
				clie.setDeudaTotal(deuda - monto);
			}
			catch (RespuestaServidor e)
			{
				e.printStackTrace();
			}
			ArrayList<Venta> ventasDeCliente = clie.getVentasArrayList();
			for(Venta v : ventasDeCliente)
			{
				if(v.getFormaPago() == 2)
				{
					if(monto > 0)
					{
						if(v.getDeudaPendiente() > monto)
						{
							v.setDeudaPendiente(v.getDeudaPendiente() - monto) ;
						}
						else
						{
							v.setDeudaPendiente(0);
							v.setPagada(true);
						}
					}
				}
			}
			ControladorABM.actualizarDeuda(clie);
		}
		
	}

}

