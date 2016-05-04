<%@page import="datos.CatalogoVentas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Venta"%>
<%@page import="entidades.Producto"%>
<%@page import="negocio.ControladorTransaccion"%>

	<link rel="stylesheet" type="text/css" href="themes/header.css">
	<link rel="stylesheet" type="text/css" href="themes/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="themes/componentes.css">
	<link rel="stylesheet" type="text/css" href="themes/select2.css">
	
	<%Venta vta = CatalogoVentas.buscarVenta(10);
	if(vta != null)
	{
	%>
	<table class="CSSTableGenerator">
			<tr>
				<td width="20%">ID</td>
				<td width="40%">Fecha Venta</td>
				<td width="20%">Comprador</td>
				<td width="10%">Precio</td>
				<td width="10%">Forma de Pago</td>
			</tr>
			<tr>
				<td><%=vta.getId() %></td>
				<td><%=vta.getFechaVenta() %></td>
				<td><%=vta.getCliente().getNombreApellido() %></td>
				<td><%=vta.getImporte() %></td>
				<td><%=vta.getFormaPago() %></td>
			</tr>
			<tr><td>Productos de la Venta</td></tr>
			<% for(Producto pr: vta.getProductos())
			{%>
			<tr>
				<td><%=pr.getDescripcion() %></td>
				<td><%=pr.getPrecio().getPrecio() %></td>
			</tr>
			<%} %>
</table>
<%
	}
	%>