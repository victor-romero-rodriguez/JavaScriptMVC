var xhr;

function validar() {
	xhr = new XMLHttpRequest();
	enviaPeticion();
}

function enviaPeticion() {
	xhr.open("POST", document.forms[0].action, true);
	xhr.onreadystatechange = procesaResultado;

	// Definición del tipo de contenido a enviar para formularios HTML
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	var datos = obtenerDatos();

	// Se envían los datos en el cuerpo de la petición
	xhr.send(datos);
}

function obtenerDatos() {
	var controles = document.forms[0].elements;
	var datos = new Array();
	var cad = "";

	for (var i = 0; i < controles.length; i++) {
		cad = encodeURIComponent(controles[i].name) + "=";
		cad += encodeURIComponent(controles[i].value);
		datos.push(cad);
	}

	// Convertir el array en una cadena donde los elementos se separan con &
	cad = datos.join("&");
	return cad;
}

function procesaResultado() {
	if (xhr.readyState == 4) {
		if (xhr.status == 200)
			document.getElementById("resultado").innerHTML = xhr.responseText;
		else
			alert(xhr.statusText);
	}
}