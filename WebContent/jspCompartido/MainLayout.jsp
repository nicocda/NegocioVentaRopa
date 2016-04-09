<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="excepciones.RespuestaServidor"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript" src="../scripts/lib/jquery-1.12.3.js"></script>
	<script type="text/javascript" src="../scripts/lib/jquery-ui.js"></script>
	<link rel="stylesheet" href="../themes/jquery-ui.css">
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<%RespuestaServidor mensaje = (RespuestaServidor)request.getAttribute("mensaje"); %>
		<%if(mensaje!=null) 
		{%>
		<%if(!mensaje.getErrors().isEmpty() || !mensaje.getWarnings().isEmpty())
		{%>
			<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;"> 
				<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span> 
			    <%for(String m : mensaje.getErrors())
			    {%>
			    <strong>Alert:</strong><%=m%></p>
			    <%}%>
			</div>
		<%}
		}%>
	<%String url = (String)request.getAttribute("url");%>
	<jsp:include page="<%= url %>" flush="true" />
</body>
</html>