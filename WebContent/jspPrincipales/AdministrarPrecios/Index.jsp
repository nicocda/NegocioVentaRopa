<script type="text/javascript" src="jspPrincipales/AdministrarPrecios/JS/AdministrarPrecios.js"></script>	

<div id="divError"></div>

<div class="container">
	<table id="tablaPrecios" class="table">
		<thead>
			<tr>
				<th>Código</th>
				<th>Descripción</th>
				<th>Precio actual</th>
				<th>Último cambio</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
	
	<hr/>
	
	<div class="row">
		<div class="col-md-4 col-md-offset-6">
			<select id="productos" style="width: 100%;"></select>
		</div>
		<div class="col-md-2">
			<button id="agregarProducto" type="button" class="btn btn-default pull-right">Agregar</button>
		</div>
	</div>

	
	
	<hr />
	
	<form id="guardarPrecios" class="form-group">
		<div class="row">
			<div class="col-md-5">
				<input id="fechaDesde" type="text" class="form-control" placeholder="Desde" readonly="readonly"/>
			</div>
			
			<div class=" col-md-5">
				<input id="fechaHasta" type="text" class="form-control" placeholder="Hasta" readonly="readonly" />
			</div>
			
			<div class=" col-md-2">
				<div class="input-group">
					<input id="porcentaje" type="text" class="form-control" placeholder="Porcentaje" />
					<span class="input-group-addon">%</span>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<br />
				<label id="diferenciaDias"></label>
				<hr>
				<button type="submit" class="btn btn-success pull-right">Guardar</button>
			</div>
		</div>
	</form>
</div>