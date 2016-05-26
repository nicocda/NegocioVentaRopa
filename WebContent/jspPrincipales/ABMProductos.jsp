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
    src: url("./fonts/PF.ttf") format("truetype"); 
  }
  .barcodeFP {
    font-family: "PF";
    font-size: 110px;
    font-stretch :normal;
    font-style : normal;
    font-variant : normal;
    font-weight: 100;
    
    text-decoration: none;
    text-align: left;
    text-shadow : none;
    text-transform: none;
    
    line-height: 1.0;
    word-spacing: 10px;
  
  }
  </style>

<div id="divError"></div>
<div id="divPrincipal">
	<div class="row">
		<div class="col-lg-9">
		    <h1 class="page-header">Productos</h1>
		</div>
	</div>
	<table id="tablaProductos" class="display">
		<thead>
			<tr>
				<th width="10%">ID</td>
				<th width="30%">Descripcion</td>
				<th width="20%">Precio</td>
				<th width="20%">Estado</td>
				<th width="10%"></td>
				<th width="10%"></td>
			</tr>
		</thead>
		
	</table>
	<hr>
</div>
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
	<div id="divBarcode" hidden="hidden" >
		<div id="idBarcode" class="barcodeFP"></div>
		<label id="codNoBarcode"></label><br>
		<label id="descBarcode"></label>
		<div style="text-align: right">
				<br>
				<button class="btn btn-default" id="btnVolverDeBarcode">Volver</button>
		</div>
	</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>