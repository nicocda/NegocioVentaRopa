$(document).ready(function()
{
	
	$(document).on('click', "#btnCerrarMensaje", function()
	{
		$("#mensaje").hide("slow");
	});
	
	$("#btnAceptar").click(function()
	{
		$.ajax(
		{
			type: "Post",
			url: '/NegocioRopa/ABMProducto',
			dataType: 'json',
			success:function(result) 
			{
				if(result.tipoMensaje == "exito")
				{
					$("#divError").load("../jspCompartido/MensajeExito.jsp", function(){
						$("#mensaje strong").append("&Eacute;xito!");
						$("#mensaje").show("slow");
					});
				}
				else
				{
					$("#divError").load("../jspCompartido/MensajeError.jsp", function(){
						$("#mensaje ul").empty();
						jQuery.each(result.mensajes, function()
						{
							$("#mensaje ul").append("<li>" + this.mensaje + "</li>");
							$("#mensaje").show("slow");
						});
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
