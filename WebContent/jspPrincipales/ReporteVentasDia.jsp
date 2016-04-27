<%@page import="entidades.Prestamo"%>
<%@page import="entidades.Venta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocio.ControladorTransaccion"%>
<%@ page%>
<H4>Reporte de Ventas</H4>
<% ArrayList<Venta> ventas = ControladorTransaccion.buscarVentasDia() ;
if(ventas!= null)
{
	for(Venta vta : ventas)
	{%>
		<table class="CSSTableGenerator" id="tablaClientes">
			<tr>
				<td>idVenta</td>
				<td>Tipo de Venta</td>
				<td>Comprador</td>
				<td>Precio </td>
			</tr>
			<tr>
				<td><%=vta.getId()%></td>
				
				<td><%=vta.getCliente().getNombreApellido() %></td>
				<td><%=vta.getImporte()%>
			</tr>
		</table>		
	<%}
}%> 