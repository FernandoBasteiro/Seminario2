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
import dto.RecomendacionesDTO;
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

	public RecomendacionesDTO listarProcedimiento (UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException, LoggedInException{
		ArrayList<ProcedimientoDTO> psDTO = new ArrayList<ProcedimientoDTO>();
		ArrayList<ProcedimientoDTO> rpsDTO = new ArrayList<ProcedimientoDTO>();
		ArrayList<MetasDTO> mDTO = new ArrayList<MetasDTO>();
		
		ArrayList<Usuario> uList = new ArrayList<Usuario>();
		uList = UsuarioDAO.getInstancia().getUsuaruiByPerfil(usuario.getVarUbicacion());
		for (Usuario u : uList) {
			ArrayList<Metas> mList = new ArrayList<Metas>();
			mList = MetasDAO.getInstancia().getListadoMetasByUsuario(u.getLogin());
			for(Metas m : mList) {
				if (meta.getVarAccion().equals(m.getVarAccion())&&meta.getVarNivel().equals(m.getVarNivel())&&meta.getVarSujeto().equals(m.getVarSujeto())) {
					mDTO.add(m.toDTO());
					for(Procedimiento p : m.getProcedimientos()) {
						if (p.getEsPromo()) rpsDTO.add(p.toDTO());
						else psDTO.add(p.toDTO());	
					}
				}
			}
		}
		RecomendacionesDTO rec = new RecomendacionesDTO(ordenarSinDups(rpsDTO), sumaHoras(rpsDTO), ordenarSinDups(psDTO), mDTO);
		return rec;
	}
	
	private Integer sumaHoras(ArrayList<ProcedimientoDTO> procs) {
		Integer suma = 0;
		for (ProcedimientoDTO p : procs) suma = suma + p.getDuracion();
		return suma;
	}
	
	private ArrayList<ProcedimientoDTO> ordenarSinDups(ArrayList<ProcedimientoDTO> procs) {
		int i = 0;
		while (i < procs.size()) {
			for (int j = procs.size()-1; j > i; j--) {
				if (procs.get(j).getId() == procs.get(i).getId()) procs.remove(j);
				else if (procs.get(j).getCalificacion() > procs.get(i).getCalificacion()) {
					ProcedimientoDTO aux = procs.get(i);
					procs.set(i, procs.get(j));
					procs.set(j, aux);
				}
			}
			i++;
		}
		return procs;
	}

	public void crearProcedimiento(UsuarioDTO usuario, MetasDTO meta, ProcedimientoDTO proc) throws LoggedInException, ComunicacionException {
		if (AdministrarUsuarios.getInstancia().isLoggedIn(usuario)) {
			Metas m = MetasDAO.getInstancia().getMetaById(meta.getId());
			Procedimiento p = new Procedimiento(proc.getDescripcion(), proc.getUrl(), proc.getDuracion());
			p.crear();
			m.getProcedimientos().add(p);
			m.guardar();
		}
	}
}
