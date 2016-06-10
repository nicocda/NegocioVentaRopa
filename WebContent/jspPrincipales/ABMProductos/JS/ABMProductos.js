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
		$("#cbTipo").show();
		$("#cbSubTipo").show();
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
		$("#cbTipo").show();
		$("#cbSubTipo").show();
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Nuevo Producto:");
		$("#txtDescripcion").val("");
		$("#txtPrecio").val("");
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
			"subTipo": $("#cbSubTipo").val()
		},
		function()
		{ 
			buscarId();
			$("#txtDescripcion").val("");
			$("#txtPrecio").val("");
			$("#accordion #mostrar").click();
			$("#nuevoEditar").val("Nuevo Producto:");
			
			$("#divPrincipal").show();
			$("#divCrearProducto").hide();
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
		$("#divCrearProducto").show();
		$("#nuevoEditar").val("Editar Producto:");
		$("#cbTipo").hide();
		$("#cbSubTipo").hide();
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
		"subTipo": $("#cbSubTipo").val()
	}, 
	function(result)
	{
		$("#txtID").val(result.id);
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