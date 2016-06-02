<%@page import="entidades.Usuario"
		import="entidades.Producto"
		import="java.util.List"
		import="entidades.Venta"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() <= 1) 
{%>

<script type="text/javascript" src="jspPrincipales/Ventas/JS/Ventas.js"></script>	

<div id="divError"></div>
<div id="principal">
	<div class="col-lg-9">
		    <h1 class="page-header">Venta</h1>
	</div>
	<form action="Ventas" method="POST">
		<div class="row">
		<select id="comboClientes" style="width: 80%" class="js-example-basic-single">
		</select>
		<a id="addCli"><i class="fa fa-plus-circle fa-fw"></i>�Nuevo Cliente?</a>
		</div>
		<table class="display" id="tablaProductos">
			<thead>
				<tr>
					<th width="10%">C�digo</th>
					<th width="50%">Descripci�n</th>
					<th width="30%">Precio</th>
				</tr>
			</thead>
				<%Venta venta = (Venta) session.getAttribute("venta");
					if(venta != null)
					{
						
							for(Producto p: venta.getProductosArrayList())
									{ %>
										<tr id="trTablaProducto">
										<td><%= p.getId() %></td>
										<td><%= p.getDescripcion()%></td>
										<td><%= p.getPrecio().getPrecio()%></td>
										</tr>
									<% }
					}
					%>
			<tfoot>
				<tr>
					<td style="background-color: #C0C0C0;"><a id="agregar"><i class="fa glyphicon-plus"></i></a></td>
					<td style="background-color: #C0C0C0;"><input id="txtID" class="textInputs" type="text" placeholder="Codigo Producto"></td>
					<td align="right" style="background-color: #C0C0C0;"><b id="total">Total: <%if(venta != null){%><%=venta.getImporte() %><%} else { %><label>0</label><%} %></b>
						<br>
						<div id="divPaga" style="text-align: right" hidden = "hidden">
							<label>Paga: </label>
							<input type="text" id="txtPago"/>
							<br>
							<label id="lblVueltoDebe"></label>
							<input type="text" id="txtVuelto"/>
						</div>
					</td>
				<tr>
			</tfoot>
		</table>
	
		<div style="text-align: center">
			<br><br>
			<input type="radio" name="radio" id="radioEfectivo" class="css-checkbox"/>
			<label class="css-label radGroup1" for="radioEfectivo"><i class="fa fa-dollar"></i> Efectivo</label>
			<input type="radio" name="radio" id="radioCtaCte" class="css-checkbox"/>
			<label class="css-label radGroup1" for="radioCtaCte"><i class="fa fa-floppy-o"></i> Cuenta Corriente</label>
			<input type="radio" name="radio" id="radioTarjeta" class="css-checkbox"/>
			<label class="css-label radGroup1" for="radioTarjeta"><i class=" fa fa-credit-card"></i> Tarjeta</label>
			<br><br>
			<input class="botones" type="button" id="realizarVenta" value="Realizar Venta">
		</div>
	</form>
</div>

<!-- HTML para el popup -->
<div id="addCliente" hidden="hidden">
	<jsp:include page="DetalleCliente.jsp"></jsp:include>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>