function validar() {
	$("#resultado")
		.load("Validador", 
			$("#formulario").serializeArray());
}