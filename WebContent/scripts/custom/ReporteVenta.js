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
	agregarEventos();
});

		function agregarEventos()
		{
			$(document).on("click", "#btnFiltrarPorFecha", function()
					{
						var fecha = document.getElementById("cbFecha");
						
						$.post('/NegocioRopa/ReporteVentas', { "action": "mostrarVenta", "fechaMinima": $("#fechaMinima").val(), "fechaMaxima": $("#fechaMaxima").val()}, function(result){
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
	