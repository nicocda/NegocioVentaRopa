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
			cargarTabla();
			$("#tablaVentas").DataTable().ajax.reload();
			
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
			setTimeout(cargarTabla(), 3000);
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
        bLengthChange: false,
        info: false,
        paginate: false,
        search: false,
        columnDefs: [{ defaultContent: "estado" }],
        "language": 
    	{
        	"lengthMenu": "Mostrar _MENU_ registros por pagina",
            "zeroRecords": "No hay productos cargados",
            "info": "Mostrando paginas _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registros disponibles",
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
		"ajax": 
    	{
        	"type": "POST",
        	"url": "/NegocioRopa/Ventas",
        	"data": function ( d ) {
        	      return $.extend( {}, d, 
        	{
	        	"action" : "recargarTabla" 
        	});
        	      },
        	"dataSrc": "productos"
    	},
		columns: 
		[
			 {"data": "id"},
			 {"data": "descripcion"},
			 {"data": "precio"},
			 {"data": "estado", "visible": false}
		]
        
    } );
} 
