<script type="text/javascript" src="../scripts/custom/ABMClientes.js"></script>	
<script type="text/javascript" src="./scripts/custom/ABMClientes.js"></script>	
<script type="text/javascript" src="../scripts/custom/popup.js"></script>
<script type="text/javascript" src="./scripts/custom/popup.js"></script>

<div id="divError"></div>
<div id="accordion">
	<h3>Lista de Clientes</h3>
	<div style="max-height: 380px">
		<table>
			<tr>
				<th>ID</th>
				<th>Nombre y Apellido</th>
				<th>Dirección</th>
				<th>Teléfono</th>
				<th></th>
			</tr>
			<%for(int i = 0; i <=10; i++)
			{%>
			<tr>
				<td>1</td>
				<td>Juan Grasso</td>
				<td>San Lorenzo 1759</td>
				<td>3444-532918</td>
				<td><input type="button" class="botones" value="Editar"></td>
			</tr>
			<%} %>
		</table>
	</div>
	<h3>Nuevo/Editar Cliente:</h3>
	<div>
		<input type="text" id="txtID" placeholder="ID" disabled="disabled" hidden="true">
		<input type="text" id="txtNombreYApellido" placeholder="Nombre y Apellido">
		<input type="text" id="txtDireccion" placeholder="Dirección">
		<input type="text" id="txtTelefono" placeholder="Teléfono">
		<input type="submit" id="btnAceptar" class="botones" value="Guardar Cliente">
	</div>
</div>