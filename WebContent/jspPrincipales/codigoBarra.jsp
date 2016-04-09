<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
  @font-face {
    font-family: PF;
    src: url("fonts/PF.ttf") format("truetype"); 
  }
  .barcode {
    font-family: "PF";
    font-size:110px;
  }
  </style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>CODIGO DE BARRAS</h2> <br>
<div class="barcode"><%=request.getAttribute("codigo") %></div>
<div><%=request.getAttribute("codigo") %></div>
<br>
<label><%=request.getAttribute("desc") %></label>
</body>
</html>