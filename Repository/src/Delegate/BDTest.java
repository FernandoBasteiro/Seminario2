package Delegate;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import dto.MetasDTO;
import dto.ProcedimientoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

public class BDTest {
	private static BDTest instance;
	private UsuarioDTO usr;
	private ArrayList<MetasDTO> metas;
	private ArrayList<ProcedimientoDTO> procedimientos;
	
	private BDTest() {
		usr = new UsuarioDTO(1, "falbino", "1234", "Fabian Albino", "", "CABA", 20, "Asd?", true);
		usr.setVarFechaNac(LocalDate.of(1987, 9, 19));
		ArrayList<ProcedimientoDTO> ps = new ArrayList<ProcedimientoDTO>();
		ProcedimientoDTO p = new ProcedimientoDTO(1, "Accion 1", "URL 1", 1);
		ps.add(p);
		procedimientos.add(p);
		p = new ProcedimientoDTO(2, "Accion 2", "URL 2", 1);
		ps.add(p);
		procedimientos.add(p);
		p = new ProcedimientoDTO(3, "Accion 3 (ASD)", "URL 3", 1);
		ps.add(p);
		procedimientos.add(p);
		MetasDTO m = new MetasDTO("Quiero aprender a manejar tractores azules", false, "Aprender", "Manejar", "Avanzado", ps, usr.getLogin());
		metas.add(m);
		
		ps = new ArrayList<ProcedimientoDTO>();
		p = new ProcedimientoDTO(4, "Accion 1", "URL 1", 1);
		ps.add(p);
		procedimientos.add(p);
		p = new ProcedimientoDTO(5, "Accion 2", "URL 2", 1);
		ps.add(p);
		procedimientos.add(p);
		p = new ProcedimientoDTO(3, "Accion 3 (ASD)", "URL 3", 1);
		ps.add(p);
		p = new ProcedimientoDTO(7, "Accion 4", "URL 4", 1);
		ps.add(p);
		procedimientos.add(p);
		p = new ProcedimientoDTO(8, "Accion 5", "URL 5", 1);
		ps.add(p);
		procedimientos.add(p);
		m = new MetasDTO("Quiero aprender a hablar swahili", false, "Aprender", "Idioma", "Basico", ps, usr.getLogin());
		metas.add(m);
		
		ps = new ArrayList<ProcedimientoDTO>();
		p = new ProcedimientoDTO(9, "Accion 1", "URL 1", 1);
		ps.add(p);
		procedimientos.add(p);
		p = new ProcedimientoDTO(10, "Accion 2", "URL 2", 1);
		ps.add(p);
		procedimientos.add(p);
		p = new ProcedimientoDTO(11, "Accion 3", "URL 3", 1);
		ps.add(p);
		procedimientos.add(p);
		p = new ProcedimientoDTO(12, "Accion 4", "URL 4", 1);
		ps.add(p);
		procedimientos.add(p);
		p = new ProcedimientoDTO(13, "Accion 5", "URL 5", 1);
		ps.add(p);
		procedimientos.add(p);
		m = new MetasDTO("Quiero bajar 1 kilo", false, "Ejercicitar", "Bajar de Peso", "Leve", ps, usr.getLogin());
		metas.add(m);
	}
	
	public static BDTest getInstance() {
		if (instance == null) {
			instance = new BDTest();
		}
		return instance;
	}
	
	public boolean isLoggedIn(UsuarioDTO usuario) throws ComunicacionException, LoggedInException {
		if (usr.getToken()!=usuario.getToken()) {
			return true;
		}
		else {
			throw new LoggedInException("No esta logueado");
		}
	}
	
	public void login(UsuarioDTO usuario) throws LoggedInException, ComunicacionException {
		usr.setToken(usuario.getToken());
	}
	
	public UsuarioDTO listarPerfil(UsuarioDTO usuario) throws LoggedInException, ComunicacionException {
		if (isLoggedIn(usuario)) {
			return usuario;
		}
		else {
			throw new LoggedInException("No esta logueado");
		}
	}

	public void modificarPerfil(UsuarioDTO usuario) throws LoggedInException, ComunicacionException {
		if (isLoggedIn(usuario)) {
			this.usr.setVarDispHoraria(usuario.getVarDispHoraria());
			this.usr.setVarFechaNac(usuario.getVarFechaNac());
			this.usr.setVarUbicacion(usuario.getVarUbicacion());
		}
		else {
			throw new LoggedInException("No esta logueado");
		}
	}

	public void altaMeta(UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException, LoggedInException {
		if (isLoggedIn(usuario)) {
			metas.add(meta);
			for (ProcedimientoDTO p : procedimientos) cargarProc(p);
		}
	}
	
	public ArrayList<MetasDTO> listarMetas (UsuarioDTO usuario) throws ComunicacionException, LoggedInException {
		if (isLoggedIn(usuario)) {
			return metas;
		}
		else {
			throw new LoggedInException("No esta logueado");
		}
	} 
	
	public ArrayList<ProcedimientoDTO> listarProcedimiento (UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException, LoggedInException {
		if (isLoggedIn(usuario)) {
			return procedimientos;
		}
		else {
			throw new LoggedInException("No esta logueado");
		}
	}
	
	private void cargarProc(ProcedimientoDTO p) {
		for (ProcedimientoDTO proc : procedimientos) {
			if (p.getId() == proc.getId()) {
				return;
			}
		}
		procedimientos.add(p);
	}
	
}
