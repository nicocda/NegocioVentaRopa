$(document).ready(function()
{
	eventos();
})

function mostrarPopup()
{
	 $( "#dialog" ).dialog({
	      modal: true
	  });
}

function eventos()
{
	 $("#agregar").click(function()
	 {
		 mostrarPopup();
	 })
	 
	 $( "#accordion" ).accordion();	 
}