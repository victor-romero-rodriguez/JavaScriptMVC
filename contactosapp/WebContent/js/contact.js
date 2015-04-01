(function(){

/*
 * Al hacer POST canJS envia los datos al servidor como 
 * 'url_encoded_form' en lugar de 'application/json'.
 * Con este filtro logramos enviarlos como JSON.     
 */
$.ajaxPrefilter(function(options, originalOptions, jqXHR){
    options["contentType"] = "application/json";
    options["data"] = JSON.stringify(originalOptions["data"]);
});    
    
/*
 * Definimos (un representante de) la capa de negocio de nuestra aplicacion
 */
var contactModel = can.Model({
    findAll : "GET rest/contacts",       // Obtener una lista de contactos
    findOne : "GET rest/contacts/{id}",  // Obtener un contacto
    create  : "POST rest/contacts",      // Crear un contacto nuevo
    update  : "PUT rest/contacts/{id}",      // Modificar un contacto existente
    destroy : "DELETE rest/contacts/{id}"    // Borrar un contacto
},{});

/*
 * Definimos un controlador que maneje la lista de contactos.
 * El modo lista queremos que sea el modo predeterminado de la aplicacion.
 * El controlador se encarga de:
 *     - pintar la lista a partir de una vista y de la lista de contactos
 *     - gestionar los eventos que el usuario produce en la vista
 *     - invocar al modelo para que se lleven a cabo las operaciones CRUD
 */
var listContactsController = can.Control({
    
    // Metodo de inicializacion
    init: function(element,options){        
        var el = this.element; // El elemento del DOM vinculado al controlador
        
        // Obtener la lista de contactos del servidor y renderizarla con la plantilla adecuada
        // findAll() devuelve 'contacts' y se la pasa como argumento a la función de callback
        contactModel.findAll({},function(contacts){         	
            el.html(can.view('views/contactsList.ejs', {contacts: contacts}));            
        });
    }	   
});


/*
 * Definimos un controlador que maneje el modo formulario de la aplicacion.
 * El modo formulario se utiliza para crear un nuevo contacto o para 
 * modificar/eliminar un contacto existente.
 */
var formContactController = can.Control({
    showFormMode: function(){
        $('#contactsList').hide();    // Ocultar el modo 'lista'    	
        $('#createForm').show();    // Mostrar el modo 'formulario'
        
        // Crear un contacto vacio y asignarlo a this.contact
        this.contact = new contactModel();
        
        // Utilizar la plantilla de formulario y renderizarla con el contacto actual
        this.element.html(can.view('views/contactForm.ejs', {contact: this.contact}));
    },
    hideFormMode: function(){
        //$('#createForm').hide(); // Ocultar el modo 'formulario'
        $( "#createForm" ).fadeOut(800, function() {
        	new listContactsController('#contactsList');
        	$('#contactsList').show(); // Mostrar el modo 'lista'
        });
                
        
    },
    
    // {document} es necesario porque el boton esta definido en el controlador de la lista
    // Es decir: "Si pulsan en el botón 'Add Contact' del widget de lista, entonces yo me muestro"
    '{document} .add click': function(){
        this.showFormMode();
    },
    createContact: function() {
        
        // En 'form' tenemos todas las parejas 'campo=valor'
        var form = this.element.find('form');
        
        // En 'values' tenemos todas las parejas 'campo=valor' 
        // como form-url-enconded (campo1=valor1&campo2=valor2...)
        values = can.deparam(form.serialize());
        
        // Si al menos el contacto tiene un nombre...
        if (values.name !== "") {            
            // canJS invoca con save() al método 'create' del modelo (se hace un POST)
        	this.contact.attr(values).save();
        	//alert("Contact created!");
        	
        	this.hideFormMode();        	        	
        }
    },
    '.save click' : function(el){
        this.createContact(el);
    },    
    '.cancel click' : function(){
        this.hideFormMode();
    },
    '.delete click' : function(el){
        this.deleteContact(el);
    },
    deleteContact:function(){
        var result = confirm("This contact will be deleted. Are you sure?");
        if (result == true) {
            var form = this.element.find('form');
            values = can.deparam(form.serialize());
            if (values.name !== "") {
                // Eliminar el contacto actual
                this.contact.attr(values).destroy();                
            }
        }                 
        this.hideFormMode(); // Ocultar el modo 'formulario'
        return;
    },
    '{document} .edit click' : function(el){
        contactModel.findOne({id: el.val()}, function(contact){
            $('#createForm').html(can.view('views/contactForm.ejs', {contact: contact}));
        });
        this.showFormMode(); // Mostrar el modo 'formulario'
    }
});

/*
 * Funcion de inicializacion.
 * Se crean los dos controladores, ambos vinculados a elementos del DOM
 */

$(document).ready(function(){
   new listContactsController('#contactsList');
   new formContactController('#createForm');
});

})();

