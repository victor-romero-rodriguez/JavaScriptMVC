package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Validador")
public class Validador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/plain;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String resultado = "<b>Datos correctos</b>";

		// Recupera todos los nombre de parámetros
		// recibidos en la petición
		Enumeration<String> nombres = request.getParameterNames();

		boolean continuar = true;
		while (nombres.hasMoreElements() && continuar) {
			if (request.getParameter(nombres.nextElement()).equals("")) {
				resultado = "<b>Error en los datos. ";
				resultado += "Debe rellenar todos los campos</b>";
				continuar = false;
			}
		}
		out.println(resultado);
		out.close();
	}
}