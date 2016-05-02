$(document).ready(function()
{
	$(function () {
		$.datepicker.setDefaults($.datepicker.regional["es"]);
		$("#fechaMinima").datepicker({
				firstDay: 1,
				onSelect: function (date) {
				$("#fechaMinima").val(date);
				},
				});
		$("#fechaMaxima").datepicker({
				firstDay: 1,
				onSelect: function (date) {
				$("#fechaMaxima").val(date);
				},
				});
		});
		var date = new Date();
		var dia = date.getDate();
		var mes = date.getMonth() + 1;
		var anio = date.getFullYear();
		var fechaActual = ((date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear());
		var fecha = document.getElementById("cbFecha");
		fecha.onchange=function(){
			if(fecha.options[fecha.selectedIndex].value==1)
				{
				$("#fechaMinima").val("01/01/"+anio);
				}
			else if(fecha.options[fecha.selectedIndex].value==2)
				{
				$("#fechaMinima").val(mes+"/01/"+anio);
				}
			else if(fecha.options[fecha.selectedIndex].value==3)
				{
				if(date.getDate()<7)
					{
					$("#fechaMinima").val((mes - 1)+"/"+(31-(7 -(dia)))+"/"+anio);
					}
				else{
					$("#fechaMinima").val(mes+"/"+(dia-7)+"/"+anio);
					}
				}
			else if(fecha.options[fecha.selectedIndex].value==4)
				{
				$("#fechaMinima").val(fechaActual);
				}
			$("#fechaMaxima").val(fechaActual);
		};
		agregarEventos();
});

		function agregarEventos()
		{
			$(document).on("click", "#verDetalle", function()
					{
						
						$.post('/NegocioRopa/ReporteVentas', { "action": "mostrarVenta", "fechaMinima": $("#fechaMinima").val(), "fechaMaxima": $("#fechaMaxima").val(),
							"opCombo": fecha.options[fecha.selectedIndex].value}, function(result){
							if(result.error)
							{
								alert(result.mensajeError);
							}
							else
							{
								recargarTabla(result);
							}
						});
					});
		
			
		

}
	