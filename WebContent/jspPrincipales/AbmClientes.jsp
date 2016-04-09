<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import = "entidades.Cliente"
	import = "java.util.ArrayList"
	import = "excepciones.RespuestaServidor"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ABM Cliente</title>
		<script type="text/javascript" src="../scripts/lib/jquery-1.12.3.js"></script>
		<script type="text/javascript" src="../scripts/lib/jquery-ui.js"></script>
		<link rel="stylesheet" href="../themes/jquery-ui.css">
		<script>
		$(function() 
		{
		   $( "#accordion" ).accordion();
		 });
		</script>
	</head>
	
	<body>
		<%RespuestaServidor mensaje = (RespuestaServidor)request.getAttribute("mensaje"); %>
		<%if(!mensaje.getErrors().isEmpty() || !mensaje.getWarnings().isEmpty())
		{%>
			<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;"> 
				<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span> 
			    <%for(String m : mensaje.getErrors())
			    {%>
			    <strong>Alert:</strong><%=m%></p>
			    <%}%>
			</div>
		<%}%>
		<div id="accordion">
		 	<h3>Section 1</h3>
			 <div>
				<form method="post" action="TestCliente">
					<p>Nombre: <input type="text" name="txtNombre" class="txtBox"></p>
					<p>Teléfono: <input type="text" name="txtTelefono" class="txtBox"></p>
					<p>Dirección: <input type="text" name="txtDireccion" class="txtBox"></p>
					<input type="submit" name="btnAgregarCliente">
				</form>
			</div>
			<h3>Section 2</h3>
			<div>
				<p>Holi</p>
			</div>
		</div> 
	</body>
</html>