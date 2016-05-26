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
	});
	
	$("#btnCancelarCreate").click(function(){
		limpiarCampos();
		$("#divPrincipal").show();
		$("#divCrearCliente").hide();
	});
}

function eventosRelacionados()
{
	$(document).on('click', "#btnGuardarCreate", function()
	{
		$.postData('/NegocioRopa/ABMClientes', { "id": $("#txtID").val(), "nombreApellido": $("#txtNombre").val(), 
			"direccion": $("#txtDireccion").val(), "telefono": $("#txtTelefono").val(), "action": "agregarCliente" }, 
			function()
			{
				$("#divPrincipal").show();
				$("#divCrearCliente").hide();
			});
	});
			
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
	$(document).on('click', ".btnEditar", function(){
		var row = $(this).closest("tr");
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Editar Cliente:");
		$("#txtID").css("display", "inline");
		$("#txtID").val(row.find(".idTabla").text());
		$("#txtNombreYApellido").val(row.find(".nyaTabla").text());
		$("#txtDireccion").val(row.find(".direTabla").text());
		$("#txtTelefono").val(row.find(".telTabla").text());
		$("#accordion #nuevoEditar").click();
	});
	
	$("#btnRestaurar").click(function(){
		limpiarCampos();
		$("#nuevoEditar").empty();
		$("#nuevoEditar").append("Nuevo Cliente:");
		$("#txtID").css("display", "none");
	});
	
	$(document).on('click', ".btnDeuda", function()
	{
		var row = $(this).closest("tr");
		var id= row.find(".idTabla").text();
		$("#accordion").hide();
		$("#divDeudas").show();
		recargarTablaVenta(id);
	
	});
	
	$("#btnVolverDeDeudas").click(function()
	{
		$('#h4Cliente').empty();
		$('#h4Cliente').append("Clientes");
		$("#divDeudas").hide();
		$("#accordion").show();
	});
}

function recargarTablaVenta(id)
{
	$.post('/NegocioRopa/Deudas', {"action": "detalleDeuda", "idCliente": id},
			function(result){
		$('#h4Cliente').empty();
		$('#h4Cliente').append("Clientes/Deudas de: " + result.cliente);
		$("#tablaVentasMorosas tr").remove();
		agregarEncabezadoVenta();
		agregarFilasVenta(result);
	});
}

function agregarEncabezadoVenta()
{
	var trEncabezado = $("<tr />");
	$("#tablaVentasMorosas").append(trEncabezado);
	trEncabezado.append($('<td width="20%">ID</td>'));
	trEncabezado.append($('<td width="40%">Fecha de Venta</td>'));
	trEncabezado.append($('<td width="20%">Importe de Venta</td>'));
	trEncabezado.append($('<td width="10%">Deuda Pendiente</td>'));

}

function agregarFilasVenta(resultado)
{
	var total=0;
	if(jQuery.isEmptyObject(resultado))
	{
		var trUsche = $("<tr />");
		$("#tablaVentasMorosas").append(trUsche);
		trUsche.append($('<td style="color: red;" colspan="4" align="center"> Este cliente no tiene deudas. </td>'));
	}
	else
	{
		for (var i = 0; i < resultado.ventas.length; i++)
		{
			total+=parseFloat(resultado.ventas[i].deuda);
			var trFilas = $("<tr />");
			$("#tablaVentasMorosas").append(trFilas);
			trFilas.append($('<td class="idVenta">'+ resultado.ventas[i].Id +'</td>'));
			trFilas.append($('<td class="fechaVenta">'+ resultado.ventas[i].fechaVenta +'</td>'));
			trFilas.append($('<td class="importeVenta">'+ resultado.ventas[i].importeTotal +'</td>'));
			trFilas.append($('<td class="deudaVenta">'+ resultado.ventas[i].deuda +'</td>'));
		}
		var trFooter = $("<tr />");
		$("#tablaVentasMorosas").append(trFooter);
		trFooter.append($('<td style="background-color: #252932; color: white;" colspan="4" align="right"> Deuda total: '+total.toFixed(1)+'</td>'));
	}
}





function recargarTabla()
{	
	$.post('/NegocioRopa/ABMClientes', { "action": "recargarTabla" }, function(resultado){
		$("#tablaClientes tr").remove();
		agregarEncabezado();
		agregarFilas(resultado);
	});
}

function agregarEncabezado()
{
	var trEncabezado = $("<tr />");
	$("#tablaClientes").append(trEncabezado);
	trEncabezado.append($('<td width="10%">ID</td>'));
	trEncabezado.append($('<td width="40%">Nombre y Apellido</td>'));
	trEncabezado.append($('<td width="20%">Direcci&#243;n</td>'));
	trEncabezado.append($('<td width="20%">Tel&#233;fono</td>'));
	trEncabezado.append($('<td width="10%"></td>'));
}

function agregarFilas(resultado)
{
	for (var i = 0; i < resultado.clientes.length; i++)
	{
		var trFilas = $("<tr />");
		$("#tablaClientes").append(trFilas);
		trFilas.append($('<td  id="idCliente" align="center" class="idTabla">'+ resultado.clientes[i].id +'</td>'));
		trFilas.append($('<td class="nyaTabla">'+ resultado.clientes[i].nombreApellido +'</td>'));
		trFilas.append($('<td class="direTabla">'+ resultado.clientes[i].direccion +'</td>'));
		trFilas.append($('<td class="telTabla">'+ resultado.clientes[i].telefono +'</td>'));
		trFilas.append($('<td align="center"><input type="button" class="botones btnEditar" value="Editar"></td>'));

	}
}

function sleep(miliseconds) 
{
   var currentTime = new Date().getTime();
   while (currentTime + miliseconds >= new Date().getTime()) 
   {
   }
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
		        }
			});
}
