package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UsuarioDTO;

/**
 * Servlet implementation class RespuestaCorta
 */
@WebServlet("/RespuestaCorta")
public class RespuestaCorta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jspPage = "index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(jspPage);
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ((action == null) || (action.length() < 1)) {
			action = "default";
		}
		
		if (action.equals("existeUsuario")) {
			try {
				Delegate.BDTest bd = Delegate.BDTest.getInstance();
				String strUsuario = request.getParameter("usuario");
				UsuarioDTO u = new UsuarioDTO(strUsuario, null, null);
				String rta = null;
				if (bd.existeUsuario(u)) {
					rta = "true";
				}
				else {
					rta = "false";
				}
				response.setContentType("text");
				response.getWriter().write(rta);
			}
			catch (Exception e) {
				String jspPage = "index.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(jspPage);
				rd.forward(request, response);
			}
		}
		else {
			String jspPage = "index.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(jspPage);
			rd.forward(request, response);
		}
	}

}
