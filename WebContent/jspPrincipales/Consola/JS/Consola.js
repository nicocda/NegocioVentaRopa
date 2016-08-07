$(document).ready(function() 
{
	var items = $("#tablaPaginada tbody tr");
	
	var numItems = items.length;
    var porPagina = 20;
    
    items.slice(porPagina).hide();
    
    $("#paginacion").pagination({
        items: numItems,
        itemsOnPage: porPagina,
        onPageClick: function(numeroPagina) 
        {
            var mostrarDesde = porPagina * (numeroPagina - 1);
            var mostrarHasta = mostrarDesde + porPagina;

            items.hide()
                 .slice(mostrarDesde, mostrarHasta).show();
        }
    });
    
    var checkFragment = function() {
        var hash = window.location.hash || "#page-1";

        hash = hash.match(/^#page-(\d+)$/);

        if(hash)
            $("#pagination").pagination("selectPage", parseInt(hash[1]));
    };

    $(window).bind("popstate", checkFragment);

    checkFragment();

});