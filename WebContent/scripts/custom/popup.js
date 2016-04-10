$(document).ready(function()
{
	 $("#agregar").click(function()
	 {
		 mostrarPopup();
	 })
	 
	 $( "#accordion" ).accordion();
	 
	 scrolleableTable();
})

function mostrarPopup()
{
	 $( "#dialog" ).dialog({
	      modal: true
	  });
}

function scrolleableTable()
{
	// Change the selector if needed
	var $table = $('table.scroll'),
	    $bodyCells = $table.find('tbody tr:first').children(),
	    colWidth;

	// Adjust the width of thead cells when window resizes
	$(window).resize(function() {
	    // Get the tbody columns width array
	    colWidth = $bodyCells.map(function() {
	        return $(this).width();
	    }).get();
	    
	    // Set the width of thead columns
	    $table.find('thead tr').children().each(function(i, v) {
	        $(v).width(colWidth[i]);
	    });    
	}).resize(); // Trigger resize handler
}