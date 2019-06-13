package controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.MetasDAO;
import dao.ProcedimientoDAO;
import dao.UsuarioDAO;
import dto.MetasDTO;
import dto.ProcedimientoDTO;
import dto.UsuarioDTO;
import entities.MetasEntity;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Metas;
import negocio.Procedimiento;
import negocio.Usuario;

public class AdministrarProcedimientos {
	private static AdministrarProcedimientos instancia;

	private AdministrarProcedimientos() { }

	public static AdministrarProcedimientos getInstancia() {
		if (instancia == null) {
			instancia = new AdministrarProcedimientos();
		}
		return instancia;
	}

	public ArrayList<ProcedimientoDTO> listarProcedimiento (UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException, LoggedInException{
		ArrayList<ProcedimientoDTO> psDTO = new ArrayList<ProcedimientoDTO>();
		
		ArrayList<Usuario> uList = new ArrayList<Usuario>();
		uList = UsuarioDAO.getInstancia().getUsuaruiByPerfil(usuario.getVarUbicacion());
		for (Usuario u : uList) {
			ArrayList<Metas> mList = new ArrayList<Metas>();
			mList = MetasDAO.getInstancia().getListadoMetasByUsuario(u.getLogin());
			for(Metas m : mList) {
				if (meta.getVarAccion().equals(m.getVarAccion())&&meta.getVarNivel().equals(m.getVarNivel())&&meta.getVarSujeto().equals(m.getVarSujeto())) {
					for(Procedimiento p : m.getProcedimientos()) {
						psDTO.add(p.toDTO());	
					}
				}
			}
		}
		
		return psDTO;
	}


}
