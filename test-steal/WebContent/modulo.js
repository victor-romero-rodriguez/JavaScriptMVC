steal(function(){
  return function(element){
    element.innerHTML = "Hola mundo";
    element.className = "bienvenido";
  };
});