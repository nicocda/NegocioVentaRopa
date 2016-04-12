<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="excepciones.RespuestaServidor"
	import="entidades.Cliente"
	import="java.util.ArrayList"
	import="negocio.ControladorABM"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%if ((String)request.getAttribute("servlet")!=null) 
	{%>
	<link rel="stylesheet" type="text/css" href="./themes/header.css">
	<link rel="stylesheet" type="text/css" href="./themes/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="./themes/componentes.css">
	<script type="text/javascript" src="./scripts/lib/jquery-1.12.3.js"></script>
	<script type="text/javascript" src="./scripts/lib/jquery-ui.js"></script>
	<script type="text/javascript" src="./scripts/custom/Ajax.js"></script>	
	<%}
	else
	{%>
	<link rel="stylesheet" type="text/css" href="../themes/header.css">
	<link rel="stylesheet" type="text/css" href="../themes/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="../themes/componentes.css">
	<script type="text/javascript" src="../scripts/lib/jquery-1.12.3.js"></script>
	<script type="text/javascript" src="../scripts/lib/jquery-ui.js"></script>
	<script type="text/javascript" src="../scripts/custom/Ajax.js"></script>	
	<%} %>
	
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema de ventas</title>
	
</head>
<body>
	
	<header>
		<div class="wrapper">
			<div class="logo">Ropa</div>
			
			<nav>
				<a href="#">Inicio</a>
				<a href="testCliente" name="editar">Editar Cliente</a>
				<a href="#">Proyectos</a>
				<a href="#">Contacto</a>
			</nav>
		</div>
	</header>

		
	<%String url = (String)request.getAttribute("url");%>
	<jsp:include page="<%= url %>" flush="true" />
</body>
</html>