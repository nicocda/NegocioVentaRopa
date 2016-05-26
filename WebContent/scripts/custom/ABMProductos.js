$(document).ready(function()
{	
	eventosRelacionados();
	cargarTabla();
	nuevosEventos();
});

function nuevosEventos()
{
	$(document).on('click', ".btnBarcode", function()
	{
		$("#divPrincipal").hide();
		$("#divBarcode").show();
	});
	
	$(document).on('click', "#btnVolverDeBarcode", function()
			{
				$("#divBarcode").hide();
				$("#divPrincipal").show();
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
				$("#nuevoEditar").empty();
				$("#nuevoEditar").append("Nuevo Producto:");
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
		var row = $(this).closest("tr");
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Editar Producto:");
		$("#txtID").val(row.find(".idTabla").text());
		$("#txtDescripcion").val(row.find(".descripcionTabla").text());
		$("#txtPrecio").val(row.find(".precioTabla").text());
		$("#accordion #nuevoEditar").click();
	});
	

	$(document).on("click", ".btnBarcode", function()
	{
		
		var row = $(this).closest("tr");
		$('#h4Productos').empty();
		$('#h4Productos').append("Codigo de barra");
		$("#divProductos").hide();
		$("#divBarcode").show();
		$("#idBarcode").empty();
		$("#idBarcode").append("<label  id=\"dialog\" class=\"barcodeFP\">"+row.find(".idTabla").text()+"</label>");
		$("#codNoBarcode").empty();
		$("#codNoBarcode").append(row.find(".idTabla").text());
		$("#descBarcode").empty();
		$("#descBarcode").append(row.find(".descripcionTabla").text());
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

function recargarTabla()
{	
	$.post('/NegocioRopa/ABMProductos', { "action": "recargarTabla" }, function(resultado){
		$("#tablaProductos tr").remove();
		agregarEncabezado();
		agregarFilas(resultado);
	});
}

function agregarEncabezado()
{
	var trEncabezado = $("<tr />");
	$("#tablaProductos").append(trEncabezado);
	trEncabezado.append($('<td width="20%">ID</td>'));
	trEncabezado.append($('<td width="40%">Descripci&#243;n</td>'));
	trEncabezado.append($('<td width="20%">Estado</td>'));
	trEncabezado.append($('<td width="10%">Precio</td>'));
	trEncabezado.append($('<td width="10%"></td>'));
}

function agregarFilas(resultado)
{
	for (var i = 0; i < resultado.productos.length; i++)
	{
		var trFilas = $("<tr />");
		$("#tablaProductos").append(trFilas);
		trFilas.append($('<td align="center" class="idTabla">'+ resultado.productos[i].id +'</td>'));
		trFilas.append($('<td class="descripcionTabla">'+ resultado.productos[i].descripcion +'</td>'));
		trFilas.append($('<td class="estadoTabla">'+ resultado.productos[i].estado +'</td>'));
		trFilas.append($('<td class="precioTabla">'+ resultado.productos[i].precio +'</td>'));
		trFilas.append($('<td><input type="button" class="botones btnEditar" value="Editar"> <input type="button" id="agregar" class="botones barcode" value="C&#243;digo"></td>'));

	}
}

function cargarTabla()
{
	$("#tablaProductos").DataTable(
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
        columnDefs: [{ defaultContent: '<input type="button" class="botones btnEditar" value="Editar">' }],
        bLengthChange: false,
        ajax: 
    	{
        	type: "POST",
        	url: "/NegocioRopa/ABMProductos",
        	data: { "action": "recargarTabla" }
    	},
    	columns: 
		[
         {"data": "id"},
         {"data": "descripcion"},
         {"data": "precio"},
         {"data": "estado"},
         {"data": null, "targets": -1, "defaultContent": "<button class='btn btn-info btnEditar'>Editar</button>"},
         {"data": null, "targets": -1, "defaultContent": "<button class='btn btn-info btnBarcode'>Barcode</button>"}
        ]
	});
}
