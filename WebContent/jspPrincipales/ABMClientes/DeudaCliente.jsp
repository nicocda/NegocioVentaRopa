<input type="hidden" id="tablaVentasId" value="0">
<div class="row">
	<div class="col-lg-12">
	    <h2>
	    	Deudas de: <label id="nombreDeuda"></label>
		</h2>
		<h3>Total deuda: $<label id="totalDeuda"></label></h3>
	</div>
</div>
<table id="tablaVentasMorosas" class="display">
	<thead>
			<tr>
				<th width="20%">ID</th>
				<th width="40%">Fecha de Venta</th>
				<th width="20%">Importe de Venta</th>
				<th width="20%">Deuda Pendiente</th>
			</tr>
	</thead>
</table>
<br>
<label>Ingrese la cantidad de dinero que desea pagar:</label>
<input type="text" id="pagoUnico" placeholder="Monto a pagar" />
<input type="button" id="registrarPagoDeuda" class="btn btn-info" value="Cancelar Deuda"/> 
<br>
<button class="btn btn-default pull-right" id="btnVolverDeDeudas">Volver</button>