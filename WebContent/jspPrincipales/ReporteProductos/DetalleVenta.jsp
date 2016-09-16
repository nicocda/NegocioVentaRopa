<input type="hidden" id="idVenta" value="0">
<div class="row">
	<div  class="col-lg-12" >
	    <small style="font-size:20px" class="page-header">
	    	<u>Compra de:</u> <small style="font-size:22px" id="nombreComprador"></small>
		</small>
		<small style="padding-left: 100px; font-size:20px">
		<u>Fecha de Venta:</u> <small style="font-size:22px" id="fechaVta"></small>
		</small>
		<small style="padding-left: 100px; font-size:20px">
		<u>Forma de pago:</u> <small style="font-size:22px" id="formaPago"></small>
		</small>
	</div>
</div>
<div id="divTabla">
			<hr>
			<table id="tablaDetalleVenta" class="display">
				<thead>
					<tr>
						<th width="15%">Código</th>
						<th width="50%">Descripción</th>
						<th width="30%">Precio</th>
						<th width="5%">Estado</th>
					</tr>
				</thead>
			</table>
</div>
<button style= "width:20%; float:right" id="btnVolver" class="btn btn-info">Volver</button>
