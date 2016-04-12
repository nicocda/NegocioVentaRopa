$(document).ready(function()
{	
	//a postData (método del js custom que se llama Ajax) le paso url y data para que muestre el mensaje.
	$("#btnAceptar").click(function()
	{
		$.postData('/NegocioRopa/ABMProducto', { "id": $("#txtID").val(), "descripcion": $("#txtDescripcion").val(), 
			"precio": $("#txtPrecio").val() });
	});
	
	//Con on agrego de forma dinámica el evento.
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
});
