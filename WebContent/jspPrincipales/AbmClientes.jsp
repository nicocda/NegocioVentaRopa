<%@page import="entidades.Usuario"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() == 1) 
{%>
<%@ page import="entidades.Cliente"
import="java.util.ArrayList"
import="negocio.ControladorABM" %>
<script type="text/javascript" src="../scripts/custom/ABMClientes.js"></script>	
<script type="text/javascript" src="./scripts/custom/ABMClientes.js"></script>	
<script type="text/javascript" src="../scripts/custom/popup.js"></script>
<script type="text/javascript" src="./scripts/custom/popup.js"></script>

<H4>Clientes</H4>
<div id="divError"></div>
<div id="accordion">
	<h3 id="mostrar">Lista de Clientes</h3>
	<div style="max-height: 320px">
		<table class="CSSTableGenerator" id="tablaClientes">
			<tr>
				<td width="10%">ID</td>
				<td width="40%">Nombre y Apellido</td>
				<td width="20%">Dirección</td>
				<td width="20%">Teléfono</td>
				<td width="10%"></td>
			</tr>
			<%
			ArrayList<Cliente> clientes = ControladorABM.buscarTodosClientes();
			for(Cliente cl : clientes)
			{%>
			<tr>
				<td align="center" class="idTabla"><%=cl.getId() %></td>
				<td class="nyaTabla"><%=cl.getNombreApellido() %></td>
				<td class="direTabla"><%=cl.getDireccion() %></td>
				<td class="telTabla"><%=cl.getTelefono() %></td>
				<td align="center"><input type="button" class="botones btnEditar" value="Editar"></td>
			</tr>
			<%} %>
		</table>
	</div>
	<h3 id="nuevoEditar">Nuevo Cliente:</h3>
	<div>
		<button id="btnRestaurar">x</button>
		<input type="text" id="txtID" placeholder="ID" disabled="disabled" style="display: none">		
		<input type="text" id="txtNombreYApellido" placeholder="Nombre y Apellido">
		<input type="text" id="txtDireccion" placeholder="Dirección">
		<input type="text" id="txtTelefono" placeholder="Teléfono">
		<input type="submit" id="btnAceptar" class="botones" value="Guardar Cliente">
	</div>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>


	