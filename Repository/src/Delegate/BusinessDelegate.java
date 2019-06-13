package Delegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dto.MetasDTO;
import dto.ProcedimientoDTO;
import dto.TagMetaDTO;
import dto.UsuarioDTO;
import enumeraciones.TipoTagsMetas;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import interfaces.InterfaceRemota;

public class BusinessDelegate {
	private InterfaceRemota ir;

	private static BusinessDelegate instance;
	private ArrayList<TagMetaDTO> tags;
	

	public static BusinessDelegate getInstance() throws ComunicacionException{

		if(instance==null){
			instance = new BusinessDelegate(); 
		}

		return instance;
	}
	
	private BusinessDelegate() throws ComunicacionException{
		try {
			ir = (InterfaceRemota) Naming.lookup("//127.0.0.1/FAR");
		} catch (MalformedURLException e) {
			throw new ComunicacionException("La direccion especificada no es correcta");
		} catch (RemoteException e) {

		} catch (NotBoundException e) {
			throw new ComunicacionException("El servidor no esta disponible");		
		}
	}

	public boolean isLoggedIn(UsuarioDTO usuario) throws ComunicacionException {
		try {
			return ir.isLoggedIn(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public void crearUsuario(UsuarioDTO usuario) throws ComunicacionException {
		try {
			ir.crearUsuario(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public void modificarUsuario(UsuarioDTO usuario) throws ComunicacionException {
		try {
			ir.modificarUsuario(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public UsuarioDTO login(UsuarioDTO usuario) throws LoggedInException, ComunicacionException {
		try {
			return ir.login(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public void altaMeta(UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException, LoggedInException {
		try {
			ir.altaMeta(usuario, meta);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public ArrayList<MetasDTO> listarMetas (UsuarioDTO usuario) throws ComunicacionException, LoggedInException {
		try {
			return ir.listarMetas(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public ArrayList<ProcedimientoDTO> listarProcedimiento (UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException, LoggedInException {
		try {
			return ir.listarProcedimiento(usuario, meta);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public void modificarPerfil(UsuarioDTO usuario) throws ComunicacionException, LoggedInException {
		try {
			ir.modificarPerfil(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public UsuarioDTO listarPerfil(UsuarioDTO usuario) throws ComunicacionException, LoggedInException {
		try {
			return ir.listarPerfil(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public Boolean existeUsuario(UsuarioDTO usuario) throws ComunicacionException {
		try {
			return ir.existeUsuario(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public ArrayList<TagMetaDTO> getTagsMetas() {
		if (tags == null) {
			tags = new ArrayList<TagMetaDTO>();
			TagMetaDTO tag;
			tag = new TagMetaDTO("Aprender",TipoTagsMetas.Accion);
			tags.add(tag);
			tag = new TagMetaDTO("Perfeccionar",TipoTagsMetas.Accion);
			tags.add(tag);
			tag = new TagMetaDTO("Viajar",TipoTagsMetas.Accion);
			tags.add(tag);
			tag = new TagMetaDTO("Comprar",TipoTagsMetas.Accion);
			tags.add(tag);
			tag = new TagMetaDTO("Logro Personal",TipoTagsMetas.Accion);
			tags.add(tag);
			tag = new TagMetaDTO("Conocer",TipoTagsMetas.Accion);
			tags.add(tag);
			tag = new TagMetaDTO("Ejercitar",TipoTagsMetas.Accion);
			tags.add(tag);
			tag = new TagMetaDTO("Idioma",TipoTagsMetas.Sujeto);
			tags.add(tag);
			tag = new TagMetaDTO("Conducción",TipoTagsMetas.Sujeto);
			tags.add(tag);
			tag = new TagMetaDTO("Deporte",TipoTagsMetas.Sujeto);
			tags.add(tag);
			tag = new TagMetaDTO("Arte",TipoTagsMetas.Sujeto);
			tags.add(tag);
			tag = new TagMetaDTO("Espiritual",TipoTagsMetas.Sujeto);
			tags.add(tag);
			tag = new TagMetaDTO("Academico",TipoTagsMetas.Sujeto);
			tags.add(tag);
			tag = new TagMetaDTO("Laboral",TipoTagsMetas.Sujeto);
			tags.add(tag);
			tag = new TagMetaDTO("Personal",TipoTagsMetas.Sujeto);
			tags.add(tag);
			tag = new TagMetaDTO("Placer",TipoTagsMetas.Sujeto);
			tags.add(tag);
			tag = new TagMetaDTO("Principiante",TipoTagsMetas.Nivel);
			tags.add(tag);
			tag = new TagMetaDTO("Básico",TipoTagsMetas.Nivel);
			tags.add(tag);
			tag = new TagMetaDTO("Intermedio",TipoTagsMetas.Nivel);
			tags.add(tag);
			tag = new TagMetaDTO("Avanzado",TipoTagsMetas.Nivel);
			tags.add(tag);
		}
		return tags;
	}
}
