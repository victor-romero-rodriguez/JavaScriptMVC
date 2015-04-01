function getProductos() {

	// Obtener la plantilla desde el servidor
	$.ajax({
		type : 'GET',
		url : 'tpl/productos.mustache',
		dataType : 'text',
		success : function(datosAjax) {
			var plantilla = datosAjax;
			
			// Obtener los datos de los productos desde el servidor
			// El null correspondiente al segundo param. es porque no enviamos nada al servidor
			$.getJSON("datos/productos.txt", null, function(datosAjax) {
				var listaProductos = datosAjax;
				
				// Creamos una funcion Lambda llamada formato2dec que podremos utilizar desde la 
				// propia plantilla para cuando queramos que un numero se imprima con 2 decimales.
				// argumentos:
				// num = es el numero al que se le quiere dar el formato
				// render = es una funcion especial de renderizado propia de Mustache que opera sobre la vista actual
				listaProductos.formato2dec = function() {
		            return function(num, render) {
		                return parseFloat(render(num)).toFixed(2);
		            };
		        };
				
		        // Ahora llamamos a la funcion que lleva a cabo el renderizado
		        // argumentos:
		        // plantilla = el fichero de plantila Mustache
		        // listaProducto = el fichero JSON con los datos que requiere la plantilla
				renderizar(plantilla, listaProductos);
			});			
		}
	});
}

function renderizar(plantilla, listaProductos) {
	var salida = Mustache.render(plantilla, listaProductos);
	$('#listaProductos').html(salida);
}