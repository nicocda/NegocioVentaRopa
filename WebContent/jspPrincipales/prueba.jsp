<div id="accordion">
	<h3>Lista de Productos</h3>
	<div style="max-height: 380px">
		<table>
			<tr>
				<th>ID</th>
				<th>Descripción</th>
				<th>Estado</th>
				<th>Precio</th>
				<th></th>
			</tr>
			<%for(int i = 0; i <=10; i++)
			{%>
			<tr>
				<td>0303</td>
				<td>Teléfono</td>
				<td>En stock</td>
				<td>456</td>
				<td><input type="button" class="botones" value="Editar"></td>
			</tr>
			<%} %>
		</table>
	</div>
	<h3>Nuevo/Editar Producto:</h3>
	<div>
		<form action="TestCliente" method="post">
			<select>
				<option>Seleccione Tipo</option>
				<option>Tipo1</option>
				<option>Tipo2</option>
				<option>Tipo3</option>
			</select>
			<select>
				<option>Seleccione SubTipo</option>
				<option>SubTipo1</option>
				<option>SubTipo2</option>
				<option>SubTipo3</option>
			</select>
			<input type="text" name="txtID" placeholder="ID" disabled="disabled">
			<input type="text" name="txtDescripcion" placeholder="Descripción">
			<input type="text" name="txtPrecio" placeholder="Precio">
			<input type="submit" name="btnAceptar" class="botones" value="Agregar Producto">
		</form>
	</div>
</div>