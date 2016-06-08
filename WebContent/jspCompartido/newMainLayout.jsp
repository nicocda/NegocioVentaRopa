<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="entidades.Usuario"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="themes/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="themes/metisMenu.min.css">
	<link rel="stylesheet" type="text/css" href="themes/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="themes/select2.css">
	<link rel="stylesheet" type="text/css" href="themes/sb-admin-2.css">
	<link rel="stylesheet" type="text/css" href="themes/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="themes/jquery.dataTables.min.css">
	
	<script type="text/javascript" src="scripts/lib/jquery-1.12.3.js"></script>
	<script type="text/javascript" src="scripts/lib/jquery-ui.js"></script>
	<script type="text/javascript" src="scripts/custom/Ajax.js"></script>	
	<script type="text/javascript" src="scripts/lib/select2.js"></script>
	<script type="text/javascript" src="scripts/lib/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/lib/metisMenu.min.js"></script>
	<script type="text/javascript" src="scripts/lib/sb-admin-2.js"></script>
	<script type="text/javascript" src="scripts/lib/jquery.dataTables.min.js"></script>
	
	<link rel="icon" type="image/png" href="themes/images/favicon.ico" />
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Te vendo hasta a tu vieja</title>
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
                        <li>
                            <a href="#">
                            <div class="row">
									<div class="col-lg-3">	<img src="http://www.gylenterprise.com.ar/images/productos/1379605178.jpg" width="50" height="50" alt="Alternate Text" /> </div>
									<div class="col-lg-9">
										<p>Remera Roja de la muerte</p>
										<p>$200</p>
									</div>
							</div>
                            </a>
                        </li>
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

            <div class="navbar-default sidebar" role="navigation">
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
                       		<%if (((Usuario)session.getAttribute("usuario")).getTipoUsuario() >1){ %>
                          	  <a href="ABMUsuarios"  ><i class="fa fa-user fa-fw"></i> Usuarios</a>
                            <%} %>
                        </li>
                        <li>
                            <a href="Ventas"><i class="fa fa-star fa-fw"></i> Venta</a>
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