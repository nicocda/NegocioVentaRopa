<%@page import="entidades.Usuario"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() == 1) 
{%>
<script type="text/javascript" src="./scripts/custom/AutoComplete.js"></script>	
<script type="text/javascript" src="../scripts/custom/AutoComplete.js"></script>	
<script type="text/javascript" src="./scripts/custom/popup.js"></script>	
<script type="text/javascript" src="../scripts/custom/popup.js"></script>
<H4>Venta</H4>
<form>

	<input type="text" id="txtClientes" placeholder="Cliente"/>

	<table class="CSSTableGenerator">
		<tr>
			<td width="10%">Código</td>
			<td width="50%">Descripción</td>
			<td width="30%">Precio</td>
		</tr>
		<tr>
			<td>1</td>
			<td>Descripción</td>
			<td align="right">200</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Descripción</td>
			<td align="right">300</td>
		</tr>
		<tr>
			<td style="background-color: #C0C0C0;"><input id="agregar" class="botones" type="button" value="Agregar +"></td>
			<td style="background-color: #C0C0C0;"></td>
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
			<th>Hola</th>
		</tr>
		<tr>
			<td><input type="text"></td>
		</tr>
		<tr>
			<td><input class="botones" type="button" value="Ok"> </td>
		</tr>
	</table>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>
 	