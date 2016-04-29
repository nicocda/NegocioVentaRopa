$(document).ready(function()
{	
	eventosRelacionados();
	
	
});

function eventosRelacionados()
{
	//a postData (método del js custom que se llama Ajax) le paso url y data para que muestre el mensaje.
	$("#btnAceptar").click(function()
	{
		$.postData('/NegocioRopa/ABMProducto', { "id": $("#txtID").val(), "descripcion": $("#txtDescripcion").val(), 
			"precio": $("#txtPrecio").val(), "action": "alta" , "tipo": $("#cbTipo").val(), 
			"subTipo": $("#cbSubTipo").val()});
		buscarId();
		$("#txtDescripcion").val("");
		$("#txtPrecio").val("");
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
	
	$(".btnEditar").click(function(){
		var row = $(this).closest("tr");
		$("#txtID").val(row.find(".idTabla").text());
		$("#txtDescripcion").val(row.find(".descripcionTabla").text());
		$("#txtPrecio").val(row.find(".precioTabla").text());
		$("#accordion #nuevoEditar").click();
	});
	
	$("#btnRestaurar").click(function(){
		buscarId();
		$("#txtDescripcion").val("");
		$("#txtPrecio").val("");
	});
}

function buscarId()
{
	$.post('/NegocioRopa/ABMProducto', {"action": "buscarId", "tipo": $("#cbTipo").val(), 
		"subTipo": $("#cbSubTipo").val()}, function(result){
			$("#txtID").val(result.id);
		});	
}
