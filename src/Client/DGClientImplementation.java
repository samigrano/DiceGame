package Client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Server.DiceGame;

public class DGClientImplementation extends UnicastRemoteObject{

	protected DGClientImplementation() throws RemoteException {
	}
	
	public static void init(String name, String ip, int number) {
		String queryName = name;
		Player plr = new Player(name, ip, number);
		String address = "rmi://" + ip + ":1099/dice";
		try {
			DiceGame n = (DiceGame) Naming.lookup(address);
			n.initPlayer(plr);
			System.out.println("Winner is " + n.getMessage());
			//System.exit(0);
			while (n.isGameOn() == false) {
				System.out.println("Waiting...");
				Thread.sleep(200);
			}
			int enemyNumber = n.giveEnemyNumber(queryName);
			setEnemyNumber(enemyNumber);
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public static void setEnemyNumber(int number) {
		DGGui.appendEnemyNumber(number);
	}
	
	public static void giveEnemyNumber() {
		
	}

}

