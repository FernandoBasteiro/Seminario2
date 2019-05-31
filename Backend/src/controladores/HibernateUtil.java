package controladores;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.MetasEntity;
import entities.ProcedimientoEntity;
import entities.UsuarioEntity;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(UsuarioEntity.class);
             config.addAnnotatedClass(ProcedimientoEntity.class);
             config.addAnnotatedClass(MetasEntity.class);
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {	
        return sessionFactory;
    }
}