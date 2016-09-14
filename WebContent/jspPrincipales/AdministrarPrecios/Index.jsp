<script type="text/javascript" src="jspPrincipales/AdministrarPrecios/JS/AdministrarPrecios.js"></script>	

<div>
	<table class="table table-stripped">
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
			<tr>
				<td>RH00003</td>
				<td>Tu</td>
				<td>Hermana</td>
				<td>5pe</td>
				<td><button>Eliminar</button></td>
			</tr>
			<tr>
				<td>RH00003</td>
				<td>Tu</td>
				<td>Hermana</td>
				<td>5pe</td>
				<td><button>Eliminar</button></td>
			</tr>
			<tr>
				<td>RH00003</td>
				<td>Tu</td>
				<td>Hermana</td>
				<td>5pe</td>
				<td><button>Eliminar</button></td>
			</tr>
			<tr>
				<td>RH00003</td>
				<td>Tu</td>
				<td>Hermana</td>
				<td>5pe</td>
				<td><button>Eliminar</button></td>
			</tr>
		</tbody>
	</table>
	
	
	<select id="productos" style="width: 90%;">		
	</select>
	
	<button type="button" class="btn btn-default">Agregar</button>
	
	<hr />
	
	<form class="form-group">
		<div class="row">
			<div class="col-md-5">
				<input id="fechaDesde" type="text" class="form-control" placeholder="Desde" readonly="readonly"/>
			</div>
			
			<div class=" col-md-5">
				<input id="fechaHasta" type="text" class="form-control" placeholder="Hasta" readonly="readonly" />
			</div>
			
			<div class=" col-md-2">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Porcentaje" />
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