package Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import interfaces.InterfaceRemota;
import remoto.ObjetoRemoto;

public class iniciarServidor {
	public iniciarServidor() throws RemoteException{
		inicializar();
	}
	
	public static void main(String[] args) {
		try {
			new iniciarServidor();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void inicializar() throws RemoteException {
		
		InterfaceRemota or = new ObjetoRemoto();
		
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//127.0.0.1/FAR", or);
			System.out.println("Iniciado");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
