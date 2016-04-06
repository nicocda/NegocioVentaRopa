<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de un producto</title>
</head>
<body>
<form action="ABMProducto" method="post">
<select name="tipo">
<option value="R">Ropa</option>
<option value="M">Marroquineria</option>
<option value="B">Bijouterie</option>
<option value="Z">Zapatos</option>
</select>
<br>
<select name="subTipo">
<option value="H">Hombre</option>
<option value="M">Mujer</option>
<option value="N">Niño</option>
</select>
<br>
Codigo: <input type="text" name="cod" paceholder="Codigo Numerico"/>
<br>
Descripcion: <input type="text" name="desc" paceholder="Descripcion"/>
<br>
<input type="submit" name="addProd" value="Agregar Articulo"/>
</form>
</body>
</html>