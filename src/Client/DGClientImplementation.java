package Client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Server.DiceGame;

public class DGClientImplementation extends UnicastRemoteObject implements Client /*,Runnable*/ {

	protected DGClientImplementation() throws RemoteException {
	}
	

	public static void main(String[] args) {

		
	}
	
	public static void init() {
		Player plr = new Player();
		String address = "rmi://127.0.0.1:1099/dice";
		try {
			DiceGame n = (DiceGame) Naming.lookup(address);
			//System.out.print(" " + n.sayHello());
			System.out.println(" ");
			n.initPlayer(plr);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}


}

