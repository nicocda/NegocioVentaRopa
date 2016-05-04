<%@page import="negocio.ControladorABM"%>
<%@page import="entidades.Prestamo"%>
<%@page import="entidades.Venta"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.Producto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="negocio.ControladorTransaccion"%>
<%@page import="entidades.Venta.formaPago"%>

<script type="text/javascript" src="./scripts/custom/ReporteVenta.js"></script>	
<script type="text/javascript" src="../scripts/custom/ReporteVenta.js"></script>

<H4>Reporte de Ventas</H4>
<div class="filtros">
	<label style="color: #6F6F6F">Clientes</label>
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

<table class="CSSTableGenerator" id="tablaTransacciones">
</table>
	