package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Map<Usuario, Double> saldo;
	
	static {
		Usuario usuario = null;		
		saldo = new HashMap<Usuario, Double>();
		
		usuario = new Usuario("luis@gmail.com", "12345");
		saldo.put(usuario, 6000d);
		
		usuario = new Usuario("sara@yahoo.es", "54321");
		saldo.put(usuario, 30500d);
	}
       
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
				
        response.setContentType("application/json");
        
		String json = getJsonFromRequest(request);
		
        // Jackson mapper: Creara un objeto Usuario a partir de un Json
        ObjectMapper mapper = new ObjectMapper();
		
        Usuario usuario = getUsuarioFromJson(mapper, json);
        
        String respuesta = getRespuesta(usuario);
        
        // Enviar la respuesta al usuario en formato Json
        mapper.writeValue(response.getOutputStream(), respuesta);		
	}

	private String getJsonFromRequest(HttpServletRequest request) throws IOException {		
        BufferedReader br = 
        	new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null){
            json = br.readLine();
        }
		return json;
	}
	
	private Usuario getUsuarioFromJson(ObjectMapper mapper, String json) 
			throws JsonParseException, JsonMappingException, IOException {		
		Usuario usuario = mapper.readValue(json, Usuario.class);
		return usuario;
	}
	
	private String getRespuesta(Usuario usuario) {
		String respuesta = null;
		if (saldo.containsKey(usuario)) {			
			respuesta = NumberFormat.getCurrencyInstance().format(saldo.get(usuario));
        } else {
        	if (usuario.getLogin() == null || usuario.getLogin().trim().equals("")) {
        		respuesta = "Atencion: El login no puede estar vacio!";
        	} else if (usuario.getPwd() == null || usuario.getPwd().trim().equals("")) {
        		respuesta = "Atencion: El password no puede estar vacio!";
        	} else {
        		respuesta = "Atencion: Credenciales incorrectas!";
        	}	
        }
		return respuesta;
	}

}
