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
	 
	 
	 var icons = 
	 {
		  header: "ui-icon-circle-arrow-e",
		  activeHeader: "ui-icon-circle-arrow-s"
	 };
	 $( "#accordion" ).accordion({
	      icons: icons
	    });	 
}