<%@page import="entidades.Usuario"%>
<%@page import="entidades.Venta"%>
<%@page import="java.util.ArrayList"%>
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

<H4 id="h4Cliente">Clientes</H4>
<div id="divError"></div>
<div id="accordion">
	<h3 id="mostrar">Lista de Clientes</h3>
	<div style="max-height: 320px">
		<table class="CSSTableGenerator" id="tablaClientes">
			<tr>
				<td width="10%">ID</td>
				<td width="30%">Nombre y Apellido</td>
				<td width="20%">Direcci�n</td>
				<td width="20%">Tel�fono</td>
				<td width="10%">Deuda</td>
				<td width="10%"></td>
			</tr>
			<%
			ArrayList<Cliente> clientes = ControladorABM.buscarTodosClientes();
			for(Cliente cl : clientes)
			{%>
			<tr>
				<td id="idCliente" align="center" class="idTabla"><%=cl.getId() %></td>
				<td class="nyaTabla"><%=cl.getNombre() %></td>
				<td class="direTabla"><%=cl.getDireccion() %></td>
				<td class="telTabla"><%=cl.getTelefono() %></td>
				<td align="center"><input type="button" class="botones btnDeuda" value="Ver Deuda"></td>
				<td align="center"><input type="button" class="botones btnEditar" value="Editar"></td>
			</tr>
			<%} %>
		</table>
	</div>
	<h3 id="nuevoEditar">Nuevo Cliente:</h3>
	<div>
		<button id="btnRestaurar">x</button>
		<input type="text" class="textInputs" id="txtID" placeholder="ID" disabled="disabled" style="display: none">		
		<input type="text" class="textInputs" id="txtNombreYApellido" placeholder="Nombre y Apellido">
		<input type="text" class="textInputs" id="txtDireccion" placeholder="Direcci�n">
		<input type="text" class="textInputs" id="txtTelefono" placeholder="Tel�fono">
		<input type="submit" id="btnAceptar" class="botones" value="Guardar Cliente">
	</div>

</div>

<div id="divDeudas"  hidden="hidden">
	<table id="tablaVentasMorosas" class="CSSTableGenerator"></table>
	<div style="text-align: right">
		<br>
		<button class="botones" id="btnVolverDeDeudas">Volver</button>
	</div>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>


	