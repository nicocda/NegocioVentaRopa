$(document).ready(function()
{	
	setearFormatosDatePicker();
	agregarEventos();
	cargarComboClientes();
	cargarOtrosCombos();
});

function agregarEventos()
{
	$("#btnBuscar").click(function()
	{
		$.post('/NegocioRopa/ReporteVentas', { "action": "mostrarVenta", "fechaMinima": $("#fechaMinima").datepicker( "getDate" ), 
			"fechaMaxima": $("#fechaMaxima").datepicker( "getDate" ), "idCliente": $("#comboClientes").val(), "tipoPago": $("#cbTipoPago").val() }, function(resultado)
		{
			$("#tablaTransacciones tr").remove();
			agregarEncabezado();
			agregarFilas(resultado);
			$("#tablaTransacciones").DataTable();
		});
	});	
	
	$(document).on("click", "#detalleVenta", function()
	{
		$.post('/NegocioRopa/ReporteVentas', {"action": "detalleVenta", "idVenta": $("#idVenta").val()})
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



function agregarEncabezado()
{
	var theadEncabezado = $("<thead />");
	var trEncabezado = $("<tr />");
	$("#tablaTransacciones").append(theadEncabezado);
	theadEncabezado.append(trEncabezado);
	trEncabezado.append($('<th width="10%">ID</th>'));
	trEncabezado.append($('<th width="45%">Cliente</th>'));
	trEncabezado.append($('<th width="35%">Fecha de Transacci&oacute;n</th>'));
	trEncabezado.append($('<th width="10%"></th>'));
}

function agregarFilas(resultado)
{
	var tbody = $("<tbody />");
	$("#tablaTransacciones").append(tbody);
	if (resultado.ventas.length > 0)
	{	
		for (var i = 0; i < resultado.ventas.length; i++)
		{
			var trFilas = $("<tr />");
			tbody.append(trFilas);
			trFilas.append($('<td id="idVenta">'+ resultado.ventas[i].idVenta +'</td>'));
			trFilas.append($('<td>'+ resultado.ventas[i].nombreApellido +'</td>'));
			trFilas.append($('<td>'+ resultado.ventas[i].fecha +'</td>'));
			trFilas.append($('<td align="center"><input id="detalleVenta" type="button" class="botones" value="Ver"></td>'));
		}
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
		$("#comboClientes").select2(
		{
		  data: clientes
		});
	});
}

function cargarOtrosCombos()
{
	$("#cbFecha").select2({minimumResultsForSearch: -1});
	$("#cbTipoPago").select2({minimumResultsForSearch: -1});
}