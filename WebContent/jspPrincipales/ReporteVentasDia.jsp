<%@page import="negocio.ControladorABM"%>
<%@page import="entidades.Prestamo"%>
<%@page import="entidades.Venta"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocio.ControladorTransaccion"%>
<%@page import="entidades.Venta.formaPago"%>

<H4>Reporte de Ventas</H4>
	<div>
	<h3>Filtro Por Clientes</h3>
		<select>
			<%for(Cliente c: ControladorABM.buscarClientes()){ %>
			<option><%=c.getNombreApellido() %></option>
			<%} %>
		</select>
	</div>
	
	<% ArrayList<Venta> ventas = ControladorTransaccion.buscarVentasDia();
		if(ventas!= null)
		{%>
		
	<div>
	<h3>Filtro Por Fechas</h3>
		<select>
			<option>Ultimo Año</option>
			<option>Ultimo Mes</option>
			<option>Del Día</option>
		</select>
		Fecha mínima:<input type="date" class="botones btnEditar" value="Detalle Venta">
		Fecha máxima:<input type="date" class="botones btnEditar" value="Detalle Venta">
	</div>
	
	<div>
	<h3>Filtro Por Tipo de Pago</h3>
		<select>
		<%
		for(formaPago e : formaPago.values())
			{%>
			<option><%=e.toString() %></option>
				<%} %>
		</select>
	</div>
	
	<h3>Ventas Realizadas</h3>
	<div style="max-width: 80%">
		<table class="CSSTableGenerator">
			<tr>
				<td width="20%">Comprador </td>
				<td width="20%">Fecha de Realización</td>
				<td width="20%">Detalle</td>
			</tr>
			<%
			for(Venta vta : ventas)
			{
			%>
			<tr>
				<td><%=vta.getCliente().getNombreApellido() %></td>
				<td><%=vta.getFechaVenta() %></td>
				<td><input type="button" class="botones btnEditar" value="Detalle Venta"></td> 			
			</tr>
			<%}
		}%>
		</table>
	</div>		

