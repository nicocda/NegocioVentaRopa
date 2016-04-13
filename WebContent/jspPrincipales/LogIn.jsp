<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		<%} %>
	
	
		<title>Iniciar sesi�n</title>
	</head>
	<body>
		<header>
			<div class="wrapper">
				<div class="logo"><a href="TestCliente"><img height="78" width="150" src="http://vignette1.wikia.nocookie.net/logopedia/images/a/ab/200px-Lacoste_logo_svg.png/revision/latest?cb=20100911064850"></a></div>
				
				<nav>
					<a href="#">Contacto</a>
					<a id="agregar">Registrate Ahora!</a>
				</nav>
			</div>
		</header>
		<label for="userId">Usuario:</label>
		<input type="text" autofocus="autofocus" id="userId"/>
		<label for="userPassword">Contrase�a:</label>
		<input type="password" id="userPassword"/>
		<input type="button" value="Ingresar"/>
		<label>Recordar Usuario</label>
		<input type="checkbox">
		<a href=#>�No puedes ingresar?</a>
		
		
		<!-- HTML para el popup -->
		<div id="dialog" title="Registrate" style="display:none;">
			<label for="userId">Usuario:</label>
			<input type="text" id="registrarId"/>
			<label for="userPassword">Contrase�a:</label>
			<input type="password" id="registrarPass"/>
			<input type="button" id="button" value="Ingresar"/>
		</div>
	</body>
</html>