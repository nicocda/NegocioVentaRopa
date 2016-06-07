<%@page import="datos.CatalogoVentas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Venta"%>
<%@page import="entidades.Producto"%>
<%@page import="negocio.ControladorTransaccion"%>

	<link rel="stylesheet" type="text/css" href="themes/header.css">
	<link rel="stylesheet" type="text/css" href="themes/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="themes/componentes.css">
	<link rel="stylesheet" type="text/css" href="themes/select2.css">
	
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