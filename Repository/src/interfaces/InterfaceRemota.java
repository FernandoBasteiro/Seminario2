package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dto.MetasDTO;
import dto.ProcedimientoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import excepciones.LoggedInException;

public interface InterfaceRemota extends Remote {

	public boolean isLoggedIn(UsuarioDTO usuario) throws RemoteException, ComunicacionException;

	public void crearUsuario(UsuarioDTO usuario) throws ComunicacionException;
	
	public void modificarUsuario(UsuarioDTO usuario) throws ComunicacionException;
	
	public void login(UsuarioDTO usuario) throws LoggedInException;
	
	public void altaMeta(UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException;
	
	public ArrayList<MetasDTO> listarMetas (UsuarioDTO usuario) throws ComunicacionException, LoggedInException;
	
	public ArrayList<ProcedimientoDTO> listarProcedimiento (UsuarioDTO usuario, MetasDTO meta) throws ComunicacionException, LoggedInException; 
}
