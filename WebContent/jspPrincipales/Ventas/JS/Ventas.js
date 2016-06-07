$(document).ready(function()
{
	agregarEventos();
	cargarComboClientes();
	cargarTabla();
});

function agregarEventos() 
{   
	$(document).on("click", "#agregar", function()
	{
		$.postDataSinExito('/NegocioRopa/Ventas', { "action": "agregarProducto" , "id": $("#txtID").val()}, function()
		{
			recargarTabla();
		});
	});
	
	$("#realizarVenta").click(function()
	{
		if($("#radioEfectivo").prop("checked"))
			var formaPago = 1;
		else if($("#radioCtaCte").prop("checked"))
			var formaPago = 2;
		else if($("#radioTarjeta").prop("checked"))
			var formaPago = 3;
		else 
			var formaPago = 0;
		$.postData('/NegocioRopa/Ventas', {"action":"realizarVenta", "idCliente": $("#comboClientes").val(), "formaPago": formaPago }, function(result)
		{
			setTimeout(recargarTabla(), 3000);
		});
	});
	
	$(document).on('click', '#addCli',function()
	{
		$("#addCliente").show();
		$("#txtID").empty();
		$("#txtNombre").empty();
		$("#txtApellido").empty();
		$("#txtDireccion").empty();
		$("#txtTelefono").empty();
	});
	
	$(document).on('click', '#btnCancelarCreate',function()
	{
		$("#addCliente").hide();
	});
	
	$(document).on('click', "#btnGuardarCreate", function()
			{
				$.postData('/NegocioRopa/ABMClientes', { "id": $("#txtID").val(), "nombre": $("#txtNombre").val(), "apellido": $("#txtApellido").val(), 
					"direccion": $("#txtDireccion").val(), "telefono": $("#txtTelefono").val(), "action": "agregarCliente" }, 
					function()
					{
						cargarComboClientes();
						$("#addCliente").hide();
					});
			});
	
	
	
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
	$("#radioEfectivo").click(function()
	{
		$("#divPaga").show();
		$('#lblVueltoDebe').empty();
		$('#lblVueltoDebe').append('Vuelto: ')
	});
	
	$("#radioCtaCte").click(function()
	{
		$("#divPaga").show();
		$('#lblVueltoDebe').empty();
		$('#lblVueltoDebe').append('Debe: ')
	});
	
	$("#radioTarjeta").click(function()
	{
		$("#divPaga").hide();
	});
}

function recargarTabla()
{
	
	$.post('/NegocioRopa/Ventas', { "action": "recargarTabla" }, function(resultado)
	{
		$("#tablaProductos tr").remove();
		agregarEncabezado();
		agregarFilas(resultado);
		agregarFooter(resultado.importe);
	});
}

function agregarEncabezado()
{
	var trEncabezado = $("<tr />");
	$("#tablaProductos").append(trEncabezado);
	trEncabezado.append($('<td width="10%">ID</td>'));
	trEncabezado.append($('<td width="50%">Descripcion</td>'));
	trEncabezado.append($('<td width="30%">Precio</td>'));
}

function agregarFooter(importe)
{
	var trFooter = $("<tr />");
	$("#tablaProductos").append(trFooter);
	trFooter.append($('<td style="background-color: #C0C0C0;"><input id="agregar" class="botones" type="button" value="Agregar +"></td>'));
	trFooter.append($('<td style="background-color: #C0C0C0;"><input id="txtID" type="text" placeholder="Codigo Producto"></td>'));
	trFooter.append($('<td align="right" style="background-color: #C0C0C0;"><b id="total">Total: '+importe+'</b></td>'));
}

function agregarFilas(resultado)
{
	for (var i = 0; i < resultado.productos.length; i++)
	{
		var trFilas = $("<tr />");
		$("#tablaProductos").append(trFilas);
		trFilas.append($('<td align="center">'+ resultado.productos[i].id +'</td>'));
		trFilas.append($('<td>'+ resultado.productos[i].descripcion +'</td>'));
		trFilas.append($('<td>'+ resultado.productos[i].precio +'</td>'));
	}
}

function cargarComboClientes()
{
	$.post('/NegocioRopa/ABMClientes', { "action": "recargarTabla" }, function(resultado)
	{
		var clientes = [];
		jQuery.each(resultado.data, function()
		{
			clientes.push({id: this.id, text: this.nombre+" "+this.apellido});
		});
		$('#comboClientes').select2(
		{
			placeholder: 'Seleccione una opcion',
			data: clientes
		});
	});
}

function cargarTabla()
{
	$("#tablaVentas").DataTable(
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
        bLengthChange: false,
		"ajax": 
    	{
        	"type": "POST",
        	"url": "/NegocioRopa/Ventas",
        	"data": function ( d ) {
        	      return $.extend( {}, d, 
        	{
	        	"action" : "recargarTabla", 
        	});
        	      },
        	"dataSrc": "ventas"
    	},
		columns: 
		[
			 {"data": "id"},
			 {"data": "descripcion"},
			 {"data": "precio"}
		]
	});
}
