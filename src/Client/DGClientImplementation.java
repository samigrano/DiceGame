package Client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Server.DiceGame;

public class DGClientImplementation extends UnicastRemoteObject implements Client /*,Runnable*/ {

	protected DGClientImplementation() throws RemoteException {
	}
	


	
	public static void init(String name, String ip, int number) {
		Player plr = new Player(name, ip, number);
		String address = "rmi://" + ip + ":1099/dice";
		try {
			DiceGame n = (DiceGame) Naming.lookup(address);
			System.out.println(" ");
			n.initPlayer(plr);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}


}

