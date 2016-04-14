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
		<header>
			<div class="wrapper">
				<div class="logo"><a href="TestCliente"><img height="78" width="150" src="http://vignette1.wikia.nocookie.net/logopedia/images/a/ab/200px-Lacoste_logo_svg.png/revision/latest?cb=20100911064850"></a></div>
				
				<nav>
					<a href="#">Contacto</a>
					<a id="agregar">Registrate Ahora!</a>
				</nav>
			</div>
		</header>
		<form action="Login" method="POST">
		<div class="ajustar">
		<label for="userId">Usuario:</label>
		<input type="text" autofocus="autofocus" id="txtLogin"/>
		<label for="userPassword">Contraseña:</label>
		<input type="password" id="txtPass"/>
		<label id="mensaje"></label><br>
		<input class="botones" id="btnIngresar" type="submit" value="Ingresar"/><br>
		<a href=#>¿No puedes ingresar?</a>
		<%Usuario usu = (Usuario) request.getAttribute("usuario");%>
		<label><%=usu.getNombreYApellido() %></label>
		</div>
		</form>
		
		
		<!-- HTML para el popup -->
		<div id="dialog" title="Registrate" style="display:none;">
			<label for="userId">Usuario:</label>
			<input type="text" id="registrarId"/>
			<label for="userPassword">Contraseña:</label>
			<input type="password" id="registrarPass"/>
			<input class="botones" type="button" value="Ingresar"/>
		</div>
	</body>
</html>