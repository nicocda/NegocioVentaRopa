<%@page import="entidades.Usuario.tipoUsuario"%>
<div class="row">
	<div class="col-lg-12">
	    <h1 id="nuevoEditar"class="page-header">Nuevo Usuario</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Ingrese los datos del usuario
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label>Usuario</label>
							<input id="txtUsuario" class="form-control">
						</div>
						<div class="form-group">
							<label>Password</label>
							<input type="password" id="txtPassword" class="form-control">
						</div>
						<div class="form-group">
							<label>Nombre Completo</label>
							<input id="txtNombreYApellido" class="form-control">
						</div>
						<div class="form-group">
							<label>Email</label>
							<input id="txtEmail" class="form-control">
						</div>
						<div class="form-group">
							<label>Tipo</label>
							<select id="cbTipo" class="form-control">
								<%for(tipoUsuario tu: tipoUsuario.values()) 
								{%>
									<option value="<%=tu.ordinal()%>"><%=tu.name()%></option>
								<%}%>
							</select>
						</div>
					</div>
				</div>
			</div>
			
			<div class="pull-right">
			<hr />
				<button type="submit" id="btnGuardarCreate" class="btn btn-primary">Guardar</button>
               	<button type="reset" id="btnCancelarCreate" class="btn btn-default">Cancelar</button>
			</div>
		</div>
	</div>
</div>