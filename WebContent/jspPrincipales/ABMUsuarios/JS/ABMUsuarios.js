$(document).ready(function()
{	
	cargarTabla();
	eventosRelacionados();	
});

function limpiarCampos()
{
	$("#txtUsuario").val("");
	$("#txtNombreYApellido").val("");
	$("#txtEmail").val("");
	$("#txtPassword").val("");
	$("#cbTipo").val("");
	$("#txtPassword").prop("disabled", false);
}

function eventosRelacionados()
{
	$("#cbTipo").val(1);
	
	$("#btnMostrarCreate").click(function()
	{
		$("#divPrincipal").hide();
		$("#divCrearUsuario").show();
		$("#txtID").hide();
	});
	
	$("#btnCancelarCreate").click(function(){
		$("#divPrincipal").show();
		$("#divCrearUsuario").hide();
		limpiarCampos();
	});
	
	$("#tablaUsuarios tbody").on('click', ".btnEditar", function()
	{
		var data = $("#tablaUsuarios").DataTable().row($(this).closest('tr')).data();
		$("#divCrearUsuario").show();
		$("#divPrincipal").hide();
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Editar Usuario");
		
		$("#txtUsuario").val(data.usuario);
		$("#txtNombreYApellido").val(data.nombreYApellido);
		$("#txtEmail").val(data.email);
		$("#cbTipo").text(data.tipoUsuario);
		$("#txtPassword").val(data.password);
		$("#txtPassword").prop("disabled", true);
	});
	
	$("#tablaUsuarios tbody").on('click', ".btnEliminar", function()
	{
		var data = $("#tablaUsuarios").DataTable().row($(this).closest('tr')).data();
		var usuario = data.usuario;
		$.post('/NegocioRopa/ABMUsuarios', {"action": "eliminarUsuario", "usuario": usuario},
				function()
				{
					$("#tablaUsuarios").DataTable().ajax.reload();
				});
	});
	
	$("#btnGuardarCreate").click(function()
	{
		$.postData('/NegocioRopa/ABMUsuarios',
		{
			"usuario": $("#txtUsuario").val(),
			"nombreApellido": $("#txtNombreYApellido").val(),
			"email": $("#txtEmail").val(), "password": $("#txtPassword").val(),
			"tipo": $("#cbTipo").val(),
			"sucursal": $("#cbSucursal").val(),
			"action": "guardarUsuario"
		}, 
		function()
		{
			limpiarCampos();
			$("#tablaUsuarios").DataTable().ajax.reload();
			$("#nuevoEditar").empty();
			$("#nuevoEditar").append("Nuevo Usuario:");
			$("#divCrearUsuario").hide();
			$("#divPrincipal").show();
		});
	});
}

function cargarTabla()
{
	$("#tablaUsuarios").DataTable(
	{
        info: false,

    	url: "/NegocioRopa/ABMUsuarios",
    	params: function() { return { "action": "recargarTabla" } },
		columns: 
		[
			 {"data": "usuario"},
			 {"data": "nombreYApellido"},
			 {"data": "email"},
			 {"data": "tipoUsuario"},
			 {"data": null, "targets": -1, "defaultContent": "<button class='btn btn-info btnEditar'>Editar</button>"},
			 {"data": null, "targets": -1, "defaultContent": "<button class='btn btn-danger btnEliminar'>Eliminar</button>"},
	         
	    ]
	});
}