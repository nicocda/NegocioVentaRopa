<%@page import="entidades.Usuario"
		import="entidades.Producto"
		import="java.util.List"
		import="entidades.Venta"
		import="entidades.TipoTarjeta"
		import="negocio.ControladorABM"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() >= 1) 
{%>
<script type="text/javascript" src="jspPrincipales/Ventas/JS/Ventas.js"></script>	
<div id="divError"></div>
<div id="divPrincipal">

	<div class="col-lg-9">
		    <h1 class="page-header">Transacción</h1>
	</div>
	
	<form action="Ventas" method="POST">
	
		<div class="row">
			<select id="comboClientes" style="width: 80%" class="js-example-basic-single"></select>
			<input  type="button" id="addCli" class="btn btn-link"value="¿Nuevo Cliente?">
		</div>
		<%Venta venta = (Venta) session.getAttribute("venta");%>
		<div id="divTabla">
			<hr>
			<table id="tablaVentas" class="display">
				<thead>
					<tr>
						<th width="15%">Código</th>
						<th width="50%">Descripción</th>
						<th width="25%">Precio</th>
						<th width="10%"></th>
					</tr>
				</thead>
			</table>
			
			<table style="width: 100%">
				<tr>
					
					<td style="text-align: center"> <button style= "width:60%" id="agregar" class="btn btn-info">Agregar</button></td>
					<td><select id="comboProductos" style="width: 80%" class="js-example-basic-single"></select></td>
					<td><b id="total">Total: $<%if(venta != null){%><%=venta.getImporte() %><%} else { %><label>0</label><%} %></b>
					</td>
				<tr>
			</table>
		</div>
	
		<hr>
		<div style="text-align: center">
			<br><br>
			
			<label class="css-label radGroup1" for="radioEfectivo">
				<input type="radio" name="tipoPago" id="radioEfectivo" class="css-checkbox" value="1"/>
				<i class="fa fa-dollar"></i> 
				Efectivo
			</label>
			
			<label class="css-label radGroup1" for="radioCtaCte">
				<input type="radio" name="tipoPago" id="radioCtaCte" class="css-checkbox" value="2"/>
				<i class="fa fa-floppy-o"></i> 
				Cuenta Corriente
			</label>
			
			<label class="css-label radGroup1" for="radioTarjeta">
				<input type="radio" name="tipoPago" id="radioTarjeta" class="css-checkbox" value="3"/>
				<i class=" fa fa-credit-card"></i> 
				Tarjeta
			</label>
			
			<br><br>
			<input class="btn btn-danger" type="button" id="realizarVenta" value="Realizar Venta">
		</div>
	</form>
</div>
<div class="tarjeta" hidden="true">
	<jsp:include page="Tarjeta.jsp"></jsp:include>
</div>
	
		
<!-- HTML para el popup -->
<div id="addCliente" hidden="hidden" title="Agregar cliente">
	<jsp:include page="DetalleCliente.jsp"></jsp:include>
</div>

<div id="divConfirmacion" hidden="hidden" title="Confirmacion">
	<div style="text-align: center;">
	<p id="txtConfirmacion">¿Desea realizar la venta?</p>
	</div>
</div>
<div id="divConfirmacionAgregar" hidden="hidden" title="Confirmacion">
	<div style="text-align: center;">
	<p id="txtConfirmacion">¿Desea hacer una devolución?</p>
	</div>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>