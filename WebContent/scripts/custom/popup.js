$(document).ready(function()
{
	 $("#agregar").click(function()
	 {
		 mostrarPopup();
	 })
})

function mostrarPopup()
{
	 $( "#dialog" ).dialog({
	      modal: true
	  });
 }