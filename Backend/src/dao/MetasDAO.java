package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controladores.HibernateUtil;
import entities.MetasEntity;
import entities.ProcedimientoEntity;
import entities.UsuarioEntity;
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
			pe.setDescripcion(p.getDescripcion());
			pe.setUrl(p.getUrl());
			pe.setDuracion(p.getDuracion());
			procedimientos.add(pe);
		}
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Integer numero = (Integer)session.save(me);
		session.getTransaction().commit();
		session.close();
		return numero;
	}
	private String descripcion;
	private boolean completa;
	private String varAccion;
	private String varSujeto; 
	private String varNivel;
	private ArrayList<Procedimiento> procedimientos;
	private String user;
	
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
		
		return m;
	}
	
	public ArrayList<Metas> getListadoMetasByUsuario(String user) throws ComunicacionException {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<MetasEntity> mes = (ArrayList<MetasEntity>) session.createQuery("from MetasEntity where user = ? ")
				.setParameter(0, user)
				.list();
		if (mes == null) throw new ComunicacionException("No se encontraron metas");
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
}
