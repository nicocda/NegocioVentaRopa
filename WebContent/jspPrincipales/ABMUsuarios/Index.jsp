<%@page import="entidades.Usuario.tipoUsuario"
import="entidades.Usuario"
import="java.util.ArrayList"
import="negocio.ControladorABM" %>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() == 1) 
{%>
<script type="text/javascript" src="jspPrincipales/ABMUsuarios/JS/ABMUsuarios.js"></script>	
<script type="text/javascript" src="scripts/custom/popup.js"></script>

<div id="divError"></div>
<div id="divPrincipal">
	<div class="row">
		<div class="col-lg-9">
			<h1 class="page-header">Usuarios</h1>
		</div>
		<div class="col-lg-3">
			<button id="btnMostrarCreate" class="btn btn-primary page-header pull-right" >Nuevo Usuario</button>
		</div>
	</div>
	<table id="tablaUsuarios" class="display">
		<thead>
			<tr>
				<th width="10%">Usuario</th>
				<th width="40%">Nombre y Apellido</th>
				<th width="20%">Email</th>
				<th width="20%">Tipo</th>
				<th width="10%"></th>
			</tr>
		</thead>
	</table>
</div>

<div id="divCrearUsuario" hidden="hidden">
	<jsp:include page="DetalleUsuario.jsp"></jsp:include>
</div>

<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>


	