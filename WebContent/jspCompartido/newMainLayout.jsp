<%@page import="entidades.Producto"%>
<%@page import="entidades.Venta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="entidades.Usuario"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<%
	//Carga de css de forma dinámica
	String themes = "/themes/";
	String[] stylesheets = 
		{
			themes.concat("bootstrap.css"), 
			themes.concat("metisMenu.css"),
			themes.concat("jQueryUi.css"), 
			themes.concat("select2.css"), 
			themes.concat("SBAdmin.css"), 
			themes.concat("font-awesome/css/font-awesome.min.css"), 
			themes.concat("dataTable.css"),
			themes.concat("componentes.css")
		};
	
	for (String css : stylesheets)
	{
	%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}<%=css%>">
	<%
	}
	%>
	
	<%
	//Carga de script de fomra dinámica
	String lib = "/scripts/lib/";
	String[] scripts = 
		{
			lib.concat("jQuery.js"), 
			lib.concat("jQueryUi.js"),
			lib.concat("dataTable.js"), 
			lib.concat("bootstrap.js"), 
			lib.concat("simplePagination.js"), 
			lib.concat("metisMenu.js"), 
			lib.concat("SBAdmin.js"),
			lib.concat("select2.js")
		};
	
	for (String script : scripts)
	{
	%>
		<script type="text/javascript" src="${pageContext.request.contextPath}<%=script%>"></script>
	<%
	}
	%>
	
	<script type="text/javascript" src="scripts/custom/Ajax.js"></script>
	<script type="text/javascript" src="scripts/custom/DataTables.js"></script>
	
	<link rel="icon" type="image/png" href="themes/images/favicon.ico" />
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Software Kairós</title>
</head>
<body>

<div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">Negocio Ropa</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-shopping-cart fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <%if((Venta)session.getAttribute("venta") != null) 
                        {%>
                        	<%for(Producto p : ((Venta)session.getAttribute("venta")).getProductosArrayList())
                       		{%>
		                        <li>
		                            <a href="#">
		                            <div class="row">
											<div class="col-lg-3">	<img src="http://www.gylenterprise.com.ar/images/productos/1379605178.jpg" width="50" height="50" alt="Alternate Text" /> </div>
											<div class="col-lg-9">
												<p><%=p.getDescripcion()%></p>
												<p>$<%=p.getPrecio().getPrecio()%></p>
											</div>
									</div>
		                            </a>
		                        </li>
		                        <%}%>
                        <%}%>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Editar Venta</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                    	<%if (((Usuario)session.getAttribute("usuario")) != null) 
						{%>
                    	<li>
                    		<div style="text-align: center;">
                    			<strong><%=((Usuario)session.getAttribute("usuario")).getNombreYApellido()%></strong>
                    		</div>
                    	</li>
                    	<%} %>
                        <li>
                        	<a href="#"><i class="fa fa-user fa-fw"></i>Perfil</a>
                        </li>
                        <li>
                        	<a href="Index?link=Consola"><i class="fa fa-gear fa-fw"></i>Consola</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                        	<a href="Index?link=LogOut"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        
                        <li>
                            <a href="/NegocioRopa"><i class="fa fa-home fa-fw"></i> Inicio</a>
                        </li>
                        <li>
                            <a href="ABMClientes"><i class="fa fa-male fa-fw"></i> Clientes</a>
                        </li>
                        <li>
                            <a href="ABMProductos"><i class="fa fa-list-ul fa-fw"></i> Productos</a>
                        </li>
                        <li>
                        <%if (((Usuario)session.getAttribute("usuario")) != null) 
						{
                       		if (((Usuario)session.getAttribute("usuario")).getTipoUsuario() >1)
                       		{%>
                          	  <a href="ABMUsuarios"  ><i class="fa fa-user fa-fw"></i> Usuarios</a>
                            <%} 
                        }%>
                        </li>
                        <li>
                            <a href="Ventas"><i class="fa fa-star fa-fw"></i> Venta</a>
                        </li>
                        <li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-folder-open fa-fw"></i>Reportes<b class="caret"></b></a>
	                        	<ul class="nav">
					                <li><a href="ReporteVentas">Ventas</a></li>
					                <li><a href="ReporteVentas">Tipo de Tarjeta</a></li>
				              	</ul>
                        <li>
                            <a href="Condicional"><i class="fa fa-book fa-fw"></i> Condicional</a>
                        </li>
                        <li>
                            <a href="ReporteVentas"><i class="fa fa-folder-open fa-fw"></i> Reportes</a>
                        </li>
                    </ul>
                </div>
                
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
         <div id="page-wrapper">
         <%String url = (String)request.getAttribute("url");%>
        	<jsp:include page="<%= url %>"></jsp:include>
       	</div>
</div>





</body>
</html>