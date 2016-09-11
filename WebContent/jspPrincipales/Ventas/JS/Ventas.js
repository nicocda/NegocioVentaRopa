$(document).ready(function()
{
	agregarEventos();
	cargarComboClientes();
	cargarTabla();
	inicioPopUps();
	//validarRows();
});

function inicioPopUps()
{
		$("#divConfirmacion").dialog({
			autoOpen: false,
			modal: true,
			buttons:{
				Aceptar: function()
				{
					var datos = {};
					if ($("input[type='radio'][name='tipoPago']:checked").val() === "3")
						datos=  {
								"action":"realizarVenta", 
								"idCliente": $("#comboClientes").val(), 
								"formaPago": $("input[type='radio'][name='tipoPago']:checked").val(),
								"nroTarjetaTrj" : $("#txtNroTarjetaTrj").val(),
								"txtNombreTrj" : $("#txtNombreTrj").val(),
								"txtApellidoTrj" : $("#txtApellidoTrj").val(),
								"txtCuotasTrj" : $("#txtCuotasTrj").val(),
								"txtCuponTrj" : $("#txtCuponTrj").val(),
								"cbTipoTarjetaTrj" : $("#cbTipoTarjetaTrj").val(),
								}
					else datos=  {
								"action":"realizarVenta", 
								"idCliente": $("#comboClientes").val(), 
								"formaPago": $("input[type='radio'][name='tipoPago']:checked").val()
								};
						$.postData('/NegocioRopa/Ventas', 
						datos, 
						function(result)
						{
							$("#tablaVentas").DataTable().clear().draw();
						});
					$(this).dialog('close');
				},
				Cancelar: function()
					{
						$(this).dialog('close');
					}
			}
		});
		
		$("#addCliente").dialog(
				{
					autoOpen: false
				});
}

function agregarEventos() 
{   
	$.fn.dataTable.ext.errMode = 'none';
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
			actualizarTotal();
		});
		
	});
	$("#radioTarjeta").change(function()
		{
			if($("#radioTarjeta").val() == 3)
			{
				$(".tarjeta").show();
			}
		});
	$("#realizarVenta").click(function(){
		
		var totalRegistros = $("#tablaVentas").DataTable().page.info().recordsTotal;
		if(totalRegistros !== 0)
		{
			$("#divConfirmacion").dialog('open');
		}
		else
			alert("No hay ningun producto seleccionado");
		
		
	});
	
	$("#addCli").click(function()
	{
		$("#addCliente").dialog();
		$("#txtID").empty();
		$("#txtNombre").empty();
		$("#txtApellido").empty();
		$("#txtDireccion").empty();
		$("#txtTelefono").empty();
	});
	
	
	$(document).on('click', '#btnCancelarCreate',function()
	{
		$("#addCliente").dialog("close");
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
	
	
}

function cargarComboClientes()
{
	$.post('/NegocioRopa/ABMClientes', { "action": "recargarTabla" }, function(resultado)
	{
		var clientes = [];
		jQuery.each(resultado, function()
		{
			clientes.push({id: this.id, text: this.nombre+" "+this.apellido});
		});
		$('#comboClientes').select2(
		{
			placeholder: 'Seleccione un cliente',
			data: clientes
		});
		
		cargarComboProductos();
	});
}

function cargarComboProductos()
{
	$.post('/NegocioRopa/ABMProductos', { "action": "recargarCombo" }, function(resultado)
	{
		var productos = [];
		jQuery.each(resultado, function()
		{
			productos.push({id: this.id, text: this.descripcion});
		});
		$('#comboProductos').select2(
		{
			placeholder: 'Seleccione un producto',
			data: productos
		});
		
	});
}

$('#tablaVentas tbody').on( 'click', '.borrarProducto', function() 
		{
			var data = $("#tablaVentas").DataTable().row($(this).closest('tr')).data();
			$.post('/NegocioRopa/Ventas',{"action":"borrarProducto", "idProducto": "\""+data.id+"\""}, function(resultado)
			{
				cargarTabla();
			});
		});

function actualizarTotal(){
	$.post('/NegocioRopa/Ventas', { "action": "actualizarTotal" }, function(resultado)
			{
				$("#total").text("Total: $"+resultado.tot)
				if(resultado.tot < 0)
					{
						$("#realizarVenta").attr("disabled", true);
					} else {
						$("#realizarVenta").removeAttr("disabled");
					}
			});
}

function cargarTabla(){
	$("#tablaVentas").DataTable(
	{
        info: false,
        paginate: false,
        searching: false,
   	 	scrollY:"200px",
    	url: "/NegocioRopa/Ventas",
    	params: function () { return { "action": "recargarTabla" } },
		columns: 
		[
			 {data: "id"},
			 {data: "descripcion", bSortable: false},
			 {data: "precio", bSortable: false},
			 {data: "estado", visible:false},
			 {data: null, defaultContent: "<button class='btn btn-danger borrarProducto'>X</button>", bsortable: false}
		]
        
    } );
} 

/*function validarRows()
{

	var table=$("#tablaVentas").DataTable();
	table.rows().iterator( 'row', function ( context, index ) {
		if(this.row(index.node() ).data() == "VENDIDO")
	    $( this.row( index ).node() ).addClass( 'devolucion' );
	} );}
*/

