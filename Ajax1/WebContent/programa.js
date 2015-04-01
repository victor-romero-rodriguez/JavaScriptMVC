var xhr;

function buscarComentario() {
	try {
		if (window.XMLHttpRequest)
			xhr = new XMLHttpRequest();
		else
			xhr = new ActiveXObject("Microsoft.XMLHttp");
	} catch (ex) {
		alert("Su navegador no soporta este objeto");
	}
	enviaPeticion();
}

function enviaPeticion() {
	var titulo = document.getElementById("titulo");
	if (titulo.selectedIndex == 0) {
		document.getElementById("comentario").innerHTML = "Sin comentario";
		return;
	}
	var tit = titulo.options[titulo.selectedIndex].value;
	xhr.open("GET", "ComentarioLibro?tit=" + tit, true);
	xhr.onreadystatechange = procesaDatos;
	xhr.send(null);
}

function procesaDatos() {
	if (xhr.readyState == 4)
		document.getElementById("comentario").innerHTML = "<i>"
				+ xhr.responseText + "</i>";
}