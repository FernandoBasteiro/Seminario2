package controladores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Usuario;

public class AdministrarUsuarios {

	public static AdministrarUsuarios instancia;
	public ArrayList<Usuario> usuarios;
	
	public AdministrarUsuarios() {
		usuarios = new ArrayList<Usuario>();
	}
	
	public static AdministrarUsuarios getInstancia() {
		if (instancia == null) {
			instancia = new AdministrarUsuarios();
		}
		return instancia;
	}
	
	public boolean isLoggedIn (UsuarioDTO usuario) throws LoggedInException, ComunicacionException {
		Usuario u = this.buscarUsuario(usuario.getLogin());
		if (u != null) {
			if (u.getLogin().equals(usuario.getLogin())) return true;
		}
		throw new LoggedInException("El usuario no esta conectado.");
	}

	public Usuario buscarUsuario(String login) throws ComunicacionException {
		for (Usuario u : usuarios) {
			if (u.getLogin().equals(login)) {
				u.actualizar();
				return u;
			}
		}
		Usuario u = UsuarioDAO.getInstancia().toNegocio(UsuarioDAO.getInstancia().getUsuaruiByLogin(login));
		this.usuarios.add(u);
		return u;
	}
	
	public void crearUsuario(UsuarioDTO usuario) throws ComunicacionException {
		if (!UsuarioDAO.getInstancia().existeUsuarioByLogin(usuario.getLogin())) {
			Usuario u = new Usuario(usuario.getLogin(), usuario.getPwd(), usuario.getNombre(), usuario.getToken(), true);
			u.crear();
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - Se creo el Usuario: " + usuario.getLogin());
		} else throw new ComunicacionException("El Usuario ya existe");
	}
	
	public void modificarUsuario(UsuarioDTO usuario) throws ComunicacionException {
		if (UsuarioDAO.getInstancia().existeUsuarioByLogin(usuario.getLogin())) {
			Usuario u = new Usuario(usuario.getId(),usuario.getLogin(), usuario.getPwd(), usuario.getNombre(), usuario.getToken(), usuario.getVarUbicacion(),usuario.getVarDispHoraria(),usuario.getVarNivel(),true);
			u.actualizar();
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - Se actualizo el Usuario: " + usuario.getLogin());
		} else throw new ComunicacionException("El Usuario no existe");
	}
	
	public Boolean existeUsuario(UsuarioDTO usuario) throws ComunicacionException {
		if (buscarUsuario(usuario.getLogin()) == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public UsuarioDTO login(UsuarioDTO usuario) throws LoggedInException {
		Usuario u;
		try {
			u = this.buscarUsuario(usuario.getLogin());
			if (u.passwordCorrecta(usuario.getPwd())) {
				u.setToken(usuario.getToken());
				u.grabar();
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
				System.out.println(dtf.format(now) + " - Autenticacion correcta de parte del Usuario: " + usuario.getLogin());
				UsuarioDTO usr = u.toDTO();
				return usr;
			}
			throw new LoggedInException("No pudo autenticarse el usuario");
		} catch (ComunicacionException e) {
			throw new LoggedInException("No pudo autenticarse el usuario");
		}
	}
	
	public void modificarPerfil(UsuarioDTO usuario) throws ComunicacionException, LoggedInException {
		if (this.isLoggedIn(usuario)) {
			Usuario u = this.buscarUsuario(usuario.getLogin());
			u.setVarDispHoraria(usuario.getVarDispHoraria());
			u.setVarUbicacion(usuario.getVarUbicacion());
			java.time.LocalDate javaTime = usuario.getVarFechaNac();
			org.joda.time.LocalDate jodaTime = new org.joda.time.LocalDate(javaTime.getYear(), javaTime.getMonthValue(), javaTime.getDayOfMonth());
			u.setVarFechaNac(jodaTime);
			u.grabar();
		}
	}
	
	public UsuarioDTO listarPerfil(UsuarioDTO usuario) throws ComunicacionException, LoggedInException {
		if (this.isLoggedIn(usuario)) {
			Usuario u = this.buscarUsuario(usuario.getLogin());
			usuario.setVarDispHoraria(u.getVarDispHoraria());
			usuario.setVarUbicacion(u.getVarUbicacion());
			org.joda.time.LocalDate jodaTime = u.getVarFechaNac();
			java.time.LocalDate javaTime = java.time.LocalDate.of(jodaTime.getYear(), jodaTime.getMonthOfYear(), jodaTime.getDayOfMonth());
			usuario.setVarFechaNac(javaTime);
		}
		return usuario;
	}
	
}
