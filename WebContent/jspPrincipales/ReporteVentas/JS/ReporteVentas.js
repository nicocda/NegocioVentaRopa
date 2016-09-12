$(document).ready(function()
{
	setearFormatosDatePicker();
	agregarEventos();
	cargarComboClientes();
	cargarTabla();
	eventosDeTabla();
	eventosVolver();
});

function agregarEventos()
{
	$(document).on("click", "#btnBuscar", function()
	{
			$("#divTabla").show();
			$("#tablaVenta").DataTable().ajax.reload();
	});
	
	$("#cbFecha").change(function()
	{
		var date = new Date();
				
		if($("#cbFecha").val()==1)
		{
			$("#fechaMinima").datepicker("setDate", date);
		}
		else if($("#cbFecha").val()==2)
		{
			var fechaAuxiliar = date
			fechaAuxiliar.setDate(fechaAuxiliar.getDate() - 1);
			$("#fechaMinima").datepicker("setDate", fechaAuxiliar);
		}
		else if($("#cbFecha").val()==3)
		{
			var fechaAuxiliar = date;
			fechaAuxiliar.setDate(fechaAuxiliar.getDate() - 7);
			$("#fechaMinima").datepicker("setDate", fechaAuxiliar);
		}
		else if($("#cbFecha").val()==4)
		{
			var fechaAuxiliar = date;
			fechaAuxiliar.setDate(fechaAuxiliar.getDate() - 14);
			$("#fechaMinima").datepicker("setDate", fechaAuxiliar);
		}
		else if($("#cbFecha").val()==5)
		{
			var fechaAuxiliar = date;
			fechaAuxiliar.setMonth(fechaAuxiliar.getMonth() - 1);
			$("#fechaMinima").datepicker("setDate", fechaAuxiliar);
		}
		else if($("#cbFecha").val()==6)
		{
			var fechaAuxiliar = date;
			fechaAuxiliar.setMonth(fechaAuxiliar.getMonth() - 2);
			$("#fechaMinima").datepicker("setDate", fechaAuxiliar);
		}
		else if($("#cbFecha").val()==7)
		{
			var fechaAuxiliar = date;
			fechaAuxiliar.setFullYear(fechaAuxiliar.getFullYear() - 1);
			$("#fechaMinima").datepicker("setDate", fechaAuxiliar);
		}
		else if($("#cbFecha").val()==8)
		{
			var fechaAuxiliar = date;
			fechaAuxiliar.setFullYear(fechaAuxiliar.getFullYear() - 2);
			$("#fechaMinima").datepicker("setDate", fechaAuxiliar);
		}
		else if($("#cbFecha").val()==9)
		{
			var fechaAuxiliar = date;
			fechaAuxiliar.setFullYear(fechaAuxiliar.getFullYear() - 1000);
			$("#fechaMinima").datepicker("setDate", fechaAuxiliar);
		}
		$("#fechaMaxima").datepicker("setDate", new Date());
	});
}

function eventosDeTabla()
{
	$("#tablaVenta tbody").on('click', ".detalleVenta", function()
	{
		var data = $("#tablaVenta").DataTable().row($(this).closest('tr')).data();
		cargarTablaDetalle(data);	
	});
}

function eventosVolver()
{
	$("#btnVolver").click(function(){
		
		$("#divDetalleVenta").hide();
		$("#divPrincipal").show();
		
	});
}

function setearFormatosDatePicker()
{
	$.datepicker.regional['es'] = 
	{
		 closeText: 'Cerrar',
		 prevText: '<Ant',
		 nextText: 'Sig>',
		 currentText: 'Hoy',
		 monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
		 monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
		 dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'S&#225;bado'],
		 dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','S&#225;b'],
		 dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&#225;'],
		 weekHeader: 'Sm',
		 dateFormat: 'dd MM yy',
		 firstDay: 1,
		 isRTL: false,
		 showMonthAfterYear: false,
		 yearSuffix: '',
		 changeYear: true,
		 showButtonPanel: true
	};
	$.datepicker.setDefaults($.datepicker.regional["es"]);
	$("#fechaMinima").datepicker();
	$("#fechaMaxima").datepicker();
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
		$("#comboClientes").select2(
		{
		  data: clientes
		});
		cargarOtrosCombos();
	});
}

function cargarOtrosCombos()
{
	$("#cbFecha").select2({minimumResultsForSearch: -1});
	$("#cbTipoPago").select2({minimumResultsForSearch: -1});
	$("#cbTipoPago").change(function()
			{
				$("#divTarjeta").hide();
				if($("#cbTipoPago").val() == 3)
				{
					$("#divTarjeta").show();
					$.post('/NegocioRopa/ReporteVentas', { "action": "cargarTipoTarjeta" }, function(resultado)
							{
								var tarjetas = [];
								jQuery.each(resultado, function()
								{
									tarjetas.push({id: this.id, text: this.descripcion});
								});
								$("#comboTipoTarjetas").select2(
								{
								  data: tarjetas
								});
							});
				}
			});
	
}


function cargarTabla()
{
	$("#tablaVenta").DataTable(
	{
		info: false,
        paginate: false,
        searching: false,
        cache: false,
    	url: "/NegocioRopa/ReporteVentas",
    	params: function () 
    	{
    		return {
	        	"action" : "mostrarVenta",
	        	"fechaMinima" : $("#fechaMinima").datepicker( "getDate" ),
	        	"fechaMaxima" : $("#fechaMaxima").datepicker( "getDate" ),
	        	"idCliente" :  $("#comboClientes").val(),
	        	"tipoPago" : $("#cbTipoPago").val(),
	        	"tipoTarjeta" : $("#comboTipoTarjetas").val()
	    	};
    	},
    	width: 100,
    	columns: 
		[
		 	 {"data": "idVenta"},
			 {"data": "fecha"},
			 {"data": "nombreApellido"},
			 {"data": "importe"},
			 {"data": "formaPago"},
			 {"data": "tipoTarjeta"},
	         {"data": null, "targets": -1, "defaultContent": "<button class='btn btn-info detalleVenta'>Ver Detalle</button>", "bSortable": false}
	    ]
	});
}
function cargarTablaDetalle(data)
{
	$("#tablaDetalleVenta").DataTable(
			{
		        info: false,
		        paginate: false,
		        searching: false,
		        cache: false,
		    	url: "/NegocioRopa/ReporteVentas",
		    	params: function () { return { "action" : "detalleVenta", "idVenta": data.idVenta } },
				columns: 
				[
					 {data: "id"},
					 {data: "descripcion", bSortable: true},
					 {data: "precio", bSortable: true},
					 {data: "estado", visible: true}
				]
		        
		    });
	
	$("#divPrincipal").hide();
	$("#idVenta").val(data.idVenta);
	$("#divDetalleVenta").show();
	$("#nombreComprador").html(data.nombreApellido);
	$("#fechaVta").html(data.fecha);
	$("#formaPago").html(data.formaPago);
	//$("#tablaDetalleVenta").DataTable().ajax.reload();
}


