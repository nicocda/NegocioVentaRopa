$(document).ready(function()
{	
	$(function() 
	{
	    var availableTags = [
	      "ActionScript",
	      "AppleScript",
	    ];
		$.post("/NegocioRopa/AutoCompletar", {"accion": "buscar"}, function(result)
		{
			var arr = $.map(result, function(el){return el;});
			
			$( "#txtClientes" ).autocomplete({ source:  function(request, response) {
		        var results = $.ui.autocomplete.filter(arr, request.term);

		        response(results.slice(0, 4));
		    }});
			$('#').css('fontSize', '2px');
		});
		//$( "#txtClientes" ).autocomplete({ source: availableTags});
	 });
});
