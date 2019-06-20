package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import controladores.AdministrarMetas;
import controladores.AdministrarProcedimientos;
import controladores.AdministrarUsuarios;
import dto.MetasDTO;
import dto.RecomendacionesDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;
import interfaces.InterfaceRemota;

public class ObjetoRemoto extends UnicastRemoteObject implements InterfaceRemota{
	private static final long serialVersionUID = 8384110999673649178L;

	public ObjetoRemoto() throws RemoteException {}

	public boolean isLoggedIn (UsuarioDTO usuario) throws RemoteException, ComunicacionException {
		try {
			return AdministrarUsuarios.getInstancia().isLoggedIn(usuario);
		} catch (LoggedInException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void crearUsuario(UsuarioDTO usuario) throws RemoteException, ComunicacionException {
		AdministrarUsuarios.getInstancia().crearUsuario(usuario);
	}
	
	public void modificarUsuario(UsuarioDTO usuario) throws RemoteException, ComunicacionException {
		AdministrarUsuarios.getInstancia().modificarUsuario(usuario);
	}
	
	public UsuarioDTO login(UsuarioDTO usuario) throws RemoteException, LoggedInException {
		return AdministrarUsuarios.getInstancia().login(usuario);
	}
	
	public void altaMeta(UsuarioDTO usuario, MetasDTO meta) throws RemoteException, ComunicacionException, LoggedInException{
		AdministrarMetas.getInstancia().altaMeta(usuario, meta);
	}
	
	public ArrayList<MetasDTO> listarMetas (UsuarioDTO usuario) throws RemoteException, ComunicacionException, LoggedInException{
		return AdministrarMetas.getInstancia().listarMetas(usuario);
	}
	
	public RecomendacionesDTO listarProcedimiento (UsuarioDTO usuario, MetasDTO meta) throws RemoteException, ComunicacionException, LoggedInException{
		return AdministrarProcedimientos.getInstancia().listarProcedimiento(usuario, meta);
	}
	
	public void modificarPerfil(UsuarioDTO usuario) throws RemoteException, ComunicacionException, LoggedInException {
		AdministrarUsuarios.getInstancia().modificarPerfil(usuario);
	}
	
	public UsuarioDTO listarPerfil(UsuarioDTO usuario) throws RemoteException, ComunicacionException, LoggedInException {
		return AdministrarUsuarios.getInstancia().listarPerfil(usuario);
	}
	
	public Boolean existeUsuario(UsuarioDTO usuario) throws RemoteException, ComunicacionException {
		return AdministrarUsuarios.getInstancia().existeUsuario(usuario);
	}
}
