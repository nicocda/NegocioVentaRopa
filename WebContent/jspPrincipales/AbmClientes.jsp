<%@ page import="entidades.Cliente"
import="java.util.ArrayList"
import="negocio.ControladorABM" %>
<script type="text/javascript" src="../scripts/custom/ABMClientes.js"></script>	
<script type="text/javascript" src="./scripts/custom/ABMClientes.js"></script>	
<script type="text/javascript" src="../scripts/custom/popup.js"></script>
<script type="text/javascript" src="./scripts/custom/popup.js"></script>

<div id="divError"></div>
<div id="accordion">
	<h3>Lista de Clientes</h3>
	<div style="max-height: 320px">
		<table class="CSSTableGenerator">
			<tr>
				<td>ID</td>
				<td>Nombre y Apellido</td>
				<td>Dirección</td>
				<td>Teléfono</td>
				<td></td>
			</tr>
			<%
			ArrayList<Cliente> clientes = ControladorABM.buscarClientes();
			for(Cliente cl : clientes)
			{%>
			<tr>
				<td><%=cl.getId() %></td>
				<td><%=cl.getNombreApellido() %></td>
				<td><%=cl.getDireccion() %></td>
				<td><%=cl.getTelefono() %></td>
				<td><input type="button" class="botones" value="Editar"></td>
			</tr>
			<%} %>
		</table>
	</div>
	<h3>Nuevo Cliente:</h3>
	<div>
		<input type="text" id="txtID" placeholder="ID" disabled="disabled" >
		<input type="text" id="txtNombreYApellido" placeholder="Nombre y Apellido">
		<input type="text" id="txtDireccion" placeholder="Dirección">
		<input type="text" id="txtTelefono" placeholder="Teléfono">
		<input type="submit" id="btnAceptar" class="botones" value="Guardar Cliente">
	</div>
</div>