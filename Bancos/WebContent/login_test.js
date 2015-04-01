module("bancos",{
	setup: function() {
		S.open('login.html');
	}
});

/* Comprobar que el usuario puede hacer login si el  
 * login existe y el password se corresponde al login */
test("Camino feliz", function () {
	
  // Preparacion
  S('#login').type('sara@yahoo.es');
  S('#pwd').type('54321');
  
  // Ejecucion
  S('#btn_enviar').click();

  // Aserciones
  S('#msj').visible(function () {
    var msjServidor = S('#msj').html();
    var msjEsperado = "Tu saldo: 30.500,00 " + '\u20ac'; // simbolo del euro
    equal(msjServidor, msjEsperado, "Real: " + msjServidor + " --- Esperado: " + msjServidor);
  });
});

//Comprobar que el usuario no puede hacer login si deja el login vacio
test("login vacio", function () {
	
  // Preparacion
  S('#pwd').type('12345');

  // Ejecucion
  S('#btn_enviar').click();

  // Aserciones
  S('#msj').visible(function () {
    var msjServidor = S('#msj').html();
    var msjEsperado = "Atencion: El login no puede estar vacio!";
    equal(msjServidor, msjEsperado, "Real: " + msjServidor + " --- Esperado: " + msjServidor);
  });
});

// Comprobar que el usuario no puede hacer login si deja el pwd vacio
test("password vacio", function () {
	
  // Preparacion
  S('#login').type('luis@gmail.com');

  // Ejecucion
  S('#btn_enviar').click();

  // Aserciones
  S('#msj').visible(function () {
    var msjServidor = S('#msj').html();
    var msjEsperado = "Atencion: El password no puede estar vacio!";
    equal(msjServidor, msjEsperado, "Real: " + msjServidor + " --- Esperado: " + msjServidor);
  });
});

/* Comprobar que el usuario no puede hacer login 
 * si el login no es reconocido en el sistema */
test("login no reconocido", function () {
	
  // Preparacion
  S('#login').type('antonio@gmail.com');
  S('#pwd').type('12345');
  
  // Ejecucion
  S('#btn_enviar').click();

  // Aserciones
  S('#msj').visible(function () {
    var msjServidor = S('#msj').html();
    var msjEsperado = "Atencion: Credenciales incorrectas!";
    equal(msjServidor, msjEsperado, "Real: " + msjServidor + " --- Esperado: " + msjServidor);
  });
});

/* Comprobar que el usuario no puede hacer login 
 * si el password no se corresponde al login */
test("password no pertenece al login", function () {
	
  // Preparacion
  S('#login').type('sara@yahoo.es');
  S('#pwd').type('lalalala');
  
  // Ejecucion
  S('#btn_enviar').click();

  // Aserciones
  S('#msj').visible(function () {
    var msjServidor = S('#msj').html();
    var msjEsperado = "Atencion: Credenciales incorrectas!";
    equal(msjServidor, msjEsperado, "Real: " + msjServidor + " --- Esperado: " + msjServidor);
  });
});