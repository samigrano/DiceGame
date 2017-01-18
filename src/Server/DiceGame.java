package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DiceGame extends Remote {
	
    public String sayHello() throws RemoteException;
}

