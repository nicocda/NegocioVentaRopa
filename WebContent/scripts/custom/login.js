$(document).ready(function()
{	
	$("#btnIngresar").click(function(){
		$.post("/NegocioRopa/Login", {"action":"login","usuario":$("#txtLogin").val(),"password":$("#txtPass").val()},function(result){
			cambiarBorde();
			$("#mensaje").empty();
			$("#mensaje").append(result.usuario+" el error es: "+result.mensaje);
		});

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
