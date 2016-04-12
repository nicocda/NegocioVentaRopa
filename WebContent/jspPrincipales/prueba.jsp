<%@page import="entidades.Producto.estado"%>
<%@page import="entidades.Producto"%>
<%@page import="negocio.ControladorABM"%>
<script type="text/javascript" src="../scripts/custom/ABMProductos.js"></script>	
<script type="text/javascript" src="./scripts/custom/ABMProductos.js"></script>	
<script type="text/javascript" src="../scripts/custom/popup.js"></script>
<script type="text/javascript" src="./scripts/custom/popup.js"></script>

<H4>Productos</H4>
<div id="divError"></div>
<div id="accordion">
	<h3>Lista de Productos</h3>
	<div style="max-height: 320px">
		<table class="CSSTableGenerator">
			<tr>
				<td width="20%">ID</td>
				<td width="40%">Descripción</td>
				<td width="20%">Estado</td>
				<td width="10%">Precio</td>
				<td width="10%"></td>
			</tr>
			<%for(Producto p : ControladorABM.buscarProductos())
			{%>
			<tr>
				<td class="idTabla"><%=p.getId() %></td>
				<td class="descripcionTabla"><%=p.getDescripcion() %></td>
				<td><%=estado.values()[p.getEstado()].toString() %></td>
				<td class="precioTabla">200</td>
				<td><input type="button" class="botones btnEditar" value="Editar"></td>
			</tr>
			<%} %>
		</table>
	</div>
	<h3 id="nuevoEditar">Nuevo/Editar Producto:</h3>
	<div>
		<select id="cbTipo">
			<optgroup label="Tipo">
				<option value="R">Ropa</option>
				<option value="M">Marroquineria</option>
				<option value="B">Bijouterie</option>
				<option value="Z">Zapatos</option>
			</optgroup>
		</select>
		<select id="cbSubTipo" >
			<optgroup label="Subtipo">
				<option value="H">Hombre</option>
				<option value="M">Mujer</option>
				<option value="N">Niño</option>
			</optgroup>
		</select>
		<button id="btnRestaurar">x</button>
		<input type="text" id="txtID" placeholder="ID" disabled="disabled" value = <%=ControladorABM.obtenerIdCompleto('R', 'H')%>>
		<input type="text" id="txtDescripcion" placeholder="Descripción">
		<input type="text" id="txtPrecio" placeholder="Precio">
		<input type="submit" id="btnAceptar" class="botones" value="Guardar Producto">
	</div>
</div>