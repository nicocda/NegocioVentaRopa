$(document).ready(function()
{	
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
	$(document).on("click", "#btnAceptar", function()
	{
		$.ajax(
		{
			type: "POST",
			url: '/NegocioRopa/ABMProducto',
			dataType: 'json',
			data: 
			{
				"id": $("#txtID").val(),
				"descripcion": $("#txtDescripcion").val(),
				"precio": $("#txtPrecio").val()
			},
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
			}
		});
	});
});
