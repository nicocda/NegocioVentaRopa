$(document).ready(function()
{	
	$("#btnAceptar").click(function()
	{
		$.postData('/NegocioRopa/ABMUsuarios', { "usuario": $("#txtUsuario").val(), "nombreApellido": $("#txtNombreYApellido").val(), 
			"email": $("#txtEmail").val(), "password": $("#txtPassword").val(), "tipo": $("#cbTipo").val(), "action": "guardarUsuario" }, 
			function()
			{
				recargarTabla();
				$("#accordion #mostrar").click();
				limpiarCampos();
				$("#nuevoEditar").empty();
				$("#nuevoEditar").append("Nuevo Usuario:");
			});
	});
	
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
	$(document).on('click', ".btnEditar", function(){
		var row = $(this).closest("tr");
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Editar Usuario:");
		$("#txtUsuario").val(row.find(".usuarioTabla").text());
		$("#txtNombreYApellido").val(row.find(".nyaTabla").text());
		$("#txtEmail").val(row.find(".emailTabla").text());
		$("#cbTipo  option:contains(" + row.find(".tipoTabla").text() + ")").attr('selected', 'selected');
		$("#accordion #nuevoEditar").click();
	});
	
	$("#btnRestaurar").click(function(){
		limpiarCampos();
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Nuevo Usuario:");
		$("#txtID").css("display", "none");
	});
});

function recargarTabla()
{
	$.post('/NegocioRopa/ABMUsuarios', { "action": "recargarTabla" }, function(resultado){
		$("#tablaUsuarios tr").remove();
		agregarEncabezado();
		agregarFilas(resultado);
	}),
	function(){
		alert("Oops...");
	};
}

function agregarEncabezado()
{
	var trEncabezado = $("<tr />");
	$("#tablaUsuarios").append(trEncabezado);
	trEncabezado.append($('<td width="10%">Usuario</td>'));
	trEncabezado.append($('<td width="40%">Nombre y Apellido</td>'));
	trEncabezado.append($('<td width="20%">Email</td>'));
	trEncabezado.append($('<td width="20%">Tipo</td>'));
	trEncabezado.append($('<td width="10%"></td>'));
}

function agregarFilas(resultado)
{
	for (var i = 0; i < resultado.usuarios.length; i++)
	{
		var trFilas = $("<tr />");
		$("#tablaUsuarios").append(trFilas);
		trFilas.append($('<td align="center" class="usuarioTabla">'+ resultado.usuarios[i].usuario +'</td>'));
		trFilas.append($('<td class="nyaTabla">'+ resultado.usuarios[i].nombreApellido +'</td>'));
		trFilas.append($('<td class="emailTabla">'+ resultado.usuarios[i].email +'</td>'));
		trFilas.append($('<td class="tipoTabla">'+ resultado.usuarios[i].tipo +'</td>'));
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
	$("#txtUsuario").val("");
	$("#txtNombreYApellido").val("");
	$("#txtEmail").val("");
	$("#txtPassword").val("");
	$("#cbTipo").val("");
}