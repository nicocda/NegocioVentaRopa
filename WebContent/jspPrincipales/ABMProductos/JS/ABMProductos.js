$(document).ready(function()
{	
	cargarTabla();
	eventosRelacionados();
	eventosDeTabla();
	inicializarPopUp();
});

function eventosRelacionados()
{
	
	$("#btnVolverDeBarcode").click(function()
	{
		$("#divBarcode").dialog("close");
	});
	
	$("#btnMostrarCreate").click(function()
	{
		$("#divPrincipal").hide();
		$("#divCrearProducto").show();
		$("#tipos").show();
		$("#divProducto2").show();
		$("#txtDescripcion").focus();
		eventosDelDetalle();
	});
	
	$("#cbTipo").change(function()
	{
		buscarId();
	});
	
	$("#cbSubTipo").change(function()
	{
		buscarId();
	});
	
	$("#cbTipo2").change(function()
	{
		buscarId();
	});
			
	$("#cbSubTipo2").change(function()
	{
		buscarId();
	});
}

function eventosDelDetalle()
{
	$("#btnCancelarCreate").click(function()
	{
		$("#divPrincipal").show();
		$("#divCrearProducto").hide();
		$("#tablaProductos").DataTable().ajax.reload();
	});
	
	$("#btnRestaurar").click(function()
	{
		buscarId();
		$("#tipos").show();
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Nuevo Producto:");
		$("#txtDescripcion").val("");
		$("#txtPrecio").val("");
		$("#txtDescripcion2").val("");
		$("#txtPrecio2").val("");
		$("#divProducto2").show();
	});
	
	$("#solo1").click(function()
	{
		$("#divProducto2").hide();
	});
	
	$("#btnAceptar").click(function()
	{
		$.postData('/NegocioRopa/ABMProductos', 
		{ 
			"id": $("#txtID").val(),
			"descripcion": $("#txtDescripcion").val(),
			"precio": $("#txtPrecio").val(),
			"action": "alta",
			"tipo": $("#cbTipo").val(),
			"subTipo": $("#cbSubTipo").val(),
			"id2": $("#txtID2").val(),
			"descripcion2": $("#txtDescripcion2").val(),
			"precio2": $("#txtPrecio2").val(),
		},
		function()
		{ 
			buscarId();
			$("#txtDescripcion").val("");
			$("#txtPrecio").val("");
			$("#txtDescripcion2").val("");
			$("#txtPrecio2").val("");
			$("#divProducto2").show();
			$("#accordion #mostrar").click();
			$("#nuevoEditar").val("Nuevo Producto:");
			$("#txtDescripcion").focus();
			$("#tablaProductos").DataTable().ajax.reload();
		});
	});
}

function eventosDeTabla()
{
	$("#tablaProductos tbody").on("click", ".btnEditar", function()
	{
		var data = $("#tablaProductos").DataTable().row($(this).closest('tr').index()).data();
		$("#divPrincipal").hide();
		$("#divProducto2").hide();
		$("#divCrearProducto").show();
		$("#nuevoEditar").val("Editar Producto:");
		$("#tipos").hide();
		$("#txtID").val(data.id);
		$("#txtDescripcion").val(data.descripcion);
		$("#txtPrecio").val(data.precio);
		eventosDelDetalle();
	});
	
	$('#tablaProductos tbody').on( 'click', '.btnBarcode', function() 
	{
		var data = $("#tablaProductos").DataTable().row($(this).closest('tr').index()).data();
		$("#divBarcode").dialog("open");
		
		$("#idBarcode").text(data.id);
		$("#codNoBarcode").text(data.id);
		$("#descBarcode").text(data.descripcion);
    });
}

function buscarId()
{
	$.post('/NegocioRopa/ABMProductos', 
	{
		"action": "buscarId",
		"tipo": $("#cbTipo").val(),
		"subTipo": $("#cbSubTipo").val(),
		"tipo2": $("#cbTipo2").val(),
		"subTipo2": $("#cbSubTipo2").val()
	}, 
	function(result)
	{
		$("#txtID").val(result.id);
		$("#txtID2").val(result.id2);
	});	
}

function cargarTabla()
{
	$("#tablaProductos").DataTable(
	{
    	url: "/NegocioRopa/ABMProductos",
    	params: { "action": "recargarTabla" },
    	columns: 
		[
	        {data: "id"},
	        {data: "descripcion"},
	        {data: "precio"},
	        {data: "estado"},
	        {data: null, "targets": -1, defaultContent: "<button class='btn btn-info btnBarcode'>Barcode</button>", sortable: false},
	        {data: null, "targets": -1, defaultContent: "<button class='btn btn-info btnEditar'>Editar</button>", sortable: false}
        ]
	});
}

function inicializarPopUp()
{
	$("#divBarcode").dialog(
	{
		autoOpen: false
	});
}