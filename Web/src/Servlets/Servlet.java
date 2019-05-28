package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Delegate.BDTest;
import dto.MetasDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jspPage = "index.jsp";
		try {
			String action = request.getParameter("action");
			BDTest bd = BDTest.getInstance();
			
			if ((action == null) || (action.length() < 1)) {
				action = "default";
			}
			if ("login".equals(action)) {
				HttpSession session = request.getSession();
				String sessionId = session.getId();
				String usuario = request.getParameter("usuario");
				String password = request.getParameter("password");
				if (usuario != null && password != null) {
					UsuarioDTO usr = new UsuarioDTO(usuario, password, sessionId);
					bd.login(usr);
					usr.setPwd(null);
					session.setAttribute("loggedUsr", usr);
				}
			}
			else if ("listarPerfil".equals(action)) {
				HttpSession session = request.getSession();
				UsuarioDTO uDTO = (UsuarioDTO)session.getAttribute("loggedUsr");
				if (uDTO != null) {
					uDTO = bd.listarPerfil(uDTO);
					request.setAttribute("perfil", uDTO);
					jspPage = "perfil.jsp";
				}

			}
			else if ("modificarPerfil".equals(action)) {
				HttpSession session = request.getSession();
				UsuarioDTO uDTO = (UsuarioDTO)session.getAttribute("loggedUsr");
				if (uDTO != null) {
					uDTO = bd.listarPerfil(uDTO);
					uDTO.setVarDispHoraria(Integer.valueOf(request.getParameter("dispHoraria")));
					uDTO.setVarFechaNac(LocalDate.parse(request.getParameter("fechaNac"),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
					uDTO.setVarUbicacion(request.getParameter("ubicacion"));
					bd.modificarPerfil(uDTO);
					request.setAttribute("perfil", uDTO);
					jspPage = "perfil.jsp";
				}

			}
			else if ("listarMetas".equals(action)) {
				HttpSession session = request.getSession();
				UsuarioDTO uDTO = (UsuarioDTO)session.getAttribute("loggedUsr");
				if (uDTO != null) {
					ArrayList<MetasDTO> metas = bd.listarMetas(uDTO);
					request.setAttribute("metas", metas);
					jspPage = "verMetas.jsp";
				} 
			}
		} catch (ComunicacionException e) {
			jspPage = "index.jsp";
			request.setAttribute("error", e.getMessage());
			response.setStatus(599);
		} catch (LoggedInException e) {
			jspPage = "login.jsp";
			request.setAttribute("error", e.getMessage());
			response.setStatus(401);
		}
		RequestDispatcher rd = request.getRequestDispatcher(jspPage);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
