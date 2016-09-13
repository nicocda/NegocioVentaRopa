$(document).ready(function()
{
	agregarEventos()
	cargarComboClientes();
	cargarTabla(function()
			{
				inicioPopUpCli();
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
					else if($("input[type='radio'][name='tipoPago']:checked").val() === "2")
					{
						datos=  {
								"action":"realizarVenta", 
								"idCliente": $("#comboClientes").val(), 
								"formaPago": $("input[type='radio'][name='tipoPago']:checked").val(),
								"paga" : $("#txtPaga").val()
						}
					}
					else
						datos=  {
									"action":"realizarVenta", 
									"idCliente": $("#comboClientes").val(), 
									"formaPago": $("input[type='radio'][name='tipoPago']:checked").val()
									};
					$.postData('/NegocioRopa/Ventas', 
					datos, 
					function(result)
					{
						$($("#divConfirmacion")).dialog('close');
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
function inicioPopUpCli()
{
	$("#addCliente").dialog(
			{
				autoOpen: false,
				modal: true,
				
				buttons: {
						 	 Aceptar: function()
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
									$("#addCliente").dialog('close');
								});
								
						},
							Cancelar : function()
							{
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
		//Evito que se ejecute el post del form.
		e.preventDefault();
		$.post('/NegocioRopa/ABMProductos',
				{
					"action": "buscarProducto",
					"idProducto": $("#comboProductos").val()
				},
				function(resultado)
				{	
					if(resultado.estado === 0)
					{
						$("#divConfirmacionAgregar").dialog("open");
					}
					else
					{
						agregarProducto();
					}	
				});
	});
	

	
	$("#radioTarjeta").change(function()
		{
			if($("#radioTarjeta").val() == 3)
			{
				$(".tarjeta").show();
				$("#txtNroTarjetaTrj").focus();
			}
			else
			{
			$(".tarjeta").hide();
			}
		});
	
	$("#radioCtaCte").change(function(){
		if($("#radioCtaCte").val() == 2)
		{
			$("#divCtaCte").show();
		}
		else
		{
			$("#divCtaCte").hide();
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
		$("#addCliente").dialog("open");
		$("#txtID").empty();
		$("#txtNombre").empty();
		$("#txtApellido").empty();
		$("#txtDireccion").empty();
		$("#txtTelefono").empty();
	});
	
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
	$("#pswSeg").keyup(function()
	{
		$.post('/NegocioRopa/Ventas',{"action": "validaPass", "pswSeg" : $("#pswSeg").val()}, function(resultado)
		{
			if(resultado.estado === "true")
			{
				$("#realizarVenta").removeAttr("disabled");
				$("#pswResp").text("");
			}
			else
			{
				if($("#pswSeg").val().length >= 4)
				$("#pswResp").text("La contraseña no es correcta");
				$("#realizarVenta").attr("disabled", true);
			}
		})
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


function actualizarTotal(){
	$.post('/NegocioRopa/Ventas', { "action": "actualizarTotal" }, function(resultado)
			{
				$("#total").text("Total: $"+resultado.tot)
				var importe = resultado.tot;
				if(resultado.tot < 0)
					{
						$("#realizarVenta").attr("disabled", true);
						$("#divPswSeg").show();
					} else {
						$("#realizarVenta").removeAttr("disabled");
						$("#divPswSeg").hide();
					}
			});
}

function cargarTabla(callback){
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
			 {data: null, defaultContent: "<button type='button' class='btn btn-danger borrarProducto'>X</button>", bsortable: false},
			 {data: "estado", visible:false}
		]
        
    } );
	
	callback();
} 


function agregarProducto()
{
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
}

function eventosTabla()
{
	$('#tablaVentas tbody').on( 'click', '.borrarProducto', function(e) 
			{
				e.preventDefault();
				var data = $("#tablaVentas").DataTable().row($(this).closest('tr')).data();
				$.post('/NegocioRopa/Ventas',{"action":"borrarProducto", "idProducto": data.id}, function(resultado)
				{
					actualizarTotal();
					$("#tablaVentas").DataTable().ajax.reload();
				});
			});
}
/*function validarRows()
{

	var table=$("#tablaVentas").DataTable();
	table.rows().iterator( 'row', function ( context, index ) {
		if(this.row(index.node() ).data() == "VENDIDO")
	    $( this.row( index ).node() ).addClass( 'devolucion' );
	} );}
*/

