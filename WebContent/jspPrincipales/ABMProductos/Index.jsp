<%@page import="entidades.Producto.estado"
import="entidades.Producto"
import="entidades.Usuario"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() >= 1) 
{%>

<script type="text/javascript" src="jspPrincipales/ABMProductos/JS/ABMProductos.js"></script>	
<script type="text/javascript" src="scripts/custom/popup.js"></script>
<style type="text/css">
  @font-face {
    font-family: PF;
    src: url("jspPrincipales/ABMProductos/fonts/PF.ttf") format("truetype"); 
  }
  .barcodeFP {
    font-family: "PF";
    font-size: 33px;
  }
  .barcode1
  {
  	float:left;
  	widht: 40%;
  }
  .barcode2
  {
  	float : right;
  	width : 40%;
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
				<th width="15%">ID</td>
				<th width="30%">Descripcion</td>
				<th width="15%">Precio</td>
				<th width="20%">Estado</td>
				<th width="10%"></td>
				<th width="10%"></td>
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
	<jsp:include page="PrintBarCode.jsp"></jsp:include>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>