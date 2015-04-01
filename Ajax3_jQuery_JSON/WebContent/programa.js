function buscarComentario() {
	var tit = $("#titulo").val(); // La opcion seleccionada por el usuario	
	if (tit == '- Seleccione t\u00edtulo -') {
		$("#comentario").html("Sin comentario");
		return;
	}
	$.ajax("ComentarioLibro", {
		type : "POST",
		dataType : "json", // lo que tiene que devolver el servidor
		data : 'tit=' + tit,
		timeout : 5000,
		async : false,
		beforeSend : function() {
			alert('Voy a ver al servidor');
		},
		success : postProceso,
		error : function(xhr, txtEstado, objError) {
			alert(txtEstado);
			alert(objError);
		},
		complete : function() {
			alert('operacion finalizada');
		}
	});

	function postProceso(datosLibro) {
		// Recuperacion del objeto JSON recibido en la respuesta
		var obj = datosLibro;
		// Object.keys devuelve un array con el nombre cada propiedad
		var nomCols = Object.keys(obj);
		var textoHTML = "<table id='tabla' summary='descripcion y precio'>";
		// Genera la fila con los nombres de los elementos
		textoHTML += "<thead><tr>";
		for (var i = 0; i < nomCols.length; i++) {
			textoHTML += "<th scope='col'>" + nomCols[i] + "</th>";
		}
		textoHTML += "</tr></thead>";
		// Genera la fila con los datos del libro incluidos en el documento
		textoHTML += "<tbody><tr>";
		for (var i = 0; i < nomCols.length; i++) {
			textoHTML += "<td>" + obj[nomCols[i]] // obtenemos el valor del campo
					+ "</td>";
		}
		textoHTML += "</tr></tbody></table>";

		alert("exito");
		$("#comentario").html(textoHTML);
	}
}