<%@page import="entidades.Usuario"%>
<%@page import="entidades.Venta"%>
<%@page import="java.util.ArrayList"%>
<%
if ((Usuario)session.getAttribute("usuario")!= null && ((Usuario)session.getAttribute("usuario")).getTipoUsuario() == 1) 
{%>
<%@ page import="entidades.Cliente"
import="java.util.ArrayList"
import="negocio.ControladorABM" %>
<script type="text/javascript" src="../scripts/custom/ABMClientes.js"></script>	
<script type="text/javascript" src="./scripts/custom/ABMClientes.js"></script>	
<script type="text/javascript" src="../scripts/custom/popup.js"></script>
<script type="text/javascript" src="./scripts/custom/popup.js"></script>

<div id="divError"></div>
<div id="divPrincipal">
	<div class="row">
		<div class="col-lg-12">
		    <h1 class="page-header">Clientes</h1>
		</div>
	</div>
	<table id="tablaClientes" class="display">
		<thead>
			<tr>
				<th width="10%">ID</td>
				<th width="30%">Nombre y Apellido</td>
				<th width="20%">Dirección</td>
				<th width="20%">Teléfono</td>
				<th width="10%">Deuda</td>
				<th width="10%"></td>
			</tr>
		</thead>
		<tbody>
			<%
			ArrayList<Cliente> clientes = ControladorABM.buscarTodosClientes();
			for(Cliente cl : clientes)
			{%>
			
			<tr>
				<td id="idCliente" align="center" class="idTabla"><%=cl.getId() %></td>
				<td class="nyaTabla"><%=cl.getNombre() %></td>
				<td class="direTabla"><%=cl.getDireccion() %></td>
				<td class="telTabla"><%=cl.getTelefono() %></td>
				<td align="center"><input type="button" class="botones btnDeuda" value="Ver Deuda"></td>
				<td align="center"><input type="button" class="botones btnEditar" value="Editar"></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<hr>
	<button id="btnMostrarCreate" class="btn btn-primary">Nuevo Cliente</button>
</div>

<div id="divCrearCliente" hidden="hidden">

	<div class="row">
		<div class="col-lg-12">
		    <h1 class="page-header">Nuevo Cliente</h1>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					Ingrese los datos del cliente
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
								<input type="hidden" id="txtID">
								<div class="form-group">
									<label>Nombre</label>
									<input id="txtNombre" class="form-control">
								</div>
								<div class="form-group">
									<label>Apellido</label>
									<input id="txtApellido" class="form-control">
								</div>
								<div class="form-group">
									<label>Dirección</label>
									<input id="txtDireccion" class="form-control">
								</div>
								<div class="form-group">
									<label>Teléfono</label>
									<input id="txtTelefono" class="form-control">
								</div>
								<button type="submit" id="btnGuardarCreate" class="btn btn-primary">Guardar</button>
                                <button type="reset" id="btnCancelarCreate" class="btn btn-default">Cancelar</button>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="divDeudas"  hidden="hidden">
	<table id="tablaVentasMorosas" class="CSSTableGenerator"></table>
	<div style="text-align: right">
		<br>
		<button class="botones" id="btnVolverDeDeudas">Volver</button>
	</div>
</div>
<%}
else
{%>
<script>window.location.href='/NegocioRopa/Index?link=Error';</script>
<%}%>


	