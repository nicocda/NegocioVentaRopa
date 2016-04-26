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
							alert(result.mensaje);
						}
						else
						{
							agregarFilas(result);
						}
					});
				});
		
	}
	
	function agregarFilas(resultado)
	{
		var trFilas = $("<tr />");
		$("#tablaProductos").append(trFilas);
		trFilas.append($('<td align="center">'+ resultado.id +'</td>'));
		trFilas.append($('<td>'+ resultado.descripcion +'</td>'));
		trFilas.append($('<td>'+ resultado.precio +'</td>'));
		
		
	}