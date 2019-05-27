package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.MetasEntity;
import entities.ProcedimientoEntity;
import excepciones.ComunicacionException;
import negocio.Metas;
import negocio.Procedimiento;

public class ProcedimientoDAO {
	private static ProcedimientoDAO instancia;

	private ProcedimientoDAO() {}
	
	public static ProcedimientoDAO getInstancia() {
		if (instancia == null) {
			instancia = new ProcedimientoDAO();
		}
		return instancia;
	}
		
	public Procedimiento toNegocio(ProcedimientoEntity pe) {
		Procedimiento p = new Procedimiento();
		p.setId(pe.getId());
		p.setDescripcion(pe.getDescripcion());
		p.setUrl(pe.getUrl());
		p.setDuracion(pe.getDuracion());
		return p;
	}
}
