$(document).ready(function()
{
	agregarEventos();
});

	function agregarEventos()
	{
		$("#agregar").click(function()
				{
					$.post('/NegocioRopa/Ventas', { "action": "agregarProducto" , "id": $("#txtID").val()}, function(result){
						if(result.error)
						{
							alert(result.mensajeError);
						}
						else
						{
							recargarTabla(result);
						}
					});
				});
		
		$("#realizarVenta").click(function()
				{
					if($("#radio1").checked())
						var formaPago = 1;
					if($("#radio2").checked())
						var formaPago = 2;
					if($("#radio3").checked())
						var formaPago = 3;
					$.post('/NegocioRopa/Ventas', {"action":"realizarVenta", "nombreCliente": $("#txtClientes").val(), "formaPago": formaPago }, function(result)
							{
								//Si es exito, el mensaje muestra un exito
								alert(result.mensajeError);
							
							});
				});
				
		
	}
	
	function recargarTabla(result)
	{
		$.post('/NegocioRopa/Ventas', { "action": "recargarTabla" }, function(resultado){
			$("#tablaProductos tr").remove();
			agregarEncabezado();
			agregarFilas(resultado);
			agregarFooter(result);
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
	
	function agregarFooter(resultado)
	{
		var trFooter = $("<tr />");
		$("#tablaProductos").append(trFooter);
		trFooter.append($('<td style="background-color: #C0C0C0;"><input id="agregar" class="botones" type="button" value="Agregar +"></td>'));
		trFooter.append($('<td style="background-color: #C0C0C0;"><input id="txtID" type="text" placeholder="Codigo Producto"></td>'));
		trFooter.append($('<td align="right" style="background-color: #C0C0C0;"><b id="total">Total: '+resultado.importe+'</b></td>'));
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
	