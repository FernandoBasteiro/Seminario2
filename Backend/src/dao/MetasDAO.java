package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.MetasEntity;
import entities.ProcedimientoEntity;
import excepciones.ComunicacionException;
import negocio.Metas;
import negocio.Procedimiento;

public class MetasDAO {
	private static MetasDAO instancia;

	private MetasDAO() {}
	
	public static MetasDAO getInstancia() {
		if (instancia == null) {
			instancia = new MetasDAO();
		}
		return instancia;
	}
	
	public void grabar(Metas m) {
		MetasEntity me = new MetasEntity(m.getDescripcion(), m.isCompleta(), m.getVarAccion(), m.getVarSujeto(), m.getVarNivel(), m.getUser());
		me.setId(m.getId());
		ArrayList<ProcedimientoEntity> pes = new ArrayList<ProcedimientoEntity>();
		for (Procedimiento p : m.getProcedimientos()) {
			ProcedimientoEntity pe = new ProcedimientoEntity();
			if (p.getId() != 0)	pe.setId(p.getId());
			pe.setDescripcion(p.getDescripcion());
			pe.setDuracion(p.getDuracion());
			pe.setSumaCalif(p.getSumaCalif());
			pe.setCantCalif(p.getCantCalif());
			pe.setEsPromo(p.getEsPromo());
			pe.setUrl(p.getUrl());
			pes.add(pe);
		}
		me.setProcedimientos(pes);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(me);
		session.getTransaction().commit();
		session.close();
	}
	
	public Integer crear(Metas m) {
		MetasEntity me = new MetasEntity(m.getDescripcion(), m.isCompleta(), m.getVarAccion(), m.getVarSujeto(), m.getVarNivel(), m.getUser());
		ArrayList<ProcedimientoEntity> procedimientos = new ArrayList<ProcedimientoEntity>();
		for (Procedimiento p : m.getProcedimientos()) {
			ProcedimientoEntity pe = new ProcedimientoEntity();
			pe.setId(p.getId());
			procedimientos.add(pe);
		}
		me.setProcedimientos(procedimientos);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Integer numero = (Integer)session.save(me);
		session.getTransaction().commit();
		session.close();
		return numero;
	}
	
	public Metas toNegocio(MetasEntity me) {
		Metas m = new Metas(me.getId());
		m.setDescripcion(me.getDescripcion());
		m.setCompleta(me.isCompleta());
		m.setVarAccion(me.getVarAccion());
		m.setVarSujeto(me.getVarSujeto());
		m.setVarNivel(me.getVarNivel());
		m.setUser(me.getUser());
		ArrayList<Procedimiento> procedimientos = new ArrayList<Procedimiento>();
		for(ProcedimientoEntity pe : me.getProcedimientos()) {
			procedimientos.add(ProcedimientoDAO.getInstancia().toNegocio(pe));
		}
		m.setProcedimientos(procedimientos);
		
		return m;
	}
	
	public ArrayList<Metas> getListadoMetasByUsuario(String user) throws ComunicacionException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from MetasEntity where user = ?");
		q.setParameter(0, user);
		@SuppressWarnings("unchecked")
		ArrayList<MetasEntity> mes = (ArrayList<MetasEntity>) q.list();
		
		ArrayList<Metas> ms = new ArrayList<Metas>();
		for (MetasEntity me : mes) ms.add(MetasDAO.getInstancia().toNegocio(me));
		return ms;
	}
	
	public Metas getMetaByUsuarioMeta(String user, String meta) throws ComunicacionException {
		Metas metaF = new Metas();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		MetasEntity me = (MetasEntity) session.createQuery("from MetasEntity where user = ? and descripcion = ?")
				.setParameter(0, user)
				.setParameter(1, meta)
				.uniqueResult();
		if (me != null) {
			metaF = MetasDAO.getInstancia().toNegocio(me);
		}
		return metaF;
	}

	public Metas getMetaById(Integer id) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Metas metaF = null;
		MetasEntity me = (MetasEntity) session.createQuery("from MetasEntity where id = ?")
				.setParameter(0, id)
				.uniqueResult();
		if (me != null) {
			metaF = MetasDAO.getInstancia().toNegocio(me);
		}
		return metaF;
	}

	public ArrayList<Metas> getListadoMetasActivasByUsuario(String user) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from MetasEntity where user = ? and completa = ?");
		q.setParameter(0, user);
		q.setParameter(1, false);
		@SuppressWarnings("unchecked")
		ArrayList<MetasEntity> mes = (ArrayList<MetasEntity>) q.list();
		
		ArrayList<Metas> ms = new ArrayList<Metas>();
		for (MetasEntity me : mes) ms.add(MetasDAO.getInstancia().toNegocio(me));
		return ms;
	}
	
	public ArrayList<Metas> listarTodasLasMetas() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from MetasEntity");
		@SuppressWarnings("unchecked")
		ArrayList<MetasEntity> mes = (ArrayList<MetasEntity>) q.list();	
		ArrayList<Metas> ms = new ArrayList<Metas>();
		for (MetasEntity me : mes) ms.add(MetasDAO.getInstancia().toNegocio(me));
		return ms;
	}
	
}
