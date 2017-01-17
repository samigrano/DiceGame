package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DiceImplementation extends UnicastRemoteObject implements Dice {
	
	protected DiceImplementation() throws RemoteException {
	}

	@Override
	public String sayHello() throws RemoteException {
		String s = "Hello";
		return s;
		
	}
}
