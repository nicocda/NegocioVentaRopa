<%@page import="entidades.Usuario.tipoUsuario"
import="entidades.Usuario"
import="java.util.ArrayList"
import="negocio.ControladorABM" %>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() == 1) 
{%>

<script type="text/javascript" src="../scripts/custom/ABMUsuarios.js"></script>	
<script type="text/javascript" src="./scripts/custom/ABMUsuarios.js"></script>	
<script type="text/javascript" src="../scripts/custom/popup.js"></script>
<script type="text/javascript" src="./scripts/custom/popup.js"></script>

<H4>Usuarios</H4>
<div id="divError"></div>
<div id="accordion">
	<h3 id="mostrar">Lista de Usuarios</h3>
	<div style="max-height: 320px">
		<table class="CSSTableGenerator" id="tablaUsuarios">
			<tr>
				<td width="10%">Usuario</td>
				<td width="40%">Nombre y Apellido</td>
				<td width="20%">Email</td>
				<td width="20%">Tipo</td>
				<td width="10%"></td>
			</tr>
			<%
			ArrayList<Usuario> usuarios = ControladorABM.buscarTodosUsuarios();
			for(Usuario u : usuarios)
			{%>
			<tr>
				<td align="center" class="usuarioTabla"><%=u.getUsuario() %></td>
				<td class="nyaTabla"><%=u.getNombreYApellido() %></td>
				<td class="emailTabla"><%=u.getEmail() %></td>
				<td class="tipoTabla"><%=tipoUsuario.values()[u.getTipoUsuario()].name() %></td>
				<td align="center"><input type="button" class="botones btnEditar" value="Editar"></td>
			</tr>
			<%} %>
		</table>
	</div>
	<h3 id="nuevoEditar">Nuevo Usuario:</h3>
	<div>
		<button id="btnRestaurar">x</button>
		<input type="text" id="txtUsuario" placeholder="Usuario">
		<input type="password" id="txtPassword" placeholder="Contraseña">		
		<input type="text" id="txtNombreYApellido" placeholder="Nombre y Apellido">
		<input type="text" id="txtEmail" placeholder="Email">
		<select id="cbTipo">
			<%for(tipoUsuario tu: tipoUsuario.values()) 
			{%>
				<option value="<%=tu.ordinal()%>"><%=tu.name()%></option>
			<%}%>
		</select>
		<input type="submit" id="btnAceptar" class="botones" value="Guardar Usuario">
	</div>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>


	