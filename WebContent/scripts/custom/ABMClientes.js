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
	
	$(".btnEditar").click(function(){
		var row = $(this).closest("tr");
		$("#txtID").val(row.find(".idTabla").text());
		$("#txtNombreYApellido").val(row.find(".nyaTabla").text());
		$("#txtDireccion").val(row.find(".direTabla").text());
		$("#txtTelefono").val(row.find(".telTabla").text());
		$("#accordion #nuevoEditar").click();
	});
	
});