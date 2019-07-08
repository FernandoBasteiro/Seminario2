package controladores;

import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.LocalDate;

import dao.MetasDAO;
import dao.UsuarioDAO;
import dto.MetasDTO;
import dto.MetasUsrDTO;
import dto.ProcedimientoDTO;
import dto.RecomendacionesDTO;
import dto.UsuarioDTO;
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
		ArrayList<Procedimiento> ps = new ArrayList<Procedimiento>();
		ArrayList<Procedimiento> rps = new ArrayList<Procedimiento>();
		ArrayList<MetasUsrDTO> mDTO = new ArrayList<MetasUsrDTO>();
		
		ArrayList<Usuario> uList = new ArrayList<Usuario>();
		uList = UsuarioDAO.getInstancia().getUsuarioByPerfil(usuario.getVarUbicacion());
		for (Usuario u : uList) {
			LocalDate fNac = ConversorFechas.convertJavaToJoda(usuario.getVarFechaNac());
			if (u.getVarFechaNac() != null && Math.abs(fNac.getYear() - u.getVarFechaNac().getYear()) <= 5) {
				ArrayList<Metas> mList = new ArrayList<Metas>();
				mList = MetasDAO.getInstancia().getListadoMetasByUsuario(u.getLogin());
				for(Metas m : mList) {
					if (m.isCompleta() && meta.getVarAccion().equals(m.getVarAccion())&&meta.getVarNivel().equals(m.getVarNivel())&&meta.getVarSujeto().equals(m.getVarSujeto())) {
						if (u.isActivo()) {
							MetasUsrDTO muDTO = new MetasUsrDTO();
							muDTO.setMeta(m.toDTO());
							muDTO.setUsuario(u.toDTO());
							mDTO.add(muDTO);
						}
						for(Procedimiento p : m.getProcedimientos()) {
							if (p.getEsPromo()) rps.add(p);
							else ps.add(p);	
						}
					}
				}
			}
		}
		ArrayList<ProcedimientoDTO> rpsDTO = ordenarSinDups(rps);
		ArrayList<ProcedimientoDTO> psDTO = ordenarSinDups(ps);
		RecomendacionesDTO rec = new RecomendacionesDTO(rpsDTO, sumaHoras(rpsDTO), psDTO, mDTO);
		return rec;
	}
	
	private Integer sumaHoras(ArrayList<ProcedimientoDTO> procs) {
		Integer suma = 0;
		for (ProcedimientoDTO p : procs) suma = suma + p.getDuracion();
		return suma;
	}
	
	private ArrayList<ProcedimientoDTO> ordenarSinDups(ArrayList<Procedimiento> procs) {
		ArrayList<ProcedimientoDTO> procsDTO = new ArrayList<ProcedimientoDTO>();
		int i = 0;
		while (i < procs.size()) {
			for (int j = procs.size() - 1; j > i; j--) {
				if (procs.get(j).getId() == procs.get(i).getId()) procs.remove(j);
				else if (procs.get(i).getUrl().equals(procs.get(j).getUrl())) {
					procs.get(i).setCantCalif(procs.get(i).getCantCalif() + procs.get(j).getCantCalif());
					procs.get(i).setSumaCalif(procs.get(i).getSumaCalif() + procs.get(j).getSumaCalif());
					procs.remove(j);
				}
			}
			procsDTO.add(procs.get(i).toDTO());
			i++;
		}
		Collections.sort(procsDTO);
		return procsDTO;
	}

	public void crearProcedimiento(UsuarioDTO usuario, MetasDTO meta, ProcedimientoDTO proc) throws LoggedInException, ComunicacionException {
		if (AdministrarUsuarios.getInstancia().isLoggedIn(usuario)) {
			Metas m = MetasDAO.getInstancia().getMetaById(meta.getId());
			Usuario u = AdministrarUsuarios.getInstancia().buscarUsuario(usuario.getLogin());
			Procedimiento p = new Procedimiento(proc.getDescripcion(), proc.getUrl(), proc.getDuracion());
			if (! u.isActivo()) p.setEsPromo(true);
			p.crear();
			m.getProcedimientos().add(p);
			m.guardar();
		}
	}
}
