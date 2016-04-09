<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="css/mootable.css">
</head>
<body>
		<form>
			<div>
				<div>
					<label>Cliente:</label>
					<input type="text">
				</div>
				<label>VENTA:</label>
				<table border="1">
				<% for (int i = 0; i<10; i++)
					{%>
					<tr>
						<td> precio</td>
						<td> descripcion</td>
						<td> tipo_producto</td>
						<th>subtipo</th>
					</tr>
				 	<%} %>
				
				</table>
				<label>Precio Total:</label>
				<input type="text">
			</div>
			<div>
				<label>Forma de pago:</label>
				  <div>
				  <input type="radio" name="gender" value="tarjeta"> Tarjeta <br>
				  <input type="radio" name="gender" value="cuentaCorriente"> Cuenta Corriente <br>
				  <input type="radio" name="gender" value="efectivo"> Efectivo
				  </div>
			</div>
			<input type="button" value="Realizar Venta">
		</form>
 	
 	
 	<script src="js/mootable.js"></script>
</body>
</html>