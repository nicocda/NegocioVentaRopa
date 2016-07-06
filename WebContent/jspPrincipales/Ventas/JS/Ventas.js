$(document).ready(function()
{
	agregarEventos();
	cargarComboClientes();
	cargarComboProductos();
	cargarTabla();
});

function agregarEventos() 
{   
	$("#agregar").click(function(e)
	{
		//Evito que se ejecute el post del form.
		e.preventDefault();
		
		$.postDataSinExito('/NegocioRopa/Ventas',
		{
			"action": "agregarProducto",
			"id": $("#comboProductos").val()
		},
		function()
		{
			$("#tablaVentas").DataTable().ajax.reload();
		});
	});
	
	$("#realizarVenta").click(function()
	{
		$.postData('/NegocioRopa/Ventas', 
		{
			"action":"realizarVenta", 
			"idCliente": $("#comboClientes").val(), 
			"formaPago": $("#rdbtnTipoPago").val() 
		}, 
		function(result)
		{
			setTimeout(cargarTabla(), 3000);
		});
	});
	
	$("#addCli").click(function()
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
		$.postData('/NegocioRopa/ABMClientes',
		{
			"id": $("#txtID").val(),
			"nombre": $("#txtNombre").val(),
			"apellido": $("#txtApellido").val(), 
			"direccion": $("#txtDireccion").val(),
			"telefono": $("#txtTelefono").val(),
			"action": "agregarCliente"
		}, 
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

function cargarComboProductos()
{
	$.post('/NegocioRopa/ABMProductos', { "action": "recargarCombo" }, function(resultado)
	{
		var productos = [];
		jQuery.each(resultado.data, function()
		{
			productos.push({id: this.id, text: this.descripcion});
		});
		$('#comboProductos').select2(
		{
			placeholder: 'Seleccione una opcion',
			data: productos
		});
	});
}

function cargarTabla()
{
	$("#tablaVentas").DataTable(
	{
        info: false,
        paginate: false,
        searching: false,

    	url: "/NegocioRopa/Ventas",
    	params: {"action" : "recargarTabla" },
    	dataSrc: "productos",
    	
		columns: 
		[
			 {data: "id"},
			 {data: "descripcion", bSortable: false},
			 {data: "precio", bSortable: false},
			 {data: "estado", visible: false}
		]
        
    } );
} 
