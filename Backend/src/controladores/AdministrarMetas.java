package controladores;

import java.util.ArrayList;

import dao.MetasDAO;
import dao.ProcedimientoDAO;
import dao.UsuarioDAO;
import dto.MetasDTO;
import dto.MetasUsrDTO;
import dto.ProcedimientoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Metas;
import negocio.Procedimiento;
import negocio.Usuario;

public class AdministrarMetas {
	private static AdministrarMetas instancia;

	private AdministrarMetas() { }

	public static AdministrarMetas getInstancia() {
		if (instancia == null) {
			instancia = new AdministrarMetas();
		}
		return instancia;
	}

	public void altaMeta(UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException, LoggedInException{
		Metas m = new Metas(meta.getId(), meta.getDescripcion(), meta.isCompleta(), meta.getVarAccion(), meta.getVarSujeto(), meta.getVarNivel());
		ArrayList<Procedimiento> procedimientos = new ArrayList<Procedimiento>();
		for (ProcedimientoDTO p : meta.getProcedimientos()) {
			Procedimiento po = ProcedimientoDAO.getInstancia().buscarProcedimiento(p.getId());
			procedimientos.add(po);
		}
		m.setProcedimientos(procedimientos);
		m.setUser(usuario.getLogin());
		m.crear();
	}
	
	public ArrayList<MetasDTO> listarMetasActivas (UsuarioDTO usuario) throws ComunicacionException, LoggedInException{
		ArrayList<MetasDTO> msDTO = new ArrayList<MetasDTO>();
		for (Metas m : MetasDAO.getInstancia().getListadoMetasActivasByUsuario(usuario.getLogin())) msDTO.add(m.toDTO());
		return msDTO;
	}

	public ArrayList<MetasDTO> listarMetasTodas (UsuarioDTO usuario) throws ComunicacionException, LoggedInException{
		ArrayList<MetasDTO> msDTO = new ArrayList<MetasDTO>();
		for (Metas m : MetasDAO.getInstancia().getListadoMetasByUsuario(usuario.getLogin())) msDTO.add(m.toDTO());
		return msDTO;
	}

	public void cerrarMeta(UsuarioDTO usuario, MetasDTO metaDTO) throws ComunicacionException, LoggedInException {
		if (AdministrarUsuarios.getInstancia().isLoggedIn(usuario)) {
			Metas meta = MetasDAO.getInstancia().getMetaById(metaDTO.getId());
			for (Procedimiento p : meta.getProcedimientos()) {
				for (ProcedimientoDTO pDTO : metaDTO.getProcedimientos()) {
					if (p.getId() == pDTO.getId()) {
						p.calificar(Math.round(pDTO.getCalificacion()));
						p.guardar();
					}
				}
			}
			meta.setCompleta(true);
			meta.guardar();
		}
	}
	
	public ArrayList<MetasUsrDTO> listarTodasLasMetas() throws ComunicacionException {
		ArrayList<MetasUsrDTO> todo = new ArrayList<MetasUsrDTO>();
		ArrayList<Metas> metas = MetasDAO.getInstancia().listarTodasLasMetas();
		for (Metas m : metas) {
			MetasUsrDTO mu = new MetasUsrDTO();
			mu.setMeta(m.toDTO());
			Usuario u = AdministrarUsuarios.getInstancia().buscarUsuario(m.getUser());
			mu.setUsuario(u.toDTO());
			todo.add(mu);
		}
		return todo;
	}
	
}