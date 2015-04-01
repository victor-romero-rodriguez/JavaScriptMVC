package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ComentarioLibro")
public class ComentarioLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Para funcionar con JSON
		response.setContentType("text/plain");

		// lo que cambia respecto al ejemplo anterior
		//response.setContentType("text/xml;charset=UTF-8");

		PrintWriter out = response.getWriter();

		String[] comentarios = {
				"Friedrich Nietzsche, tambien llamado el ateo nihislita o el filosofo con martillo.",
				"Leandro Fernandez de Moratin escribio esta obra en los albores de 1800. Estrenada en 1806 en el teatro de la Cruz.",
				"Gonzalo Alvarez y Pedro Pablo Perez presentan el libro mas completo y comprensible de seguridad informatica para la empresa.",
				"Introduccion a las principales tecnologias de la plataforma." };

		// Precios para cada libro
		String[] precios = { "12.50", "16.70", "17.00", "21.00" };
		// Para forzar eun error
		//String[] precios = { "12.50", "16.70", "17.00" };

		String seleccion = request.getParameter("tit");

		int index = Integer.parseInt(seleccion);

		// Formación del objeto JSON de respuesta con los datos del libro
		// Formato: {comentario:'abcd',precio:'xx'}
		StringBuilder textoJSON = new StringBuilder("{comentario:'");
		textoJSON.append(comentarios[index]);
		textoJSON.append("',precio:");
		textoJSON.append(precios[index]);
		textoJSON.append("}");
				  
		// Para JSON
		out.println(textoJSON);

		out.close();
	}
}