$(document).ready(function()
{
	eventos();
})

function mostrarPopup()
{
	 $( "#dialog" ).dialog({
	      
	      autoOpen: false,
	      show: {
	        effect: "blind",
	        duration: 1000
	      },
	      hide: {
	        effect: "blind",
	        duration: 1000
	      }
	  });
}

function eventos()
{
	 $("#agregar").click(function()
	 {
		 $( "#dialog" ).dialog("open");
	 })
	 
	 mostrarPopup();
	 
	 var icons = 
	 {
		  header: "ui-icon-circle-arrow-e",
		  activeHeader: "ui-icon-circle-arrow-s"
	 };
	 $( "#accordion" ).accordion({
	      icons: icons
	    });	 
}