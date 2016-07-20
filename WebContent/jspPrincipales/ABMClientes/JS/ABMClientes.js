$(document).ready(function()
{	
	cargarTablaDeClientes();
	cargarTablaDeVentas();
	eventosRelacionados();
	eventosDeTabla();
});

function eventosRelacionados()
{
	$("#btnMostrarCreate").click(function()
	{
		$("#divPrincipal").hide();
		$("#divCrearCliente").show();
		$("#txtID").hide();
		eventosDeDetalle();
	});
}

function eventosDeDetalle()
{
	$("#btnGuardarCreate").click(function()
	{
		guardar();
	});
	
	$("#btnCancelarCreate").click(function()
	{
		$("#divPrincipal").show();
		$("#divCrearCliente").hide();
		limpiarCampos();
	});
}

function eventosDeDeuda()
{
	$("#btnVolverDeDeudas").click(function()
	{
		$("#divDeudas").hide();
		$("#divPrincipal").show();
	});
}

function eventosDeTabla()
{
	$("#tablaClientes tbody").on('click', ".btnDeuda", function()
	{
		var data = $("#tablaClientes").DataTable().row($(this).closest('tr')).data();
		$("#nombreDeuda").empty();
		$("#nombreDeuda").append(data.nombre + " " + data.apellido);
		$("#divPrincipal").hide();
		$("#divDeudas").show();
		$("#tablaVentasId").val(data.id);
		$("#tablaVentasMorosas").DataTable().ajax.reload();
		eventosDeDeuda();
	});
			
	$("#tablaClientes tbody").on('click', ".btnEditar", function()
	{
		var data = $("#tablaClientes").DataTable().row($(this).closest('tr')).data();
		$("#divCrearCliente").show();
		$("#divPrincipal").hide();
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Editar Cliente");
		
		$("#txtID").val(data.id);
		$("#txtNombre").val(data.nombre);
		$("#txtApellido").val(data.apellido);
		$("#txtDireccion").val(data.direccion);
		$("#txtTelefono").val(data.telefono);
		eventosDeDetalle();
	});
}

function limpiarCampos()
{
	$("#txtID").val("");
	$("#txtNombre").val("");
	$("#txtApellido").val("");
	$("#txtDireccion").val("");
	$("#txtTelefono").val("");
}

function cargarTablaDeClientes()
{
	$("#tablaClientes").DataTable(
	{
        columns: 
		[
	        {data: "id"},
	        {data: "nombre"},
	        {data: "apellido"},
	        {data: "direccion"},
	        {data: "telefono"},
	        {data: null, targets: -1, defaultContent: "<button class='btn btn-info btnDeuda'>Ver deuda</button>", bSortable: false},
	        {data: null, targets: -1, defaultContent: "<button class='btn btn-info btnEditar'>Editar</button>", bSortable: false}
        ],
    	url: "/NegocioRopa/ABMClientes",
    	params: { "action": "recargarTabla" },
	});
}

function cargarTablaDeVentas()
{
	$("#tablaVentasMorosas").DataTable(
	{
		info: false,
        paginate: false,
        searching: false,
    	url: "/NegocioRopa/Deudas",
    	params: { "idCliente": $("#tablaVentasId").val() },
    	width: 100,
		columns: 
		[
	        {"data": "Id"},
	        {"data": "fechaVenta"},
	        {"data": "importeTotal", bSortable: false},
	        {"data": "deuda", bSortable: false}
        ]
	});
}

function guardar()
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
		$("#divPrincipal").show();
		$("#divCrearCliente").hide();
		$("#tablaClientes").DataTable().ajax.reload();		
		limpiarCampos();
	});
}
