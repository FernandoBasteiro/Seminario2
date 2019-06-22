package dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.MetasEntity;
import entities.ProcedimientoEntity;
import excepciones.ComunicacionException;
import negocio.Metas;
import negocio.Procedimiento;
import negocio.Usuario;

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
		return new Procedimiento(pe.getId(), pe.getDescripcion(), pe.getUrl(), pe.getDuracion(), pe.getCantCalif(), pe.getSumaCalif(), pe.getEsPromo());
	}

	public ArrayList<Procedimiento> listarProcedimientos(Usuario u, Metas m) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		Query q = session.createQuery("from ProcedimientoEntity"); //TODO Hay que cambiar todo para que pueda buscar por meta y usuario
		//q.setParameter(0, user);
		@SuppressWarnings("unchecked")
		ArrayList<ProcedimientoEntity> pes = (ArrayList<ProcedimientoEntity>) q.list();
		
		ArrayList<Procedimiento> ps = new ArrayList<Procedimiento>();
		for (ProcedimientoEntity pe : pes) ps.add(ProcedimientoDAO.getInstancia().toNegocio(pe));
		return ps;
	}
	
	public Procedimiento buscarProcedimiento(int id) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from ProcedimientoEntity where id = ?"); //TODO Hay que cambiar todo para que pueda buscar por meta y usuario
		q.setParameter(0, id);
		ProcedimientoEntity pe = (ProcedimientoEntity) q.uniqueResult();
		return this.toNegocio(pe);
	}

	public Integer crear(Procedimiento p) {
		ProcedimientoEntity pe = new ProcedimientoEntity();
		pe.setDescripcion(p.getDescripcion());
		pe.setUrl(p.getUrl());
		pe.setDuracion(p.getDuracion());
		pe.setEsPromo(p.getEsPromo());
		pe.setCantCalif(p.getCantCalif());
		pe.setSumaCalif(p.getSumaCalif());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Integer numero = (Integer)session.save(pe);
		session.getTransaction().commit();
		session.close();
		return numero;	
	}

	public void guardar(Procedimiento p) {
		ProcedimientoEntity pe = new ProcedimientoEntity();
		pe.setId(p.getId());
		pe.setDescripcion(p.getDescripcion());
		pe.setUrl(p.getUrl());
		pe.setDuracion(p.getDuracion());
		pe.setEsPromo(p.getEsPromo());
		pe.setCantCalif(p.getCantCalif());
		pe.setSumaCalif(p.getSumaCalif());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(pe);
		session.getTransaction().commit();
		session.close();
	}
}
