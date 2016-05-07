$(document).ready(function()
{
	agregarEventos();
	cargarComboClientes();
});

function agregarEventos()
{
	$(document).on("click", "#agregar", function()
	{
		$.postDataSinExito('/NegocioRopa/Ventas', { "action": "agregarProducto" , "id": $("#txtID").val()}, function()
		{
			recargarTabla();
		});
	});
	
	$("#realizarVenta").click(function()
	{
		if($("#radio1").prop("checked"))
			var formaPago = 1;
		else if($("#radio2").prop("checked"))
			var formaPago = 2;
		else if($("#radio3").prop("checked"))
			var formaPago = 3;
		else 
			var formaPago = 0;
		$.postData('/NegocioRopa/Ventas', {"action":"realizarVenta", "idCliente": $("#comboClientes").val(), "formaPago": formaPago }, function(result)
		{
			sleep(400);
			recargarTabla();
			
		});
	});
	
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
}

function recargarTabla()
{
	$.post('/NegocioRopa/Ventas', { "action": "recargarTabla" }, function(resultado)
	{
		$("#tablaProductos tr").remove();
		agregarEncabezado();
		agregarFilas(resultado);
		agregarFooter(resultado.importe);
	});
}

function agregarEncabezado()
{
	var trEncabezado = $("<tr />");
	$("#tablaProductos").append(trEncabezado);
	trEncabezado.append($('<td width="10%">ID</td>'));
	trEncabezado.append($('<td width="50%">Descripcion</td>'));
	trEncabezado.append($('<td width="30%">Precio</td>'));
}

function agregarFooter(importe)
{
	var trFooter = $("<tr />");
	$("#tablaProductos").append(trFooter);
	trFooter.append($('<td style="background-color: #C0C0C0;"><input id="agregar" class="botones" type="button" value="Agregar +"></td>'));
	trFooter.append($('<td style="background-color: #C0C0C0;"><input id="txtID" type="text" placeholder="Codigo Producto"></td>'));
	trFooter.append($('<td align="right" style="background-color: #C0C0C0;"><b id="total">Total: '+importe+'</b></td>'));
}

function agregarFilas(resultado)
{
	for (var i = 0; i < resultado.productos.length; i++)
	{
		var trFilas = $("<tr />");
		$("#tablaProductos").append(trFilas);
		trFilas.append($('<td align="center">'+ resultado.productos[i].id +'</td>'));
		trFilas.append($('<td>'+ resultado.productos[i].descripcion +'</td>'));
		trFilas.append($('<td>'+ resultado.productos[i].precio +'</td>'));
	}
}

function cargarComboClientes()
{
	$.post('/NegocioRopa/ABMClientes', { "action": "recargarTabla" }, function(resultado)
	{
		var clientes = [];
		jQuery.each(resultado.clientes, function()
		{
			clientes.push({id: this.id, text: this.nombreApellido});
		});
		$("#comboClientes").select2(
		{
		  data: clientes
		});
	});
}
