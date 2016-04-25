$(document).ready(function()
{
	$("agregar").click(function()
			{
				$.postData('/NegocioRopa/Ventas', { "id": $("#txtID").val(), "action": "agregarProducto" });
			});
	
});