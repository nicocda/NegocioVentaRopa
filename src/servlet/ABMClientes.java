package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosTODOparsear.CatalogoClientes;
import entidades.Cliente;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import util.JsonResponses;
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
			agregarCliente(request, response);
		}
		else if (action.equals("recargarTabla"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    try
		    {
				response.getWriter().write(JsonResponses.listarDataTable(CatalogoClientes.obtenerClientes()));
			}
		    catch (RespuestaServidor e)
		    {
				e.printStackTrace();
			}
		}
		else if (action.equals("saldarDeuda"))
		{
			/*float monto = Float.parseFloat(request.getParameter("monto"));
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
			ControladorABM.actualizarDeuda(clie);*/
		}
		
	}
	
	private void agregarCliente(HttpServletRequest request, HttpServletResponse response)
	{
		String idString = request.getParameter("id");
		
		int id = Tipos.esEntero(idString) ? Integer.parseInt(idString) : 0;
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("telefono");
		RespuestaServidor sr = new RespuestaServidor();
		try
		{
			CatalogoClientes.guardarCliente(new Cliente(id, nombre, apellido, direccion, telefono, true));
		}
		catch(RespuestaServidor res)
		{
			sr = res;
		}
		
		sr.setMensajeExito("Se guardo correctamente el cliente");
		
		response.setContentType("json");
	    response.setCharacterEncoding("UTF-8");
	    
	    try 
	    {
			response.getWriter().write(sr.toJson());
		} 
	    catch (IOException e)
	    {
			e.printStackTrace();
		}
	}

}

