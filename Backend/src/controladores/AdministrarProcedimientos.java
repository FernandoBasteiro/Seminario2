package controladores;

import java.util.ArrayList;

import dao.MetasDAO;
import dao.ProcedimientoDAO;
import dto.MetasDTO;
import dto.ProcedimientoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import negocio.Metas;
import negocio.Procedimiento;

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
		Metas m = MetasDAO.getInstancia().getMetaByUsuarioMeta(usuario.getLogin(), meta.getDescripcion());		
		
				
		ArrayList<ProcedimientoDTO> psDTO = new ArrayList<ProcedimientoDTO>();

		return psDTO;
	}

}
