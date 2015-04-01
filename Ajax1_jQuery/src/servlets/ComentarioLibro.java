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

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();

		String[] comentarios = {
				"Friedrich Nietzsche, tambien llamado el ateo nihislita o el filosofo con martillo.",
				"Leandro Fernandez de Moratin escribio esta obra en los albores de 1800. Estrenada en 1806 en el teatro de la Cruz.",
				"Gonzalo Alvarez y Pedro Pablo Perez presentan el libro mas completo y comprensible de seguridad informatica para la empresa.",
				"Introduccion a las principales tecnologias de la plataforma." };

		String seleccion = request.getParameter("tit");

		int index = Integer.parseInt(seleccion);

		out.println(comentarios[index]);
		out.close();
	}
}