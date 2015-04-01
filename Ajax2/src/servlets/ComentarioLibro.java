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

		// response.setContentType("text/plain");

		// lo que cambia respecto al ejemplo anterior
		response.setContentType("text/xml;charset=UTF-8");

		PrintWriter out = response.getWriter();

		String[] comentarios = {
				"Friedrich Nietzsche, tambien llamado el ateo nihislita o el filosofo con martillo.",
				"Leandro Fernandez de Moratin escribio esta obra en los albores de 1800. Estrenada en 1806 en el teatro de la Cruz.",
				"Gonzalo Alvarez y Pedro Pablo Perez presentan el libro mas completo y comprensible de seguridad informatica para la empresa.",
				"Introduccion a las principales tecnologias de la plataforma." };

		// Precios para cada libro
		//String[] precios = { "12.50", "16.70", "17.00", "21.00" };
		// Para forzar eun error
		String[] precios = { "12.50", "16.70", "17.00" };

		String seleccion = request.getParameter("tit");

		int index = Integer.parseInt(seleccion);

		// Formación del documento XML de respuesta
		StringBuilder textoXML = new StringBuilder("<?xml version='1.0'?>");
		textoXML.append("<libro>")
				.append("<comentario>")
				.append(comentarios[index])
				.append("</comentario>")
				.append("<precio>")
				.append(precios[index])
				.append("</precio>")
				.append("</libro>");

		// out.println(comentarios[index]);

		// Insercion de los datos XML en la respuesta
		out.println(textoXML);

		out.close();
	}
}