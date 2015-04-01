function enviar() {
	var usuario = new Object();
    usuario.login = $('#login').val();
	usuario.pwd = $('#pwd').val();
	$.ajax({
		url: "Login",
		type: 'POST',
		dataType: 'json',				
		data: JSON.stringify(usuario),
        contentType: 'application/json',
        mimeType: 'application/json',
		success: function(data) {
			if (data.indexOf("Atencion:") != -1) {
				$('#msj').html(data);
			} else {
				$('#msj').html("Tu saldo: " + data);
			}	
		},
		error: function(jqXHR, textStatus, errorThrown) {
			$('#msj').html("Se ha producido un error en el servidor");
			alert(jqXHR+" - "+textStatus+" - "+errorThrown);
		}       
	}); 
}	