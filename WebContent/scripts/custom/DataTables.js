//Configuración por defecto
$.extend( $.fn.dataTable.defaults, 
{
	 scrollY:"600px",
	 pageLength: 50,
	//Español
	language: 
	{
        info: "Mostrando p&aacute;ginas _PAGE_ de _PAGES_",
        infoEmpty: "Mostrando de 0 a 0 en 0 registros",
        infoFiltered: "(Filtrados de _MAX_ registros totales)",
        loadingRecords: "Cargando...",
        processing: "Procesando...",
        search: "Buscar:",
        emptyTable: "No hay registros disponibles",
        zeroRecords: "No se encontraron resultados",
        paginate: 
        {
            first: "Primero",
            last: "&Uacute;ltimo",
            next: "Siguiente",
            previous: "Anterior"
        }
    },
    //CSS responsivo
	responsive: true,
	bLengthChange: false,
	//AJAX genérico
	ajax: function (data, callback, settings)
	{
		//Hay una función params en cada DataTable que recibe los parámetros
		var postData = settings.oInit.params();
		
		$.ajax({
			type: "POST",
			url: settings.oInit.url,
			data: postData,
			dataType: "json",
			success: function (d)
			{
				//Acá se puede manejar la respuesta del servidor si hicieramos un JSON genérico
				if (settings.oInit.dataSrc != null)
					callback(JSON.parse(JSON.stringify(d).replace(settings.oInit.dataSrc, "data")));
				else
					callback(d);
			},
			error: function (d)
			{
				return ("mensaje!");
			}
		});
	}
});