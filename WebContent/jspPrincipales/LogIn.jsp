<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import = "entidades.Usuario"
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
		<script type="text/javascript" src="./scripts/custom/popup.js"></script>
		<script type="text/javascript" src="./scripts/custom/login.js"></script>		
		<%}
		else
		{%>
		<link rel="stylesheet" type="text/css" href="../themes/header.css">
		<link rel="stylesheet" type="text/css" href="../themes/jquery-ui.css">
		<link rel="stylesheet" type="text/css" href="../themes/componentes.css">
		<script type="text/javascript" src="../scripts/lib/jquery-1.12.3.js"></script>
		<script type="text/javascript" src="../scripts/lib/jquery-ui.js"></script>
		<script type="text/javascript" src="../scripts/custom/Ajax.js"></script>	
		<script type="text/javascript" src="../scripts/custom/popup.js"></script>
		<script type="text/javascript" src="../scripts/custom/login.js"></script>	
		<%} %>
	
	
		<title>Iniciar sesión</title>
	</head>
	<body>
	<%if ((Usuario)session.getAttribute("usuario")== null)
	{%>
		<header>
			<div class="wrapper">
				<div class="logo"><a href="/NegocioRopa"><img height="100" width="100" src="http://vignette1.wikia.nocookie.net/dcuo/images/a/ab/GreenLanternSymbol.png/revision/latest?cb=20120103064916""></a></div>
				
				<nav>
					<a href="#">Contacto</a>
					<a id="agregar">Registrate Ahora!</a>
				</nav>
			</div>
		</header>
		<h4>Iniciar Sesión</h4>
		<div class="ajustar"></div>
		<div class="borde">		
			<div style="text-align:center;">
				<h4>Ingresar</h4>
				<label id="mensaje"></label>
			</div>
			<form action="Login" method="POST">
			<label for="userId">Usuario:</label>
			<input type="text" class="textInputs" autofocus="autofocus" id="txtLogin"/>
			<label for="userPassword">Contraseña:</label>
			<input type="password" class="textInputs" id="txtPass"/><br>
			<div style="text-align:center;">
				<input class="botones" id="btnIngresar" type="button" value="Ingresar"/><br>
				<a href=#>No puedo ingresar</a>
			</div>
			</form>
		</div>
		
		
		
		<!-- HTML para el popup -->
		<div id="dialog" title="Registrate" style="display:none;">
			<label for="userId">Usuario:</label>
			<input type="text" id="registrarId"/>
			<label for="userPassword">Contraseña:</label>
			<input type="password" id="registrarPass"/>
			<label for="userPassword">Validar Contraseña:</label>
			<input type="password" id="validarPass"/>
			<label for="userPassword">Nombre y Apellido:</label>
			<input type="text" id="registrarNomyAp"/>
			<label for="userPassword">Email:</label>
			<input type="text" id="registrarEmail"/>
			<input class="botones" type="button" value="Registrar"/>
		</div>
	<%}
	else
	{%>
		<script>window.location.href='/NegocioRopa/Index?link=ABMClientes';</script>
	<%}%>
	</body>
</html>