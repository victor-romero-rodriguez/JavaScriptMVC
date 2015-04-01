function buscarComentario() {	
	var tit = $("#titulo").val(); // selección del usuario
	
	if (tit == '- Seleccione t\u00edtulo -') {
		$("#comentario").html("Sin comentario");
		return;
	}
	
	$("#comentario").load("ComentarioLibro", {tit:tit}, function (){
		$("#comentario").css("font-style","italic");
	});	
}

