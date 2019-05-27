package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import controladores.AdministrarMetas;
import controladores.AdministrarProcedimientos;
import controladores.AdministrarUsuarios;
import dto.MetasDTO;
import dto.ProcedimientoDTO;
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

	public void crearUsuario(UsuarioDTO usuario) throws ComunicacionException {
		AdministrarUsuarios.getInstancia().crearUsuario(usuario);
	}
	
	public void modificarUsuario(UsuarioDTO usuario) throws ComunicacionException {
		AdministrarUsuarios.getInstancia().modificarUsuario(usuario);
	}
	
	public void login(UsuarioDTO usuario) throws LoggedInException {
		AdministrarUsuarios.getInstancia().login(usuario);
	}
	
	public void altaMeta(UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException {
		AdministrarMetas.getInstancia().altaMeta(usuario, meta);
	}
	
	public ArrayList<MetasDTO> listarMetas (UsuarioDTO usuario) throws ComunicacionException, LoggedInException{
		return AdministrarMetas.getInstancia().listarMetas(usuario);
	}
	
	public ArrayList<ProcedimientoDTO> listarProcedimiento (UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException, LoggedInException{
		return AdministrarProcedimientos.getInstancia().listarProcedimiento(usuario, meta);
	}
}
