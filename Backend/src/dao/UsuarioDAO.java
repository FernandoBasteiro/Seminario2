package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.ast.tree.SessionFactoryAwareNode;
import org.hibernate.stat.Statistics;

import controladores.HibernateUtil;
import entities.MetasEntity;
import entities.UsuarioEntity;
import excepciones.ComunicacionException;
import negocio.Metas;
import negocio.Usuario;

public class UsuarioDAO {

private static UsuarioDAO instancia;
	
	
	private UsuarioDAO() {}
		
		public static UsuarioDAO getInstancia() {
			if (instancia == null) {
				instancia = new UsuarioDAO();
			}
			return instancia;
		}
		
		public int crear(Usuario u) {
			UsuarioEntity us =  new UsuarioEntity(u.getLogin(), u.getPwd(), u.getNombre(), u.getToken(), u.isActivo());
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			int numero = (int)session.save(us);
			session.getTransaction().commit();
			session.close();
			return numero;
		}
		
		public void grabar(Usuario u) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			session.saveOrUpdate(this.toEntity(u));
			session.getTransaction().commit();
			session.close();
		}
		
		public void actualizar(Usuario u) throws ComunicacionException {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			UsuarioEntity ue = (UsuarioEntity) session.createQuery("from UsuarioEntity where login = ?")
						.setParameter(0, u.getLogin())
						.uniqueResult();				
			if (ue != null) {
				u.setId(ue.getId());
				u.setLogin(ue.getLogin());
				u.setPwd(ue.getPwd());
				u.setNombre(ue.getNombre());
				u.setToken(ue.getToken());
				u.setVarUbicacion(ue.getVarUbicacion());
				u.setVarDispHoraria(ue.getVarDispHoraria());
				u.setVarNivel(ue.getVarNivel());
				u.setActivo(ue.isActivo());
				u.setVarFechaNac(ue.getVarFechaNac());
			}
			else throw new ComunicacionException("El jugador solicitado no existe");
			session.close();			
		}
		
		public Usuario toNegocio(UsuarioEntity u) {
			Usuario usr = new Usuario(u.getId(), u.getLogin(), u.getPwd(), u.getNombre(), u.getToken(), u.getVarUbicacion(), u.getVarDispHoraria(), u.getVarNivel(), u.isActivo());
			usr.setVarFechaNac(u.getVarFechaNac());
			return usr;
		}
		
		public UsuarioEntity toEntity(Usuario u) {
			UsuarioEntity ue = new UsuarioEntity(u.getId(), u.getLogin(), u.getPwd(), u.getNombre(), u.getToken(), u.getVarUbicacion(), u.getVarDispHoraria(), u.getVarNivel(), u.isActivo());
			ue.setVarFechaNac(u.getVarFechaNac());
			return ue;
		}
	
		public UsuarioEntity getUsuaruiByLogin(String login) throws ComunicacionException {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			UsuarioEntity ue = (UsuarioEntity) session.createQuery("from UsuarioEntity where login = ?")
					.setParameter(0, login)
					.uniqueResult();
			session.close();
			if (ue != null) return ue;

			else throw new ComunicacionException("El usuario solicitado no existe");
		}
		
		public Boolean existeUsuarioByLogin(String login) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			UsuarioEntity ue = (UsuarioEntity) session.createQuery("from UsuarioEntity where login = ?")
					.setParameter(0, login)
					.uniqueResult();
			if (ue != null) return true;
			else return false;
		}
		
		public ArrayList<Usuario> getUsuaruiByPerfil(String ubicacion) throws ComunicacionException {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			Query q = session.createQuery("from UsuarioEntity where varUbicacion = ?");
			q.setParameter(0, ubicacion);
			@SuppressWarnings("unchecked")
			ArrayList<UsuarioEntity> uen = (ArrayList<UsuarioEntity>) q.list();
			
			ArrayList<Usuario> us = new ArrayList<Usuario>();
			for (UsuarioEntity u : uen) us.add(UsuarioDAO.getInstancia().toNegocio(u));
			return us;
		}
}
