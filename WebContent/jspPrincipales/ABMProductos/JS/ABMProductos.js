$(document).ready(function()
{	
	eventosRelacionados();
	cargarTabla();
	nuevosEventos();
	$("#divBarcode").dialog({autoOpen: false});
});

function nuevosEventos()
{
	
	$(document).on('click', "#btnVolverDeBarcode", function()
	{
		$("#divBarcode").dialog("close");
	});
	
	$("#btnMostrarCreate").click(function(){
		$("#divPrincipal").hide();
		$("#divCrearProducto").show();
		$("#cbTipo").show();
		$("#cbSubTipo").show();
	});
	
	$(document).on('click', "#btnCancelarCreate", function()
	{
		$("#divPrincipal").show();
		$("#divCrearProducto").hide();
		$("#tablaProductos").DataTable().ajax.reload();
	});
}


function eventosRelacionados()
{
	$("#inputDescripcion").keyup(function(){
		$.post('/NegocioRopa/ABMProductos', { "action" : "buscar" , "valor": $("#inputDescripcion").val()}, function(resultado){
			$("#tablaProductos tr").remove();
			if(jQuery.isEmptyObject(resultado))
				{
				var trUsche = $("<tr />");
				$("#tablaProductos").append(trUsche);
				trUsche.append($('<td style="color: red;" colspan="4" align="center"> No existen productos. </td>'));
				}
			else
			{
				agregarEncabezado();
				agregarFilas(resultado);
			}
		
			
		});
	});
	//a postData (método del js custom que se llama Ajax) le paso url y data para que muestre el mensaje.
	$("#btnAceptar").click(function()
	{
		$.postData('/NegocioRopa/ABMProductos', { "id": $("#txtID").val(), "descripcion": $("#txtDescripcion").val(), 
			"precio": $("#txtPrecio").val(), "action": "alta" , "tipo": $("#cbTipo").val(), 
			"subTipo": $("#cbSubTipo").val()}, 
			function()
			{ 
				recargarTabla();
				buscarId();
				$("#txtDescripcion").val("");
				$("#txtPrecio").val("");
				$("#accordion #mostrar").click();
				$("#nuevoEditar").val("Nuevo Producto:");
			});
	});
	
	//Con on agrego de forma dinámica el evento.
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
	$("#cbTipo").change(function(){
		buscarId();
	});
	
	$("#cbSubTipo").change(function(){
		buscarId();
	});
	
	$(document).on("click", ".btnEditar", function()
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
	});
	
	
	$(document).on("click", "#btnVolverDeBarcode", function()
	{
		$('#h4Productos').empty();
		$('#h4Productos').append("Productos");
		$("#divBarcode").hide();
		$("#divProductos").show();
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
}

function buscarId()
{
	$.post('/NegocioRopa/ABMProductos', {"action": "buscarId", "tipo": $("#cbTipo").val(), 
		"subTipo": $("#cbSubTipo").val()}, function(result){
			$("#txtID").val(result.id);
		});	
}

function cargarTabla()
{
	var tablaProd = $("#tablaProductos").DataTable(
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
	
	$('#tablaProductos tbody').on( 'click', '.btnBarcode', function () 
	{
		var data = $("#tablaProductos").DataTable().row($(this).closest('tr').index()).data();
		$("#divBarcode").dialog("open");
		
		$("#idBarcode").text(data.id);
		$("#codNoBarcode").text(data.id);
		$("#descBarcode").text(data.descripcion);
    });
}
