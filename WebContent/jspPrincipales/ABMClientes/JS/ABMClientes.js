$(document).ready(function()
{	
	cargarTabla();
	eventosRelacionados();
});

function eventosRelacionados()
{
	$("#btnMostrarCreate").click(function(){
		$("#divPrincipal").hide();
		$("#divCrearCliente").show();
		$("#txtID").hide();
	});
	
	$("#btnCancelarCreate").click(function(){
		$("#divPrincipal").show();
		$("#divCrearCliente").hide();
		limpiarCampos();
	});
	
	$(document).on('click', "#btnGuardarCreate", function()
	{
		$.postData('/NegocioRopa/ABMClientes', { "id": $("#txtID").val(), "nombre": $("#txtNombre").val(), "apellido": $("#txtApellido").val(), 
			"direccion": $("#txtDireccion").val(), "telefono": $("#txtTelefono").val(), "action": "agregarCliente" }, 
			function()
			{
				$("#divPrincipal").show();
				$("#divCrearCliente").hide();
				$("#tablaClientes").DataTable().ajax.reload();		
				limpiarCampos();
			});
	});
	
	$("#btnVolverDeDeudas").click(function()
	{
		$("#divDeudas").hide();
		$("#divPrincipal").show();
	});
	
	$("#tablaClientes tbody").on('click', ".btnDeuda", function()
	{
		var data = $("#tablaClientes").DataTable().row($(this).closest('tr').index()).data();
		$("#nombreDeuda").empty();
		$("#nombreDeuda").append(data.nombre + " " + data.apellido);
		$("#divPrincipal").hide();
		$("#divDeudas").show();
		$("#tablaVentasId").val(data.id);
		$("#tablaVentasMorosas").DataTable().ajax.reload();
	});
	
	$("#tablaClientes tbody").on('click', ".btnEditar", function()
	{
		var data = $("#tablaClientes").DataTable().row($(this).closest('tr').index()).data();
		$("#divCrearCliente").show();
		$("#divPrincipal").hide();
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Editar Cliente");
		
		$("#txtID").val(data.id);
		$("#txtNombre").val(data.nombre);
		$("#txtApellido").val(data.apellido);
		$("#txtDireccion").val(data.direccion);
		$("#txtTelefono").val(data.telefono);
	});
}

function limpiarCampos()
{
	$("#txtID").val("");
	$("#txtNombre").val("");
	$("#txtApellido").val("");
	$("#txtDireccion").val("");
	$("#txtTelefono").val("");
}

function cargarTabla()
{
	$("#tablaClientes").DataTable(
	{
		responsive: true,
		"language": {
            "lengthMenu": "Mostrar _MENU_ registros por pagina",
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando paginas _PAGE_ de _PAGES_",
            "infoEmpty": "No records available",
            "infoFiltered": "(filtered from _MAX_ total records)",
            "search": "Buscar:",
            "loadingRecords": "Cargando...",
            "processing": "Procesando...",
            "paginate": {
                "first":      "Primero",
                "last":       "Ultimo",
                "next":       "Siguiente",
                "previous":   "Anterior"
            }
        },
        aoColumnDefs: [ { 'bSortable': false, 'aTargets': [4, 5] } ],
        bLengthChange: false,
        ajax: 
    	{
        	type: "POST",
        	url: "/NegocioRopa/ABMClientes",
        	data: { "action": "recargarTabla" }
    	},
    	columns: 
		[
         {"data": "id"},
         {"data": "nombre"},
         {"data": "apellido"},
         {"data": "direccion"},
         {"data": "telefono"},
         {"data": null, "targets": -1, "defaultContent": "<button class='btn btn-info btnDeuda'>Ver deuda</button>"},
         {"data": null, "targets": -1, "defaultContent": "<button class='btn btn-info btnEditar'>Editar</button>"}
        ]
	});

	$("#tablaVentasMorosas").DataTable(
	{
		ajax: 
		{       	
	    	url: "/NegocioRopa/Deudas",
	    	type: "POST",
	    	data: function(d)
	    	{ 
	    		d.idCliente = $("#tablaVentasId").val();
	    	}
		},
		columns: 
		[
         {"data": "Id"},
         {"data": "fechaVenta"},
         {"data": "importeTotal"},
         {"data": "deuda"}
        ]
	});
}
