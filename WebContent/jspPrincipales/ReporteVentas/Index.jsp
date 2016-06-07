<%@page import="negocio.ControladorABM"%>
<%@page import="entidades.Prestamo"%>
<%@page import="entidades.Venta"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocio.ControladorTransaccion"%>
<%@page import="entidades.Venta.formaPago"%>

<script type="text/javascript" src="jspPrincipales/ReporteVentas/JS/ReporteVentas.js"></script>

<div id="divError"></div>
<div id="divPrincipal">
<H4>Reporte de Ventas</H4>
<div class="filtros">
	<label style="color: #6F6F6F">Reporte Ventas</label>
	<div style="padding: 20px">
		Cliente:
		<select id="comboClientes" style="width: 200px">
	    	<option value="" selected>TODOS</option>
		</select>
		Tipo de Pago:
		<select id="cbTipoPago" style="width: 200px">
			<option value="">TODOS</option>
			<%
			for(formaPago e : formaPago.values())
			{%>
				<option value ="<%=e.ordinal()%>"><%=e.toString() %></option>
			<%}%>
		</select>
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
		Hasta:<input id="fechaMaxima" readonly="true">
		<button id="btnBuscar" class="botones">Buscar</button>
	</div>
</div>
<br>
<div id="divTabla" hidden="hidden">
<table id="tablaVentas" class="display">
		<thead>
			<tr>
				<th width="30%">ID</td>
				<th width="40%">fechaVenta</td>
				<th width="30%">Comprador</td>
				<th width="20%"></th>
			</tr>
		</thead>
	</table>
</div>
</div>

<div id="divDetalleVenta" hidden="hidden">
	<jsp:include page="DetalleVenta.jsp"></jsp:include>
</div>

<div id="divDevolucion" hidden="hidden">
	<jsp:include page="Devolucion.jsp"></jsp:include>
</div>
	