package controladores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dto.MetasDTO;
import dto.ProcedimientoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Metas;
import negocio.Procedimiento;
import negocio.Usuario;
import controladores.AdministrarUsuarios;
import dao.MetasDAO;

public class AdministrarMetas {
	private static AdministrarMetas instancia;

	private AdministrarMetas() { }

	public static AdministrarMetas getInstancia() {
		if (instancia == null) {
			instancia = new AdministrarMetas();
		}
		return instancia;
	}

	public void altaMeta(UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException {
		Metas m = new Metas(meta.getId(), meta.getDescripcion(), meta.isCompleta(), meta.getVarAccion(), meta.getVarSujeto(), meta.getVarNivel());
		ArrayList<Procedimiento> procedimientos = null;
		for (ProcedimientoDTO p : meta.getProcedimientos()) {
			Procedimiento po = new Procedimiento(p.getId(), p.getDescripcion(), p.getUrl(), p.getDuracion());
			procedimientos.add(po);
		}
		m.setProcedimientos(procedimientos);
		m.setUser(usuario.getLogin());
		m.crear();
	}
	
	public ArrayList<MetasDTO> listarMetas (UsuarioDTO usuario) throws ComunicacionException, LoggedInException{
		ArrayList<MetasDTO> msDTO = new ArrayList<MetasDTO>();
		for (Metas m : MetasDAO.getInstancia().getListadoMetasByUsuario(usuario.getLogin())) msDTO.add(m.toDTO());
		return msDTO;
	}

	
}