<%@page import="entidades.Producto.estado"
import="entidades.Producto"
import="entidades.Usuario"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() >= 1) 
{%>

<script type="text/javascript" src="jspPrincipales/ABMProductos/JS/ABMProductos.js"></script>	
<script type="text/javascript" src="scripts/custom/popup.js"></script>
<link rel="stylesheet" type="text/css" href="jspPrincipales/ABMProductos/CSS/printBarCode.css"></link>
<style type="text/css">
  @font-face {
    font-family: PF;
    src: url("jspPrincipales/ABMProductos/fonts/PF.ttf") format("truetype"); 
  }
  .barcodeFP {
    font-family: "PF";
  }
  </style>

<div id="divError"></div>
<div id="divPrincipal">
	<div class="row">
		<div class="col-lg-9">
		    <h1 class="page-header">Productos</h1>
		</div>
		<div class="col-lg-3">
			<button id="btnMostrarCreate" class="btn btn-primary page-header pull-right" >Nuevo Producto</button>
		</div>
	</div>
	<table id="tablaProductos" class="display">
		<thead>
			<tr>
				<th width="15%">ID</th>
				<th width="30%">Descripcion</th>
				<th width="15%">Precio</th>
				<th width="20%">Estado</th>
				<th width="10%"></th>
				<th width="10%"></th>
			</tr>
		</thead>
	</table>
</div>

<div id="divCrearProducto" hidden="hidden">
	<jsp:include page="DetalleProducto.jsp"></jsp:include>
</div>
		
<div id="divBarcode" hidden="hidden" >
	<jsp:include page="BarCode.jsp"></jsp:include>
</div>

<div id="divPrintBarCode" hidden="hidden">
	<jsp:include page="PrintBarCode.html"></jsp:include>
</div>

<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>