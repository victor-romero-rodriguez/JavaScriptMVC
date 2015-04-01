package com.curso.javascriptmvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/MiServlet")
public class MiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		StringBuilder str = new StringBuilder();
		str.append("<html>");
		str.append("<body>");
		str.append("<h1>HOLA</h1>");
		str.append("</body>");
		str.append("</html>");

		writer.write(str.toString());
		writer.close();
	}

}
