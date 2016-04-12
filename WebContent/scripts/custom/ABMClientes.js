$(document).ready(function()
{	
	$("#btnAceptar").click(function()
	{
		$.postData('/NegocioRopa/ABMCliente', { "id": $("#txtID").val(), "nombreApellido": $("#txtNombreYApellido").val(), 
			"direccion": $("#txtDireccion").val(), "telefono": $("#txtTelefono").val() });
	});
	
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
});