<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="entidades.Usuario"
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
	<script type="text/javascript" src="./scripts/lib/select2.js"></script>
	<script type="text/javascript" src="./scripts/custom/Ajax.js"></script>	
	<%}
	else
	{%>
	<link rel="stylesheet" type="text/css" href="../themes/header.css">
	<link rel="stylesheet" type="text/css" href="../themes/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="../themes/componentes.css">
	<script type="text/javascript" src="../scripts/lib/jquery-1.12.3.js"></script>
	<script type="text/javascript" src="../scripts/lib/jquery-ui.js"></script>
	<script type="text/javascript" src="../scripts/lib/select2.js"></script>
	<script type="text/javascript" src="../scripts/custom/Ajax.js"></script>	
	<%} %>
	
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sistema de ventas</title>
	
</head>
<body>
		<header>
			<div class="wrapper">
				<%String url = (String)request.getAttribute("url");%>
				<%if (url.contains("Error"))
				{%>
				<div class="logo"><a href="/NegocioRopa"><img height="100" width="100" src="https://joyleedotnet.files.wordpress.com/2012/12/error-sadface-f0f0f0.png?w=605"></a></div>
				<%}
				else
				{%>
				<div class="logo"><a href="/NegocioRopa"><img height="100" width="100" src="http://vignette1.wikia.nocookie.net/dcuo/images/a/ab/GreenLanternSymbol.png/revision/latest?cb=20120103064916"></a></div>
				<%} %>
				<nav>
					<%if (((Usuario)session.getAttribute("usuario")) != null) 
					{%>
					<a href="Index?link=LogOut">Hola <%=((Usuario)session.getAttribute("usuario")).getNombreYApellido()%></a>
					<%}%>
					<a href="/NegocioRopa">Inicio</a>
					<a href="Index?link=AbmClientes" name="editar">Clientes</a>
					<a href="Index?link=AbmProducto">Productos</a>
					<a href="Index?link=Ventas">Venta</a>
				</nav>
			</div>
		</header>
	
	<div class="contenedor">
	
	<jsp:include page="<%= url %>" flush="true" />
	</div>
</body>
</html>