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