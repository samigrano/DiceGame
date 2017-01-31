package Client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Server.DiceGame;
/**
 * Luokka sis‰lt‰‰ asiakkaan RMI yhdist‰misen serveriin
 * 
 *
 */
public class DGClientImplementation extends UnicastRemoteObject{

	protected DGClientImplementation() throws RemoteException {
	}
	/**
	 * 
	 * @param name
	 * @param ip
	 * @param number
	 */
	public static void init(String name, String ip, int number) {
		String queryName = name;
		int ownNumber = number;
		
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
			setYourNumber(ownNumber);
			setEnemyNumber(enemyNumber);
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	/**
	 * 
	 * @param number
	 */
	public static void setEnemyNumber(int number) {
		DGGui.appendEnemyNumber(number);
	}
	/**
	 * 
	 * @param number
	 */
	public static void setYourNumber(int number) {
		DGGui.appenOwnNumber(number);
	}

}

