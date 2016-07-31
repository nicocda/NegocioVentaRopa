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
		
		var val = $("#cbTipo").val();
		if(val == "M" || val == "B")
		{
			$("#cbSubTipo").val("0");
			$("#cbSubTipo").hide();
		}
		else
		{
			$("#cbSubTipo").show();
			$("#cbSubTipo").val("H");
		}
		buscarId();
		
	});
	
	$("#cbSubTipo").change(function()
	{
		buscarId();
	});
	
	$("#cbTipo2").change(function()
	{
		var val = $("#cbTipo2").val();
		if(val == "M" || val == "B")
		{
			$("#cbSubTipo2").val("0");
			$("#cbSubTipo2").hide();
		}
		else
		{
			$("#cbSubTipo2").show();
			$("#cbSubTipo2").val("H");
		}
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

///TODO TESTEAR ESTO
	
	
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
			$("#divPrintBarCode").show();
			$("#divCrearProducto").hide();
			$("#idBarcode1").text($("#txtID").val());
			$("#codNoBarcode1").text($("#txtID").val());
			$("#precio1").text($("#txtPrecio").val());
			
			$("#idBarcode2").text($("#txtID2").val());
			$("#codNoBarcode2").text($("#txtID2").val());
			$("#precio2").text($("#txtPrecio2").val());
			window.print();
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
		$("#divBarcode").dialog("close");
		$("#solo1").hide();
		var data = $("#tablaProductos").DataTable().row($(this).closest('tr')).data();
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
	
	
	$("#btnMostrarCreate").click(function(){
		$("#solo1").show();
	});
	
	$("#solo1").click(function()
	{
		if($("#solo1").html() == "Agregar solo 1 producto")
		{
			$("#divProducto2").hide();
			$("#solo1").html('Agregar 2 productos');
		}
		else if($("#solo1").html() == "Agregar 2 productos")
		{
			$("#divProducto2").show();
			$("#solo1").html('Agregar solo 1 producto');
		}
	});
	
	
	$('#tablaProductos tbody').on( 'click', '.btnBarcode', function() 
	{
		var data = $("#tablaProductos").DataTable().row($(this).closest('tr')).data();
		$("#divBarcode").dialog("open");
		
		$("#idBarcode").text(data.id);
		$("#codNoBarcode").text(data.id);
		$("#precio").text(data.precio);
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
    	params: function () { return { "action": "recargarTabla" } },
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