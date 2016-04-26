<%@page import="entidades.Usuario"
		import="entidades.Producto"
		import="java.util.ArrayList"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() == 1) 
{%>
<script type="text/javascript" src="./scripts/custom/AutoComplete.js"></script>	
<script type="text/javascript" src="../scripts/custom/AutoComplete.js"></script>	
<script type="text/javascript" src="./scripts/custom/Ventas.js"></script>	
<script type="text/javascript" src="../scripts/custom/Ventas.js"></script>
<H4>Venta</H4>
<form action="Ventas" method="POST">

	<input type="text" id="txtClientes" placeholder="Cliente"/>

	<table class="CSSTableGenerator" id="tablaProductos">
		<tr>
			<td width="10%">Código</td>
			<td width="50%">Descripción</td>
			<td width="30%">Precio</td>
		</tr>
		<tr id="trTablaProducto"></tr>
		<tr>
			<td style="background-color: #C0C0C0;"><input id="agregar" class="botones" type="button" value="Agregar +"></td>
			<td style="background-color: #C0C0C0;"><input id="txtID" type="text" placeholder="Codigo Producto"></td>
			<td align="right" style="background-color: #C0C0C0;"><b>Total:</b></td>
		<tr>
	</table>

	<div style="text-align: center">
		<br><br>
		<input type="radio" name="radio" id="radio1" class="css-checkbox"/>
		<label class="css-label radGroup1" for="radio1">Efectivo</label>
		<input type="radio" name="radio" id="radio2" class="css-checkbox"/>
		<label class="css-label radGroup1" for="radio2">Tarjeta</label>
		<input type="radio" name="radio" id="radio3" class="css-checkbox"/>
		<label class="css-label radGroup1" for="radio3">Cuenta Corriente</label><br><br>
		<input class="botones" type="button" value="Realizar Venta">
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
 	