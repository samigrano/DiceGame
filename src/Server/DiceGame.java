package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Client.Player;

public interface DiceGame extends Remote {
	
    public String sayHello() throws RemoteException;
    public int rollNumber() throws RemoteException;
    public void initPlayer(Player player) throws RemoteException;
}

