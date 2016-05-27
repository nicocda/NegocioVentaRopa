$(document).ready(function()
{	
	eventosRelacionados();
	cargarTabla();
	nuevosEventos();
});

function nuevosEventos()
{
	$("#btnMostrarCreate").click(function(){
		$("#divPrincipal").hide();
		$("#divCrearCliente").show();
		$("#txtID").hide();
	});
	
	$("#btnCancelarCreate").click(function(){
		limpiarCampos();
		$("#divPrincipal").show();
		$("#divCrearCliente").hide();
	});
	
	$(document).on('click', "#btnGuardarCreate", function()
	{
		$.postData('/NegocioRopa/ABMClientes', { "id": $("#txtID").val(), "nombre": $("#txtNombre").val(), "apellido": $("#txtApellido").val(), 
			"direccion": $("#txtDireccion").val(), "telefono": $("#txtTelefono").val(), "action": "agregarCliente" }, 
			function()
			{
				$("#divPrincipal").show();
				$("#divCrearCliente").hide();
				$("#tablaClientes").DataTable().ajax.reload();
			});
	});
	
	$("#tablaClientes tbody").on('click', ".btnDeuda", function()
	{
		var data = $("#tablaClientes").DataTable().row($(this).closest('tr').index()).data();
		$("#divPrincipal").hide();
		$("#divDeudas").show();
		recargarTablaVenta(data.id);
		$("#tablaVentasMorosas").DataTable();
		$("#nombreDeuda").empty();
		$("#nombreDeuda").append(data.nombreApellido);
	});
			
}

function eventosRelacionados()
{
	$("#tablaClientes tbody").on('click', ".btnEditar", function()
	{
		var data = $("#tablaClientes").DataTable().row($(this).closest('tr').index()).data();
		$("#divCrearCliente").hide();
		$("#divPrincipal").show();
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Editar Cliente:");
		$("#txtID").show();
		$("#txtID").empty();
		$("#txtID").append(data.id);
		$("#txtNombre").empty();
		$("#txtNombre").append(data.nombreApellido);
		$("#txtApellido").empty();
		$("#txtDireccion").empty();
		$("#txtDireccion").append(data.direccion);
		$("#txtTelefono").empty();
		$("#txtTelefono").append(data.telefono);
	
	});


	$("#btnVolverDeDeudas").click(function()
	{
		$("#divDeudas").hide();
		$("#divPrincipal").show();
	});
}

function recargarTablaVenta(id)
{
	$.post('/NegocioRopa/Deudas', {"action": "detalleDeuda", "idCliente": id}, 
		function(result)
		{
			$("#tablaVentasMorosas tr").remove();
			agregarEncabezadoVenta();
			agregarFilasVenta(result);
		});
}

function agregarEncabezadoVenta()
{
	var theadEncabezado = $("<thead />");
	var trEncabezado = $("<tr />");
	$("#tablaVentasMorosas").append(theadEncabezado);
	theadEncabezado.append(trEncabezado);
	trEncabezado.append($('<th width="20%">ID</td>'));
	trEncabezado.append($('<th width="40%">Fecha de Venta</td>'));
	trEncabezado.append($('<th width="20%">Importe de Venta</td>'));
	trEncabezado.append($('<th width="10%">Deuda Pendiente</td>'));

}

function agregarFilasVenta(resultado)
{
	var total=0;
	var tbody = $("<tbody />");
	$("#tablaVentasMorosas").append(tbody);
	if(jQuery.isEmptyObject(resultado))
	{
		var trUsche = $("<tr />");
		tbody.append(trUsche);
		trUsche.append($('<td style="color: red;" colspan="4" align="center"> Este cliente no tiene deudas. </td>'));
	}
	else
	{
		for (var i = 0; i < resultado.ventas.length; i++)
		{
			total+=parseFloat(resultado.ventas[i].deuda);
			var trFilas = $("<tr />");
			tbody.append(trFilas);
			trFilas.append($('<td class="idVenta">'+ resultado.ventas[i].Id +'</td>'));
			trFilas.append($('<td class="fechaVenta">'+ resultado.ventas[i].fechaVenta +'</td>'));
			trFilas.append($('<td class="importeVenta">'+ resultado.ventas[i].importeTotal +'</td>'));
			trFilas.append($('<td class="deudaVenta">'+ resultado.ventas[i].deuda +'</td>'));
		}
	}
	var trFooter = $("<tr />");
	var tfooter = $("<tfoot />");
	tfooter.append(trFooter);
	$("#tablaVentasMorosas").append(tfooter);
	trFooter.append($('<td colspan="4" align="right"> Deuda total: '+total.toFixed(1)+'</td>'));
}


function limpiarCampos()
{
	$("#txtID").val("");
	$("#txtNombreYApellido").val("");
	$("#txtDireccion").val("");
	$("#txtTelefono").val("");
}

function cargarTabla()
{
	$("#tablaClientes").DataTable(
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
        columnDefs: [{ defaultContent: '<input type="button" class="btn btn-info btnEditar" value="Editar">' }],
        bLengthChange: false,
        ajax: 
    	{
        	type: "POST",
        	url: "/NegocioRopa/ABMClientes",
        	data: { "action": "recargarTabla" }
    	},
    	columns: 
		[
         {"data": "id"},
         {"data": "nombreApellido"},
         {"data": "direccion"},
         {"data": "telefono"},
         {"data": null, "targets": -1, "defaultContent": "<button class='btn btn-info btnDeuda'>Ver deuda</button>"},
         {"data": null, "targets": -1, "defaultContent": "<button class='btn btn-info btnEditar'>Editar</button>"}
        ]
	});
}
