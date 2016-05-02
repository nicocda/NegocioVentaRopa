<%@page import="entidades.Usuario"
		import="entidades.Producto"
		import="java.util.ArrayList"
		import="entidades.Venta"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() == 1) 
{%>

<script type="text/javascript" src="./scripts/custom/Ventas.js"></script>	
<script type="text/javascript" src="../scripts/custom/Ventas.js"></script>
<H4>Venta</H4> <p align="right"><%if(request.getParameter("time") != null)%><%=request.getParameter("time") %></p>
<div id="divError"></div>
<form action="Ventas" method="POST">

	<select id="comboClientes" style="width: 100%" class="js-example-basic-single">
	    <option value="" disabled selected>Seleccione un cliente</option>
	</select>

	<table class="CSSTableGenerator" id="tablaProductos">
		<tr>
			<td width="10%">Código</td>
			<td width="50%">Descripción</td>
			<td width="30%">Precio</td>
		</tr>
			<%Venta venta = (Venta) session.getAttribute("venta");
				if(venta != null)
				{
					for(Producto p: venta.getProductos())
							{ %>
								<tr id="trTablaProducto">
								<td><%= p.getId() %></td>
								<td><%= p.getDescripcion()%></td>
								<td><%= p.getPrecio().getPrecio()%></td>
								</tr>
							<% }
				}
				%>
		<tr>
			<td style="background-color: #C0C0C0;"><input id="agregar" class="botones" type="button" value="Agregar +"></td>
			<td style="background-color: #C0C0C0;"><input id="txtID" type="text" placeholder="Codigo Producto"></td>
			<td align="right" style="background-color: #C0C0C0;"><b id="total">Total: <%if(venta != null){%><%=venta.getImporte() %><%} else { %><label>0</label><%} %></b></td>
		<tr>
	</table>

	<div style="text-align: center">
		<br><br>
		<input type="radio" name="radio" id="radio1" class="css-checkbox"/>
		<label class="css-label radGroup1" for="radio1">Efectivo</label>
		<input type="radio" name="radio" id="radio2" class="css-checkbox"/>
		<label class="css-label radGroup1" for="radio2">Cuenta Corriente</label>
		<input type="radio" name="radio" id="radio3" class="css-checkbox"/>
		<label class="css-label radGroup1" for="radio3">Tarjeta</label>
		<br><br>
		<input class="botones" type="button" id="realizarVenta" value="Realizar Venta">
	</div>

</form>

<!-- HTML para el popup -->
<div id="dialog" title="Agregar Producto" style="display:none;">
	<table>
		<tr>
			<th>Ingrese codigo de producto</th>
		</tr>
		<tr>
			<td><input type="text" name="idProducto"></td>
		</tr>
		<tr>
			<td><input class="botones" type="button" id="addProducto" value="Ok"> </td>
		</tr>
	</table>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>