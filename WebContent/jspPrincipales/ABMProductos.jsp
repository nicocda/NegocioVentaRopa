<%@page import="entidades.Usuario"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() == 1) 
{%>
<%@page import="entidades.Producto.estado"
	import="entidades.Producto"
	 import="negocio.ControladorABM"%>
<script type="text/javascript" src="../scripts/custom/ABMProductos.js"></script>	
<script type="text/javascript" src="./scripts/custom/ABMProductos.js"></script>	
<script type="text/javascript" src="../scripts/custom/popup.js"></script>
<script type="text/javascript" src="./scripts/custom/popup.js"></script>
<style type="text/css">
  @font-face {
    font-family: PF;
    src: url("fonts/PF.ttf") format("truetype"); 
  }
  .barcode {
    font-family: "PF";  
  }
  </style>

<H4>Productos</H4>
<div id="divError"></div>
<div id="accordion">
	<h3 id="mostrar">Lista de Productos</h3>
	<div style="max-height: 320px">
		<table class="CSSTableGenerator" id="tablaProductos">
			<tr>
				<td width="20%">ID</td>
				<td width="40%">Descripción</td>
				<td width="20%">Estado</td>
				<td width="10%">Precio</td>
				<td width="10%"></td>
			</tr>
			<%for(Producto p : ControladorABM.buscarTodosProductos())
			{%>
			<tr>
				<td class="idTabla"><%=p.getId() %></td>
				<td class="descripcionTabla"><%=p.getDescripcion() %></td>
				<td class="estadoTabla"><%=estado.values()[p.getEstado()].toString() %></td>
				<td class="precioTabla"><%=p.getPrecio().getPrecio() %></td>
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
		<input type="text" id="txtID" placeholder="ID" class="textInputs" disabled="disabled" value = <%=ControladorABM.obtenerIdCompleto('R', 'H')%>>
		<input type="text" id="txtDescripcion" class="textInputs" placeholder="Descripción">
		<input type="text" id="txtPrecio" class="textInputs" placeholder="Precio">
		<input type="submit" id="btnAceptar" class="botones" value="Guardar Producto">
	</div>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>

		<!-- HTML para el popup -->
	<div  id="dialog" >
	<%if(request.getAttribute("codigo") != null && request.getAttribute("desc")  != null) 
	{%>
	<h2>CODIGO DE BARRAS</h2> <br>
	<div class="barcode"><%=request.getAttribute("codigo") %></div>
	<div><%=request.getAttribute("codigo") %></div>
	<br>
	<label><%=request.getAttribute("desc") %></label>
	<%} %>
	</div>