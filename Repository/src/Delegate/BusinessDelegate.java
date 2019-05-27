package Delegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dto.MetasDTO;
import dto.ProcedimientoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import interfaces.InterfaceRemota;

public class BusinessDelegate {
	private InterfaceRemota ir;

	private static BusinessDelegate instance;

	public static BusinessDelegate getInstance() throws ComunicacionException{

		if(instance==null){
			instance = new BusinessDelegate(); 
		}

		return instance;
	}
	
	private BusinessDelegate() throws ComunicacionException{
		try {
			ir = (InterfaceRemota) Naming.lookup("//127.0.0.1/FARServer");
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
	
	public void login(UsuarioDTO usuario) throws LoggedInException, ComunicacionException {
		try {
			ir.login(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");	
		}
	}
	
	public void altaMeta(UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException {
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
}
