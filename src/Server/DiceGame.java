package Server;
/**
 * Pelin rajapinta serverin ja asiakkaan väliseen kommunikointiin.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.sun.org.apache.regexp.internal.recompile;

import Client.Player;

public interface DiceGame extends Remote {
	
    public void initPlayer(Player player) throws RemoteException;
    public void sendMessage(String s) throws RemoteException;
    public String getMessage() throws RemoteException;
    public boolean isGameOn() throws RemoteException;
    public int giveEnemyNumber(String name) throws RemoteException;
}

