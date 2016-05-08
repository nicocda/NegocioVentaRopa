$(document).ready(function()
{	
	eventosRelacionados();
});

function eventosRelacionados()
{
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
