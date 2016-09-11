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

import com.google.gson.Gson;

import util.JsonResponses;
import entidades.Cliente;
import entidades.Producto;
import entidades.Producto.estado;
import entidades.Tarjeta;
import entidades.TipoTarjeta;
import entidades.Usuario;
import entidades.Venta;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import negocio.ControladorTransaccion;

@WebServlet("/Ventas")
public class Ventas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Ventas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		Venta vta = (Venta) session.getAttribute("venta");
		String action = request.getParameter("action");	
		
		if (action == null)
		{
			request.setAttribute("url","../jspPrincipales/Ventas/Index.jsp");
			request.getRequestDispatcher("jspCompartido/newMainLayout.jsp").forward(request, response);
		}
		else if(action.equals("realizarVenta"))
		{

			String formaPago = request.getParameter("formaPago");
			int idCliente = request.getParameter("idCliente") != ""?Integer.parseInt(request.getParameter("idCliente")) : -1; 
			RespuestaServidor sr = new RespuestaServidor();
			
			Cliente cli;
			try
			{
				cli=ControladorABM.buscarCliente(idCliente);
			}
			catch(RespuestaServidor res)
			{
				cli = null;
			}
			if(formaPago.equals("3"))
			{
				Tarjeta trj = new Tarjeta();
				int idTarjeta = request.getParameter("nroTarjetaTrj") != ""? Integer.parseInt(request.getParameter("nroTarjetaTrj")): -1;
				trj.setNroTarjeta(idTarjeta);
				trj.setCliente(cli);
				trj.setCuotas(request.getParameter("txtCuotasTrj") != ""? Integer.parseInt(request.getParameter("txtCuotasTrj")): -1);
				trj.setNroCupon(request.getParameter("txtCuponTrj") != ""? Integer.parseInt(request.getParameter("txtCuponTrj")): -1);
				int tipoTarjeta;
				try
				{
				 tipoTarjeta = Integer.parseInt(request.getParameter("cbTipoTarjetaTrj"));	
				}
				catch(NumberFormatException nfe)
				{
				 tipoTarjeta = -1;
				}
				trj.setTipoTarjeta(ControladorABM.buscartipoTarjeta(tipoTarjeta));
				vta.setTarjeta(trj);
			}
			Usuario usu = (Usuario) session.getAttribute("usuario");
			vta.setSucursal(usu.getSucursal());
			vta.setCliente(cli);
			Calendar today = Calendar.getInstance();
			today.set(Calendar.HOUR_OF_DAY, 0);
			vta.setFechaVenta(today.getTime());
			vta.setFormaPago(Integer.parseInt(formaPago));
			//Devuelvo los productos que sean devolucion
			for(Producto p : vta.getProductos())
			{
				if(p.getEstado() == Producto.estado.VENDIDO.ordinal())
				{
					ControladorTransaccion.devolverProducto(p);
				}
			}
			try
			{
				ControladorTransaccion.registrarVenta(vta);
			}
			catch(RespuestaServidor e)
			{
				sr = e;
			}	
			session.setAttribute("venta", new Venta());
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonResponses.devolverMensaje(sr, "La venta se registró con éxito"));
		}
		else if (action.equals("agregarProducto"))
		{	
			//Primero intento buscar y validar el producto
			String id = request.getParameter("id");
			Producto pro;
			try
			{
				pro= ControladorTransaccion.buscarProducto(id);
			}
			catch(Exception sr)
			{
				pro = null;
			}
			
			RespuestaServidor sr = new RespuestaServidor();
			sr = validarProducto(pro, vta);
			
			//Si el producto ingresado no es correcto, muestro el mensaje
			if (!sr.getStatus())
			{
				response.setContentType("json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(JsonResponses.devolverMensaje(sr, ""));
			}
			//Sino lo agrego a la lista de la venta, y actualizo el importe.
			else	
			{
				vta.addProducto(pro);
				
				float importe= 0;
				for(Producto p : vta.getProductos())
				{
					if(p.getEstado() == Producto.estado.STOCK.ordinal())
						importe= importe + p.getPrecio().getPrecio();
					else if(p.getEstado() == Producto.estado.VENDIDO.ordinal())
						importe = importe - p.getPrecio().getPrecio();
				}
				vta.setImporte(importe);
				session.setAttribute("venta", vta);
				
				response.setContentType("json");
			    response.setCharacterEncoding("UTF-8");
			    //SobreCarga
			    String json = JsonResponses.devolverMensaje(sr, "",importe, pro.getEstado());
			    response.getWriter().write(json);
			}
		}
		else if (action.equals("recargarTabla"))
		{
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
				//Gson g = new Gson();
			    //response.getWriter().write(g.toJson(vta.getProductosArrayList()));
		    String toJson = JsonResponses.arrayTodosProductosVenta(vta);
		    System.out.println(toJson);
			   response.getWriter().write(toJson);
		}
		else if(action.equals("actualizarTotal"))
		{
			String msg = "{\"tot\":\""+vta.getImporte()+"\"}";
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(msg);
		}
		else if(action.equals("borrarProducto"))
		{
			/*String idProducto = request.getParameter("idProducto");
			Producto pr = ControladorTransaccion.buscarProducto(idProducto);
			Venta venta = (Venta) session.getAttribute("venta");
			ArrayList<Producto> prVta = venta.getProductosArrayList();
			prVta.remove(pr);
			venta.setProductos(prVta);
			session.setAttribute("venta", venta);*/
		}
	}
	
	private RespuestaServidor validarProducto(Producto pro, Venta venta)
	{
		RespuestaServidor sr = new RespuestaServidor();
		
		if(pro == null)
			sr.addError("El producto ingresado no existe.");
		
		if (pro != null)
		{
			
			
			if(pro.getEstado() == estado.SEÑADO.ordinal())
				sr.addError("El producto ingresado está señado.");
		
			boolean enLista = false;
			for(Producto p : venta.getProductos())
			{
				if(p.getId().equals(pro.getId()))
					enLista = true;
			}
			if(enLista)
				sr.addError("El producto seleccionado ya fue ingresado en esta venta");
		}
		return sr;
	}
}
