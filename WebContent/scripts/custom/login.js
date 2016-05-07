$(document).ready(function()
{	
	$("#btnIngresar").click(function(){
		ingresar();
	});
	
	$(document).keypress(function(e) {
	    if(e.which == 13) 
	    {
	        ingresar();
	    }
	});
	
	$("#txtLogin").click(function(){
	
		$("#txtLogin").css("border", "solid 5px #c9c9c9");
		$("#txtPass").css("border", "solid 5px #c9c9c9");
		$("#mensaje").empty();
	});
	$("#txtPass").click(function(){
		
		$("#txtLogin").css("border", "solid 5px #c9c9c9");
		$("#txtPass").css("border", "solid 5px #c9c9c9");
		$("#mensaje").empty();

	});
});
function cambiarBorde(cambia)
{
		$("#txtPass").css("border", "solid 5px #FF0000");
		$("#txtLogin").css("border", "solid 5px #FF0000");
}

function ingresar()
{
	$.post("/NegocioRopa/Login", {"action":"login","usuario":$("#txtLogin").val(),"password":$("#txtPass").val()},function(result){
		if (!result.exito)
		{
			cambiarBorde();
			$("#mensaje").empty();
			$("#mensaje").append(result.mensaje);
		}
		else
		{
			window.location.href = "/NegocioRopa/Index?link=ABMCliente"
		}
	});
}
