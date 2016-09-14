$(document).ready(function()
{	
	eventosRelacionados();
	inicializarDatePicker();
	cargarComboProductos();
});

function cargarComboProductos()
{
	$.post('/NegocioRopa/ABMProductos', { "action": "recargarCombo" }, function(resultado)
	{
		var productos = [];
		
		jQuery.each(resultado, function()
		{
			productos.push({id: this.id, text: this.descripcion});
		});
		
		$('#productos').select2(
		{
			placeholder: 'Seleccione un producto',
			data: productos
		});
		
	});
}

function inicializarDatePicker()
{
	$("#fechaDesde").datepicker();
	$("#fechaHasta").datepicker();
}

function eventosRelacionados()
{
	$("#fechaDesde").change(function()
	{
		actualizarDiferenciaDias();
	});
	
	$("#fechaHasta").change(function()
	{
		actualizarDiferenciaDias();
	});
}

function actualizarDiferenciaDias()
{
	var fechaDesde = $("#fechaDesde").datepicker("getDate");
	var fechaHasta = $("#fechaHasta").datepicker("getDate");

	if (fechaDesde && fechaHasta && fechaDesde < fechaHasta)
	{
		var timeDiff = Math.abs(fechaHasta.getTime() - fechaDesde.getTime());
		var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
		
		$("#diferenciaDias").text(diffDays + " días con el nuevo precio.");
	}
	else
		$("#diferenciaDias").text("Error al ingresar fecha");
}