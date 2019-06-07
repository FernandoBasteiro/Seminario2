package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MetasDTO;
import dto.ProcedimientoDTO;
import dto.TagMetaDTO;
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
			// Delegate.BDTest bd = Delegate.BDTest.getInstance();
			Delegate.BusinessDelegate bd = Delegate.BusinessDelegate.getInstance();

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
					usr = bd.login(usr);
					usr.setPwd(null);
					session.setAttribute("loggedUsr", usr);
				}
			} else if ("logout".equals(action)) {
				HttpSession session = request.getSession();
				session.invalidate();
				jspPage = "login.jsp";
				request.setAttribute("error", "Usuario desconectado correctamente");
			} else if ("crearUsuario".equals(action)) {
				String strUsuarioFull = request.getParameter("usuario");
				List<String> arrUsuario = Arrays.asList(strUsuarioFull.split(","));
				UsuarioDTO usuario = new UsuarioDTO(arrUsuario.get(2), arrUsuario.get(3), arrUsuario.get(0) + " " + arrUsuario.get(1), null, true);
				bd.crearUsuario(usuario);		
			} else if ("listarPerfil".equals(action)) {
				HttpSession session = request.getSession();
				UsuarioDTO uDTO = (UsuarioDTO) session.getAttribute("loggedUsr");
				if (uDTO != null) {
					uDTO = bd.listarPerfil(uDTO);
					session.setAttribute("loggedUsr", uDTO);
					jspPage = "perfil.jsp";
				}

			} else if ("modificarPerfil".equals(action)) {
				HttpSession session = request.getSession();
				UsuarioDTO uDTO = (UsuarioDTO) session.getAttribute("loggedUsr");
				if (uDTO != null) {
					uDTO = bd.listarPerfil(uDTO);
					uDTO.setVarDispHoraria(Integer.valueOf(request.getParameter("dispHoraria")));
					uDTO.setVarFechaNac(LocalDate.parse(request.getParameter("fechaNac"),
							DateTimeFormatter.ofPattern("yyyy-MM-dd")));
					uDTO.setVarUbicacion(request.getParameter("ubicacion"));
					bd.modificarPerfil(uDTO);
					session.setAttribute("loggedUsr", uDTO);
					request.setAttribute("perfil", uDTO);
					request.setAttribute("success", true);
					jspPage = "perfil.jsp";
				}

			} else if ("listarMetas".equals(action)) {
				HttpSession session = request.getSession();
				UsuarioDTO uDTO = (UsuarioDTO) session.getAttribute("loggedUsr");
				if (uDTO != null) {
					ArrayList<MetasDTO> metas = bd.listarMetas(uDTO);
					request.setAttribute("metas", metas);
					jspPage = "verMetas.jsp";
				}
			} else if ("cargarMeta".equals(action)) {
				HttpSession session = request.getSession();
				UsuarioDTO uDTO = (UsuarioDTO) session.getAttribute("loggedUsr");
				if (uDTO != null) {
					uDTO = bd.listarPerfil(uDTO);
					if (uDTO.getVarDispHoraria() == null || uDTO.getVarFechaNac() == null
							|| uDTO.getVarUbicacion() == null) {
						session.setAttribute("loggedUsr", uDTO);
						jspPage = "perfil.jsp";
					} else {
						ArrayList<TagMetaDTO> tags = bd.getTagsMetas();
						request.setAttribute("tags", tags);
						jspPage = "cargarMeta.jsp";
					}
				}
			} else if ("listarAcciones".equals(action)) {
				HttpSession session = request.getSession();
				UsuarioDTO uDTO = (UsuarioDTO) session.getAttribute("loggedUsr");
				if (uDTO != null) {
					MetasDTO meta = new MetasDTO();
					meta.setDescripcion(request.getParameter("nombre"));
					meta.setVarAccion(request.getParameter("accion"));
					meta.setVarSujeto(request.getParameter("sujeto"));
					meta.setVarNivel(request.getParameter("nivel"));
					ArrayList<ProcedimientoDTO> procs = bd.listarProcedimiento(uDTO, meta);
					request.setAttribute("meta", meta);
					request.setAttribute("procs", procs);
					jspPage = "cargarAcciones.jsp";
				}
			} else if ("crearMeta".equals(action)) {
				HttpSession session = request.getSession();
				UsuarioDTO uDTO = (UsuarioDTO) session.getAttribute("loggedUsr");
				if (uDTO != null) {
					MetasDTO meta = new MetasDTO();
					meta.setDescripcion(request.getParameter("nombre"));
					String strTags = request.getParameter("tags");
					List<String> strArrTags = Arrays.asList(strTags.split(","));
					meta.setVarAccion(strArrTags.get(0));
					meta.setVarSujeto(strArrTags.get(1));
					meta.setVarNivel(strArrTags.get(2));
					ArrayList<ProcedimientoDTO> procs = new ArrayList<ProcedimientoDTO>();
					String strProcs = request.getParameter("procs");
					List<String> strArrProcs = Arrays.asList(strProcs.split(","));
					for (String i : strArrProcs) {
						ProcedimientoDTO p = new ProcedimientoDTO(Integer.valueOf(i));
						procs.add(p);
					}
					meta.setProcedimientos(procs);
					bd.altaMeta(uDTO, meta);

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
