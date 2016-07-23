<%@page import="negocio.ControladorABM"%>

<div class="row">
	<div class="col-lg-12">
	    <h1 id="nuevoEditar"class="page-header">Nuevo Producto</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Ingrese los datos del producto
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
					<div class="form-group" id="tipos">
						<h3>Producto 1</h3>
						
							<select id="cbTipo" class="form-control">
								<optgroup label="Tipo">
									<option value="R">Ropa</option>
									<option value="M">Marroquineria</option>
									<option value="B">Bijouterie</option>
									<option value="Z">Zapatos</option>
								</optgroup>
							</select>
							<select id="cbSubTipo" class="form-control">
								<optgroup label="Subtipo">
									<option value="H">Hombre</option>
									<option value="M">Mujer</option>
									<option value="N">Niño</option>
								</optgroup>
							</select>
						</div>
						<div class="form-group">
							<label>ID</label>
							<input type="text" id="txtID" placeholder="ID" class="form-control" disabled="disabled" value = <%=ControladorABM.obtenerIdCompleto('R', 'H')%>>
						</div>
						<div class="form-group">	
							<label>Descripcion</label>
							<input type="text" id="txtDescripcion" class="form-control" placeholder="Descripción">
						</div>
						<div class="form-group">
							<label>Precio</label>
							<input type="text" id="txtPrecio" class="form-control" placeholder="Precio">
						</div>
					</div>
					<div class="col-lg-6" id="divProducto2">
						<h3>Producto 2</h3>
						<div class="form-group" >
							<select id="cbTipo2" class="form-control">
								<optgroup label="Tipo">
									<option value="R">Ropa</option>
									<option value="M">Marroquineria</option>
									<option value="B">Bijouterie</option>
									<option value="Z">Zapatos</option>
								</optgroup>
							</select>
							<select id="cbSubTipo2" class="form-control">
								<optgroup label="Subtipo">
									<option value="H">Hombre</option>
									<option value="M">Mujer</option>
									<option value="N">Niño</option>
								</optgroup>
							</select>
						</div>
						<div class="form-group">
							<label>ID</label>
							<input type="text" id="txtID2" placeholder="ID" class="form-control" disabled="disabled" value = <%=ControladorABM.obtenerIdCompleto2('R', 'H','R', 'H')%> >
						</div>
						<div class="form-group">	
							<label>Descripcion</label>
							<input type="text" id="txtDescripcion2" class="form-control" placeholder="Descripción">
						</div>
						<div class="form-group">
							<label>Precio</label>
							<input type="text" id="txtPrecio2" class="form-control" placeholder="Precio">
						</div>
					</div>
				</div>
			</div>
			
			<div class="pull-right">
			<hr />
				
				<button type="submit" id="btnAceptar" class="btn btn-primary">Guardar</button>
				<button type="reset" id="btnRestaurar" class="btn btn-default">Limpiar Campos</button>
				<button id="btnCancelarCreate" class="btn btn-default">Volver</button>
				<button id="solo1" class="btn btn-warning btn-outline">Agregar solo 1 producto</button>
			</div>
		</div>
	</div>
</div>