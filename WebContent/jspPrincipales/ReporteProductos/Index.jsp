<%@page import="negocio.ControladorABM"%>
<%@page import="entidades.Prestamo"%>
<%@page import="entidades.Venta"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.Producto"%>
<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocio.ControladorTransaccion"%>
<%@page import="entidades.Venta.formaPago"%>

<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() >= 1) 
{%>
<script type="text/javascript" src="jspPrincipales/ReporteProductos/JS/ReporteProductos.js"></script>



<div id="divError"></div>
<div id="divPrincipal">

	<div class="row">
		<div class="col-lg-9">
		    <h1 class="page-header">Reportes</h1>
		</div>
	</div>

	<div class="filtros">
		<label style="color: #6F6F6F">Fechas</label>
		<div style="padding: 20px">
			Rango:	
			<select id="cbFecha" style="width: 200px">
				<option value="" disabled selected>Seleccione rango</option>
				<option value="1">Hoy</option>
				<option value="2">Desde Ayer</option>
				<option value="3">Última Semana</option>
				<option value="4">Últimas 2 Semanas</option>
				<option value="5">Último Mes</option>
				<option value="6">Últimos 2 Meses</option>
				<option value="7">Último Año</option>
				<option value="8">Últimos 2 Años</option>
				<option value="9">Siempre</option>
			</select>
			Desde:<input id="fechaMinima" readonly="true">
			<input type="hidden" id="hddFechaMinima">
			Hasta:<input id="fechaMaxima" readonly="true">
			<input type="hidden" id="hddFechaMaxima">
			<button id="btnBuscar" class="botones">Buscar</button>
		</div>
	</div>
	<br>
	
	<table id="tablaProductos" class="display" style="width: 100%">
		<thead>
			<tr>
				<th width="10%">Id</th>
				<th width="30%">Detalle del Producto</th>
				<th width="10%">Fecha de Venta</th>
				<th width="25%">Comprador</th>
				<th width="15%">Tipo de Pago</th>
				<th width="10%">Importe</th>
			</tr>
		</thead>
	</table>

	
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>	