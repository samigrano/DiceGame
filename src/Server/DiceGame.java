package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Client.Player;

public interface DiceGame extends Remote {
	
    public void initPlayer(Player player) throws RemoteException;
    public void sendMessage(String s) throws RemoteException;
    public String getMessage() throws RemoteException;
}

