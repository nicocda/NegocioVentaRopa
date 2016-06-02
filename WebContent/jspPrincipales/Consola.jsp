<%@page 
import="negocio.ControladorABM"
import="entidades.EventLog"
import="entidades.Configuracion"
import="entidades.Usuario"%>
<script type="text/javascript" src="scripts/lib/jquery.simplePagination.js"></script>
<script type="text/javascript" src="scripts/custom/Consola.js"></script>	
<link rel="stylesheet" type="text/css" href="themes/simplePagination.css"/>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() == 0) 
{%>
<%java.net.InetAddress host = java.net.InetAddress.getLocalHost(); %>
<H4>Consola (<%=host.getHostName()%>)</H4>
<div id="divError"></div>
<table style="width: 100%">
	<tr>
		<td style="vertical-align: top">
			<table border="1">
				<tr>
					<th style="font-weight: bold;">Config</th>
					<th style="font-weight: bold;">Valor</th>
				</tr>
				<%Configuracion configuracion = ControladorABM.buscarConfiguracion(); %>
				<tr>
					<td>ID Configuración</td>
					<td><%= configuracion.getId()%></td>
				</tr>
				<tr>
					<td>Permitir Log</td>
					<td><%= configuracion.isPermitirLog()%></td>
				</tr>
				<tr>
					<td>Tipo Log</td>
					<td><%= configuracion.getTipoLog()%></td>
				</tr>
			</table>
		</td>
		
		<td>&nbsp;</td>
		<td style="vertical-align: top;">
			<div style="text-align: right;">
				<table border="1" style="margin: 0 auto; text-align: left;" id="tablaPaginada">
					<thead>
						<tr>
							<th style="font-weight: bold;">Fecha Evento</th>
							<th style="font-weight: bold;">Tipo Evento</th>
							<th style="font-weight: bold;">Descripción</th>
							<th style="font-weight: bold;">Nivel</th>
						</tr>
					</thead>
					<tbody>
						<%for(EventLog e : ControladorABM.buscarTodosEventLog())
						{%>
						<tr>
							<td><%= e.getFechaAlta()%>></td>
							<td><%= e.getTipoDeEvento()%></td>
							<td><%= e.getDescripcion()%></td>
							<td><%= e.getNivelLog()%></td>
						</tr>
						<%} %>
					</tbody>
				</table>
			</div>
			<div style="text-align: center">
				<div id="paginacion" style="display: inline-block;" ></div>
			</div>
		</td>
	</tr>			
</table>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>


	