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
<script type="text/javascript" src="jspPrincipales/ReporteVentas/JS/ReporteVentas.js"></script>



<div id="divError"></div>
<div id="divPrincipal">

	<div class="row">
		<div class="col-lg-9">
		    <h1 class="page-header">Reportes</h1>
		</div>
	</div>
	
	<div class="filtros">
		<label style="color: #6F6F6F">Reporte Ventas</label>
		<div style="padding: 20px">
			<span>
			Cliente:
			<select id="comboClientes" style="width: 200px">
		    	<option value="0" selected>TODOS</option>
			</select>
			</span>
			<span>
			Tipo de Pago:
			<select id="cbTipoPago" style="width: 200px">
				<option value="">TODOS</option>
				<%
				for(formaPago e : formaPago.values())
				{%>
					<option value ="<%=e.ordinal()%>"><%=e.toString() %></option>
				<%}%>
			</select>
			</span>
			<span id="divTarjeta" style="width:400px;" hidden="hidden">
			Tipo de Tarjeta:
			<select id="comboTipoTarjetas" style="width: 200px">
		    	<option value="0" selected>Seleccionar Tarjeta</option>
			</select>
			</span>
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
				<option value="3">�ltima Semana</option>
				<option value="4">�ltimas 2 Semanas</option>
				<option value="5">�ltimo Mes</option>
				<option value="6">�ltimos 2 Meses</option>
				<option value="7">�ltimo A�o</option>
				<option value="8">�ltimos 2 A�os</option>
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
	
	<table id="tablaVenta" class="display" style="width: 100%">
		<thead>
			<tr>
				<th width="5%">Id</th>
				<th width="15%">Fecha</th>
				<th width="30%">Comprador</th>
				<th width="10%">Importe</th>
				<th width="20%">Forma Pago</th>
				<th width="10%">Tipo Tarjeta</th>
				<th width="15%"></th>
			</tr>
		</thead>
	</table>

	
</div>

<div id="divDetalleVenta" hidden="hidden">
	<jsp:include page="DetalleVenta.jsp"></jsp:include>
</div>

<div id="divDevolucion" hidden="hidden">
	<jsp:include page="Devolucion.jsp"></jsp:include>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>	