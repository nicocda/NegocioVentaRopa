$(document).ready(function()
{	
	$("#btnAceptar").click(function()
	{
		$.postData('/NegocioRopa/ABMCliente', { "id": $("#txtID").val(), "nombreApellido": $("#txtNombreYApellido").val(), 
			"direccion": $("#txtDireccion").val(), "telefono": $("#txtTelefono").val(), "action": "agregarCliente" });
		sleep(200);
		recargarTabla();
		$("#accordion #mostrar").click();
		limpiarCampos();
	});
	
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
	$(document).on('click', ".btnEditar", function(){
		var row = $(this).closest("tr");
		$("#txtID").val(row.find(".idTabla").text());
		$("#txtNombreYApellido").val(row.find(".nyaTabla").text());
		$("#txtDireccion").val(row.find(".direTabla").text());
		$("#txtTelefono").val(row.find(".telTabla").text());
		$("#accordion #nuevoEditar").click();
	});
	
});

function recargarTabla()
{
	$.post('/NegocioRopa/ABMCliente', { "action": "recargarTabla" }, function(resultado){
		$("#tablaClientes tr").remove();
		agregarEncabezado();
		agregarFilas(resultado);
	});
}

function agregarEncabezado()
{
	var trEncabezado = $("<tr />");
	$("#tablaClientes").append(trEncabezado);
	trEncabezado.append($('<td width="10%">ID</td>'));
	trEncabezado.append($('<td width="40%">Nombre y Apellido</td>'));
	trEncabezado.append($('<td width="20%">Direcci&#243;n</td>'));
	trEncabezado.append($('<td width="20%">Tel&#233;fono</td>'));
	trEncabezado.append($('<td width="10%"></td>'));
}

function agregarFilas(resultado)
{
	for (var i = 0; i < resultado.clientes.length; i++)
	{
		var trFilas = $("<tr />");
		$("#tablaClientes").append(trFilas);
		trFilas.append($('<td align="center" class="idTabla">'+ resultado.clientes[i].id +'</td>'));
		trFilas.append($('<td class="nyaTabla">'+ resultado.clientes[i].nombreApellido +'</td>'));
		trFilas.append($('<td class="direTabla">'+ resultado.clientes[i].direccion +'</td>'));
		trFilas.append($('<td class="telTabla">'+ resultado.clientes[i].telefono +'</td>'));
		trFilas.append($('<td align="center"><input type="button" class="botones btnEditar" value="Editar"></td>'));

	}
}

function sleep(miliseconds) 
{
   var currentTime = new Date().getTime();
   while (currentTime + miliseconds >= new Date().getTime()) 
   {
   }
}

function limpiarCampos()
{
	$("#txtID").val("");
	$("#txtNombreYApellido").val("");
	$("#txtDireccion").val("");
	$("#txtTelefono").val("");
}