$(document).ready(function()
{
	agregarEventos()
	cargarComboClientes();
	cargarComboProductos();
	cargarTabla(function()
			{
				inicioPopUpConf();
				eventosTabla();
			});
});

function inicioPopUpConf()
{
		$("#divConfirmacion").dialog({
			resizable: false,
			height: "auto",
		     width: 400,
			autoOpen: false,
			modal: true,
			buttons: {
				Aceptar: function()
				{
					$.postData('/NegocioRopa/Condicional', 
							{
						"action":"registrarCondicional", 
						"idCliente": $("#comboClientes").val()
						
						}, 
					function(result)
					{
						$("#divConfirmacion").dialog('close');
						$("#tablaVentas").DataTable().clear().draw();
					
					});
					
				},
				Cancelar: function()
					{
						$(this).dialog('close');
					}
			}
		});
		$("#divConfirmacionAgregar").dialog({
			resizable: false,
			height: "auto",
		     width: 400,
			autoOpen: false,
			modal: true,
			buttons: {
					Aceptar: function()
					{
						agregarProducto(),
						$(this).dialog('close');
					},
					Cancelar: function() {
						$(this).dialog('close');
					}
			}
		});
}




function agregarEventos() 
{   
	$.fn.dataTable.ext.errMode = 'none';
	$("#agregar").click(function(e)
	{
		e.preventDefault();
		$.post('/NegocioRopa/ABMProductos',
				{
					"action": "buscarProducto",
					"idProducto": $("#comboProductos").val()
				},
				function(resultado)
				{	
					if(resultado.estado ===  3)
					{
						$("#divConfirmacionAgregar").dialog("open");
					}
					else
					{
						agregarProducto();
					}	
				});
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
	});
}
function agregarProducto()
{
	$.postDataSinExito('/NegocioRopa/Condicional',
			{
				"action": "agregarProducto",
				"id": $("#comboProductos").val()
			},
			function()
			{
				$("#tablaVentas").DataTable().ajax.reload();
				actualizarTotal();
			});
}
function cargarComboProductos()
{
	$.post('/NegocioRopa/Condicional', { "action": "recargarCombo" }, function(resultado)
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


function actualizarTotal(){
	$.post('/NegocioRopa/Condicional', { "action": "actualizarTotal" }, function(resultado)
			{
				$("#total").text(resultado.tot)
			});
}

function cargarTabla(callback){
	$("#tablaVentas").DataTable(
	{
        info: false,
        paginate: false,
        searching: false,
   	 	scrollY:"200px",
    	url: "/NegocioRopa/Condicional",
    	params: function () { return { "action": "recargarTabla" } },
		columns: 
		[
			 {data: "id"},
			 {data: "descripcion", bSortable: false},
			 {data: "precio", bSortable: false},
			 {data: null, defaultContent: "<button type='button' class='btn btn-danger borrarProducto'>X</button>", bsortable: false},
			 {data: "estado", visible:false, bSortable: false}
		]
        
    } );
	
	callback();
} 


function eventosTabla()
{
	$('#tablaVentas tbody').on( 'click', '.borrarProducto', function(e) 
			{
				e.preventDefault();
				var data = $("#tablaVentas").DataTable().row($(this).closest('tr')).data();
				$.post('/NegocioRopa/Condicional',{"action":"borrarProducto", "idProducto": data.id}, function(resultado)
				{
					$("#tablaVentas").DataTable().ajax.reload();
					actualizarTotal();
				});
			});
}

