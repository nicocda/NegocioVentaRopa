<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="excepciones.RespuestaServidor"
	import="java.util.ArrayList"
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
	<script type="text/javascript" src="./scripts/custom/popup.js"></script>
	<%}
	else
	{%>
	<link rel="stylesheet" type="text/css" href="../themes/header.css">
	<link rel="stylesheet" type="text/css" href="../themes/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="../themes/componentes.css">
	<script type="text/javascript" src="../scripts/lib/jquery-1.12.3.js"></script>
	<script type="text/javascript" src="../scripts/lib/jquery-ui.js"></script>
	<script type="text/javascript" src="../scripts/custom/popup.js"></script>	
	<%} %>
	
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	
</head>
<body>
	<header>
		<div class="wrapper">
			<div class="logo">Ropa</div>
			
			<nav>
				<a href="#">Inicio</a>
				<a href="#">Servicios</a>
				<a href="#">Proyectos</a>
				<a href="#">Contacto</a>
			</nav>
		</div>
	</header>
	<div class="abajoHeader">
	<%ArrayList<String> errores = (ArrayList<String>)request.getAttribute("errores"); %>
		<%if(errores!=null) 
		{%>
		<%if(!errores.isEmpty())
		{%>
			<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;"> 
				<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span> 
			    <%for(String m : errores)
			    {%>
			    <strong>Alert:</strong><%=m%></p>
			    <%}%>
			</div>
		<%}
		}%>
		
	<%String url = (String)request.getAttribute("url");%>
	<jsp:include page="<%= url %>" flush="true" />
	</div>

</body>
</html>