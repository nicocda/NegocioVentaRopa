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
	$("#btnMostrarCreate").click(function(){
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
		var data = $("#tablaUsuarios").DataTable().row($(this).closest('tr').index()).data();
		$("#divCrearUsuario").show();
		$("#divPrincipal").hide();
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Editar Usuario");
		
		$("#txtUsuario").val(data.usuario);
		$("#txtNombreYApellido").val(data.nombreYApellido);
		$("#txtEmail").val(data.mail);
		$("#cbTipo").val(data.tipoUsuario);
		$("#txtPassword").val(data.password);
		$("#txtPassword").prop("disabled", true);
	});
	
	$("#btnGuardarCreate").click(function()
	{
		$.postData('/NegocioRopa/ABMUsuarios', { "usuario": $("#txtUsuario").val(), "nombreApellido": $("#txtNombreYApellido").val(), 
			"email": $("#txtEmail").val(), "password": $("#txtPassword").val(), "tipo": $("#cbTipo").val(), "action": "guardarUsuario" }, 
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
		ajax: 
    	{
        	type: "POST",
        	url: "/NegocioRopa/ABMUsuarios",
        	data: { "action": "recargarTabla" },
        	dataSrc: ""
    	},
		columns: 
		[
			 {"data": "usuario"},
			 {"data": "nombreYApellido"},
			 {"data": "mail"},
			 {"data": "tipoUsuario"},
			 {"data": null, "targets": -1, "defaultContent": "<button class='btn btn-info btnEditar'>Editar</button>"},
	         {"data": "password", "visible": false}
	    ]
	});
}