<%@page import="negocio.ControladorABM"%>
<%@page import="entidades.Prestamo"%>
<%@page import="entidades.Venta"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocio.ControladorTransaccion"%>
<%@page import="entidades.Venta.formaPago"%>

<script type="text/javascript" src="./scripts/custom/ReporteVenta.js"></script>	
<script type="text/javascript" src="../scripts/custom/ReporteVenta.js"></script>

<H4>Reporte de Ventas</H4>
	<div>
	<h3>Filtro Por Clientes</h3>
		<select  class="styled-select">
			<%for(Cliente c: ControladorABM.buscarClientes()){ %>
			<option><%=c.getNombreApellido() %></option>
			<%} %>
		</select>
	</div>
	
	
		
	<div>
	<h3>Filtro Por Fechas</h3>
	<div id="datepicker"></div>
		<select id="cbFecha"  class="styled-select">
			<option value="1">Ultimo Año</option>
			<option value="2">Ultimo Mes</option>
			<option value="3">Ultima Semana</option>
			<option value="4">Del Día</option>
		</select><br>
		Fecha mínima:<input style="width: 20%;" type="text" id="fechaMinima" >
		Fecha máxima:<input style="width: 20%;" type="text" id="fechaMaxima" >
		
	</div>
	
	<div>
	<h3>Filtro Por Tipo de Pago</h3>
		<select  class="styled-select">
		<%
		for(formaPago e : formaPago.values())
			{%>
			<option><%=e.toString() %></option>
				<%} %>
		</select>
	</div>
	
	<% ArrayList<Venta> ventas = ControladorTransaccion.buscarVentasDia("04/28/2016", "05/01/2016");
		if(ventas!= null)
		{%>
	<h3>Ventas Realizadas</h3>
	<div style="max-width: 80%">
		<table class="CSSTableGenerator">
			<tr>
				<td width="20%">Comprador </td>
				<td width="30%">Fecha de Realización (año-mes-día)</td>
				<td width="20%">Detalle</td>
			</tr>
			<%
			for(Venta vta : ventas)
			{
			%>
			<tr>
				<td><%=vta.getCliente().getNombreApellido() %></td>
				<td><%=vta.getFechaVenta() %></td>
				<td><input type="button" class="botones btnEditar" id="verDetalle" value="Detalle Venta"></td> 			
			</tr>
			<%}
		}%>
		</table>
	</div>		

