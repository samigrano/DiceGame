package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class DGImplementation extends UnicastRemoteObject implements DiceGame {
	
	protected DGImplementation() throws RemoteException {
	}

	@Override
	public String sayHello() throws RemoteException {
		String s = "Hello";
		return s;
		
	}
	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("dice", new DGImplementation());
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
