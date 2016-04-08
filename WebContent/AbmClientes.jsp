<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import = "entidades.Cliente"
	import = "java.util.ArrayList"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ABM Cliente</title>
</head>
<body>

	<%ArrayList<Cliente> cbCliente = (ArrayList<Cliente>)request.getAttribute("listaClientes"); %>
	<select>
	<%for(Cliente c : cbCliente)
	{%>
		<option value="<%= c.getId()%>"><%= c.getNombreApellido() %></option>
	<%} %>
	</select>
	<form method="post" action="TestCliente">
		<p>Nombre: <input type="text" name="txtNombre" class="txtBox" placeholder="Ingrese nombre del cliente"></p>
		<p>Teléfono: <input type="text" name="txtTelefono" class="txtBox" placeholder="Ingrese teléfono del cliente"></p>
		<p>Dirección: <input type="text" name="txtDireccion" class="txtBox" placeholder="Ingrese dirección del cliente"></p>
		<input type="submit" name="btnAgregarCliente">
	</form>
</body>
</html>