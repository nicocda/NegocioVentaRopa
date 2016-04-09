
<form>
	
	<label class="etiquetas">Cliente</label>
	<input type="text" >
	<label style="font-weight: bold;">Ventas</label>
	
	<table>
		<tr>
			<th>Código</th>
			<th>Descripción</th>
			<th>Precio</th>
		</tr>
		<% for (int i = 0; i<2; i++)
		{%>
		<tr>
			<td> 1234 </td>
			<td> Producto </td>
			<td> 100 </td>
		</tr>
	 	<%} %>
	 	<tr>
			<td colspan="3"> <input id="agregar" class="botones" type="button" value="Agregar +"> </td>
		</tr>
		<tfoot>
			<td></td>
			<td></td>
			<td style="background-color: #C0C0C0;">Total: 200</td>
		</tfoot>
	</table>
	

		<input type="radio" name="radio" id="radio1" class="css-checkbox"/>
		<label class="css-label radGroup1" for="radio1">Efectivo</label>
		<input type="radio" name="radio" id="radio2" class="css-checkbox"/>
		<label class="css-label radGroup1" for="radio2">Tarjeta</label>
		<input type="radio" name="radio" id="radio3" class="css-checkbox"/>
		<label class="css-label radGroup1" for="radio3">Cuenta Corriente</label><br>

	<input class="botones" type="button" value="Realizar Venta">
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
 	