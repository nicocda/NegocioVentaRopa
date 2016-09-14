$.postData = function(url, data, complete)
{
	var exito = true;
	$.ajax(
	{
		type: "POST",
		url: url,
		dataType: 'json',
		data: data,
		success:function(result) 
		{
			if(result.tipoMensaje == "error")
			{
				$("#divError").load("./jspCompartido/MensajeError.jsp", function(){
					$("#mensaje ul").empty();
					jQuery.each(result.mensajes, function()
					{
						$("#mensaje ul").append("<li>" + this.mensaje + "</li>");
						$("#mensaje").show("slow");
					});
				});
				exito = false;
			}
			else
			{
				$("#divError").load("./jspCompartido/MensajeExito.jsp", function(){
					$("#mensaje strong").append(result.tipoMensaje);
					$("#mensaje").show("slow");
				});
			}
		},
		error:function()
		{
			alert("error");
		},
		complete:function()
		{
			if (exito)
				complete();
		}
	});
}

$.postDataSinExito = function(url, data, exito)
{
	$.ajax(
	{
		type: "POST",
		url: url,
		dataType: 'json',
		data: data,
		success: function(result) 
		{
			if(result.tipoMensaje == "error")
			{
				$("#divError").load("./jspCompartido/MensajeError.jsp", function(){
					$("#mensaje ul").empty();
					jQuery.each(result.mensajes, function()
					{
						$("#mensaje ul").append("<li>" + this.mensaje + "</li>");
						$("#mensaje").show("slow");
					});
				});					
			}
			else
			{
				exito();
			}
		},
		error: function(d)
		{
			alert("error");
		}
	});
}

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

$.datepicker.setDefaults($.datepicker.regional["es"]);;
