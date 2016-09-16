<%@page import="entidades.Usuario"
		import="entidades.Producto"
		import="java.util.List"
		import="entidades.Venta"
		import="entidades.Prestamo"
		import="negocio.ControladorABM"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() >= 1) 
{%>
<script type="text/javascript" src="jspPrincipales/Condicional/JS/Condicional.js"></script>	
<div id="divError"></div>
<div id="divPrincipal">

	<div class="col-lg-9">
		    <h1 class="page-header">Condicional</h1>
	</div>
	
	<form action="Ventas" method="POST">
	
		<div class="row">
			<select id="comboClientes" style="width: 80%" class="js-example-basic-single"></select>
		</div>
		<%Prestamo prestamo = (Prestamo) session.getAttribute("prestamo");%>
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
					<td><b id="total">Total: $<%if(prestamo != null){%><%=prestamo.getImporte() %><%} else { %><label>0</label><%} %></b>
					</td>
				<tr>
			</table>
		</div>
	<input class="btn btn-danger" type="button" id="realizarVenta" value="Registrar Cambios">
		
	</form>
</div>


<div id="divConfirmacion" hidden="hidden" title="Confirmacion">
	<div style="text-align: center;">
	<p id="txtConfirmacion">¿Desea realizar la Transacción?</p>
	</div>
</div>

<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>