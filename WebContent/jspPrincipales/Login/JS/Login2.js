$(document).ready(function()
{	
	$("#btnLogin").click(function(e)
	{
		e.preventDefault();
		login();
	});
	
	$(document).keypress(function(e) {
	    if(e.which == 13) 
	    {
	    	e.preventDefault();
			login();
	    }
	});
	
});


function login()
{
	$("#btnLogin").hide();
	$("#divLoader").load("/NegocioRopa/jspCompartido/Loader/Loader.html");
	$("#divLoader").show();
	
	$.post("/NegocioRopa/Login", { "action": "login", "usuario": $("#txtLogin").val(), "password": $("#txtPass").val() },function(result)
	{
		if (!result.exito)
		{
			$("#mensaje").empty();
			$("#mensaje").append(result.mensaje);
			$("#divLoader").hide();
			$("#btnLogin").show();
			$("#lblMensajeError").text("El usuario o la contrasena ingresados no son correctos");
		}
		else
		{
			window.location.href = "/NegocioRopa/ABMClientes"
		}
	});
}
