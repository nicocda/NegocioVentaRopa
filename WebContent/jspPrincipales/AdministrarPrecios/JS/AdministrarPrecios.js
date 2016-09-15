$(document).ready(function()
{	
	var listaProductos = [];
	var productosElegidos = [];
	eventosRelacionados(productosElegidos, listaProductos);
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

function eventosRelacionados(productosElegidos, listaProductos)
{
	$("#fechaDesde").change(function()
	{
		actualizarDiferenciaDias();
	});
	
	$("#fechaHasta").change(function()
	{
		actualizarDiferenciaDias();
	});
	
	$("#agregarProducto").click(function()
	{
		var producto = $("#productos").val();
				
		//Si no está ya en el arreglo, lo agrego
		if ($.inArray(producto, productosElegidos) === -1)
		{
			productosElegidos.push(producto);
			
			$.post("/NegocioRopa/AdministrarPrecios", {"action": "buscarProducto", "producto": producto }, function(data)
			{
				listaProductos.push(data);
				recargarTabla(listaProductos);
			});
		}
	});
	
	$("#guardarPrecios").submit(function(event)
	{
		event.preventDefault();
		$.postData("/NegocioRopa/AdministrarPrecios", 
		{ 
			"action": "guardarPrecios", 
			"fechaInicio": $("#fechaDesde").datepicker("getDate"), 
			"fechaFin": $("#fechaHasta").datepicker("getDate"),
			"porcentaje": $("#porcentaje").val(),
			"productos": JSON.stringify(productosElegidos) 
		}, 
		function(data)
		{
			
		});
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

function recargarTabla(listaProductos)
{	
	$("#tablaPrecios tbody").empty();
	
	$.each(listaProductos, function()
	{
		var tr = $("<tr>");
		
		tr.append($("<td>").append(this.codigo));
		tr.append($("<td>").append(this.descripcion));
		tr.append($("<td>").append(this.precioActual));
		tr.append($("<td>").append(this.fechaCambio));
		tr.append($("<td>").append(""));

		$("#tablaPrecios tbody").append(tr);
	});
}