package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.JsonResponses;
import util.Tipos;
import util.JsonUtil;
import entidades.Cliente;
import entidades.Producto;
import entidades.Producto.estado;
import entidades.Tarjeta;
import entidades.Usuario;
import entidades.Venta;
import excepciones.RespuestaServidor;
import negocio.ControladorABM;
import negocio.ControladorTransaccion;

@WebServlet("/Ventas")
public class Ventas extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public Ventas() 
    {
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
				vta.setPagada(true);
			}
			if(formaPago.equals("2"))
			{
				vta.setPagada(false);
				
				float paga = Float.parseFloat(request.getParameter("paga"));
				vta.setDeudaPendiente(vta.getImporte()-paga);
				cli.setDeudaTotal(vta.getImporte()-paga);
				//TODO ANOTAR DEUDA Y GENERAR PAGO EN CASO QUE PAGUE ALGO
			}
			if(formaPago.equals("1"))
			{
				vta.setPagada(true);
			}
			Usuario usu = (Usuario) session.getAttribute("usuario");
			vta.setSucursal(usu.getSucursal());
			vta.setCliente(cli);
			vta.setFechaVenta(new Timestamp(System.currentTimeMillis()));
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
			// Primero intento buscar y validar el producto
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
			
			// Si el producto ingresado no es correcto, muestro el mensaje
			if (!sr.getStatus())
			{
				response.setContentType("json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(JsonResponses.devolverMensaje(sr, ""));
			}
			// Sino lo agrego a la lista de la venta, y actualizo el importe.
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
			    String json = JsonResponses.devolverMensaje(sr, "");
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
			String formaPago = request.getParameter("formaPago");
			
			int idCliente = Tipos.esEntero(request.getParameter("idCliente")) ? Integer.parseInt(request.getParameter("idCliente")) : -1; 
			
			RespuestaServidor sr = new RespuestaServidor();
			
			Cliente cliente;
			try
			{
				cliente=ControladorABM.buscarCliente(idCliente);
			}
			catch(RespuestaServidor res)
			{
				cliente = null;
			}
			
			if(formaPago == "3")
			{
				Tarjeta tarjeta = new Tarjeta();
				
				tarjeta.setNroTarjeta(Tipos.esEntero(request.getParameter("nroTarjetaTrj")) ? Integer.parseInt(request.getParameter("nroTarjetaTrj")) : -1);
				tarjeta.setCliente(cliente);
				tarjeta.setCuotas(Tipos.esEntero(request.getParameter("txtCuotasTrj")) ? Integer.parseInt(request.getParameter("txtCuotasTrj")) : -1);
				tarjeta.setNroCupon(Tipos.esEntero(request.getParameter("txtCuponTrj")) ? Integer.parseInt(request.getParameter("txtCuponTrj")) : -1);
				int tipoTarjeta = Tipos.esEntero(request.getParameter("cbTipoTarjetaTrj")) ? Integer.parseInt(request.getParameter("cbTipoTarjetaTrj")) : -1;

				//BuscarTipoTarjeta y Agregarselo a la Tarjeta				
				tarjeta.setTipoTarjeta(ControladorABM.buscartipoTarjeta(tipoTarjeta));
				vta.setTarjeta(tarjeta);
			}
			
			// Le seteo el cliente a la venta.
			vta.setCliente(cliente);
			
			// Le seteo fecha actual a la venta.
			vta.setFechaVenta(new Timestamp(System.currentTimeMillis()));
			vta.setFormaPago(Integer.parseInt(formaPago));
			
			try
			{
				ControladorTransaccion.registrarVenta(vta);
			
			}
			catch(RespuestaServidor e)
			{
				sr = e;
			}	
			
			session.setAttribute("venta", new Venta());
			
			vta = (Venta) session.getAttribute("venta");
			String msg = "{\"tot\":\""+vta.getImporte()+"\"}";
			
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(msg);
		}
		else if(action.equals("borrarProducto"))
		{
			String idProducto = request.getParameter("idProducto");
			Producto pr = ControladorTransaccion.buscarProducto(idProducto);
			Venta ventaActual = (Venta) session.getAttribute("venta");
			float importeActual = ventaActual.getImporte();
			ArrayList<Producto> productosEnVenta = ventaActual.getProductosArrayList();
			ventaActual.setProductos(removeProducto(productosEnVenta, pr));
			if(pr.getEstado()== 0)
				ventaActual.setImporte(importeActual + pr.getPrecio().getPrecio());
			else
				ventaActual.setImporte(importeActual- pr.getPrecio().getPrecio());
			session.setAttribute("venta", ventaActual);
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(JsonUtil.toJson(ventaActual.getProductosArrayList()));
		}
		else if(action.equals("validaPass"))
		{
			String pass = request.getParameter("pswSeg");
			boolean bandera = ControladorTransaccion.validarContraseñaAdministracion(pass);
			String msg = "{\"estado\":\""+bandera+"\"}";
			response.setContentType("json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(msg);
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
	
	private ArrayList<Producto> removeProducto(ArrayList<Producto> productos, Producto p)
	{
		for(Producto pr : productos )
		{
			if(pr.getId().equals(p.getId()))
			{
				productos.remove(pr);
				return productos;
			}
			
		}
		return productos;
	}
}