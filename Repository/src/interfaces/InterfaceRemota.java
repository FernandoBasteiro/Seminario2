package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dto.MetasDTO;
import dto.ProcedimientoDTO;
import dto.RecomendacionesDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

public interface InterfaceRemota extends Remote {

	public boolean isLoggedIn(UsuarioDTO usuario) throws RemoteException, ComunicacionException;

	public void crearUsuario(UsuarioDTO usuario) throws RemoteException, ComunicacionException;
	
	public void modificarUsuario(UsuarioDTO usuario) throws RemoteException, ComunicacionException;
	
	public UsuarioDTO login(UsuarioDTO usuario) throws RemoteException, LoggedInException;
	
	public void altaMeta(UsuarioDTO usuario, MetasDTO meta) throws RemoteException, ComunicacionException, LoggedInException;
	
	public ArrayList<MetasDTO> listarMetas (UsuarioDTO usuario) throws RemoteException, ComunicacionException, LoggedInException;
	
	public RecomendacionesDTO listarProcedimiento (UsuarioDTO usuario, MetasDTO meta) throws RemoteException, ComunicacionException, LoggedInException; 
	
	public void modificarPerfil(UsuarioDTO usuario) throws RemoteException, ComunicacionException, LoggedInException;
	
	public UsuarioDTO listarPerfil(UsuarioDTO usuario) throws RemoteException, ComunicacionException, LoggedInException;

	public Boolean existeUsuario(UsuarioDTO usuario) throws RemoteException, ComunicacionException;

	public void crearProcedimiento(UsuarioDTO usuario, MetasDTO meta, ProcedimientoDTO proc) throws RemoteException, ComunicacionException, LoggedInException;
}
