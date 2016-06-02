<%@page import="entidades.Usuario"%>
<%@page import="entidades.Venta"%>
<%@page import="java.util.ArrayList"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() == 1) 
{%>
<%@ page import="entidades.Cliente"
import="java.util.ArrayList"
import="negocio.ControladorABM" %>
<script type="text/javascript" src="jspPrincipales/ABMClientes/JS/ABMClientes.js"></script>	
<script type="text/javascript" src="scripts/custom/popup.js"></script>

<div id="divError"></div>
<div id="divPrincipal">
	<div class="row">
		<div class="col-lg-9">
		    <h1 class="page-header">Clientes</h1>
		</div>
		<div class="col-lg-3">
			<button id="btnMostrarCreate" class="btn btn-primary page-header pull-right" >Nuevo Cliente</button>
		</div>
	</div>
	<table id="tablaClientes" class="display">
		<thead>
			<tr>
				<th width="10%">ID</td>
				<th width="15%">Nombre</td>
				<th width="15%">Apellido</td>
				<th width="20%">Dirección</td>
				<th width="20%">Teléfono</td>
				<th width="10%"></td>
				<th width="10%"></td>
			</tr>
		</thead>
		
	</table>
</div>

<div id="divCrearCliente" hidden="hidden">
	<jsp:include page="DetalleCliente.jsp"></jsp:include>
</div>

<div id="divDeudas"  hidden="hidden">
	<jsp:include page="DeudaCliente.jsp"></jsp:include>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>


	