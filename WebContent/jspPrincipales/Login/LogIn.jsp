<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import = "entidades.Usuario"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

		<link rel="stylesheet" type="text/css" href="/NegocioRopa/themes/jQueryUi.css">
		<link rel="stylesheet" type="text/css" href="/NegocioRopa/jspPrincipales/Login/login.css">
				
		<%
		String lib = "/scripts/lib/";
		String custom = "/scripts/custom/";
		String[] scripts = 
			{
				lib.concat("jQuery.js"), 
				lib.concat("jQueryUi.js"),
				custom.concat("Ajax.js"),
			};
		
		for (String script : scripts)
		{
		%>
			<script type="text/javascript" src="${pageContext.request.contextPath}<%=script%>"></script>
		<%
		}
		%>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/jspPrincipales/Login/JS/Login2.js"></script>
			
		<title>Iniciá Sesión</title>
	</head>
	
	<body>
		<div class="login-card">
		    <h1>Iniciá Sesión</h1><br>
			<form style="text-align: center"> 
				<input id="txtLogin" type="text" placeholder="Usuario">
				<input id="txtPass" type="password" placeholder="Contraseña">
				<button type="submit" id="btnLogin" class="login login-submit">Login</button>
				<div id="divLoader"></div>
				<label id="lblMensajeError" ></label>
			</form>
			  
			<div class="login-help">
				<a href="#">¿Olvidaste la contraseña?</a>
			</div>
		</div>
	</body>

</html>