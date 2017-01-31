package Server;

/**
 * Serverin p‰‰luokka
 * Sis‰lt‰‰ pelin logiikan ja yhteyden luomisen asiakkaan ja serverin v‰lille 
 * k‰ytt‰en RMI:t‰.
 * 
 * Kyseess‰ on noppapeli, jossa kumpikin asiakas heitt‰‰ noppaa jonka tahkojen arvot ovat 1-6.
 * Suuremman luvun saanut pelaaja voittaa.
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import Client.DGClientImplementation;
import Client.Player;

public class DGImplementation extends UnicastRemoteObject implements DiceGame {
	
	Random rnd = new Random();
	static ArrayList<Player> players = new ArrayList<>();
	String winnerName;
	
	/**
	 * Konstruktori
	 * @throws RemoteException
	 */
	protected DGImplementation() throws RemoteException {
	}
	/**
	 * Main metodi serverille.
	 * Metodissa luodaan rekisteri johon asiakas voi ottaa yhteyden.
	 * Metodissa myˆs toiminto sille, ett‰ metodi odottaa ennen pelin luontia, jotta 
	 * 2 asiakasta on luonut yhteyden serverille.
	 * @param args
	 */
	public static void main(String[] args) {
		Registry reg = null;
		
		try {
			reg = LocateRegistry.createRegistry(1099);
			reg.rebind("dice", new DGImplementation());
			while (!isPlayersConnected()) {
				Thread.sleep(2000);
				System.out.println("Waiting for players...");
			}
			
			System.out.println("Player: "+checkWinner()+" wins!");
			//System.exit(0);
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	/**
	 * Metodissa lis‰t‰‰n pelaaja ArrayListaan yhdist‰misen j‰lkeen
	 */
	@Override
	public void initPlayer(Player player) throws RemoteException {
		players.add(player);
		System.out.println("hepp "+player.getName());
	}
	/**
	 * Metodilla voidaan testata onko kummatkin asiakkaat yhdistetty serverille
	 * @return
	 */
	public static boolean isPlayersConnected() {
		if (players.size() > 1) return true;
		else return false;
	}
	/**
	 * Metodilla voidaan hakea voittaja pelaajien listalta.
	 * @return
	 */
	public static String checkWinner() {
		int winnerValue = 0;
		String winnerName = null;
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player "+players.get(i).getName() +": "+players.get(i).getNumber());
			if ((players.get(i).getNumber() == winnerValue)){
				System.out.println("draw");
				//System.exit(0);
			}
			else if(players.get(i).getNumber() > winnerValue) {
				winnerValue = players.get(i).getNumber();
				winnerName =  players.get(i).getName();
			}
		}
		return winnerName;
	}
	/**
	 * Metodilla voidaan l‰hett‰‰ viesti asiakkaan ja serverin v‰lisesti
	 */
	@Override
	public void sendMessage(String s) throws RemoteException {
		System.out.println(s);	
		
	}
	/**
	 * Metodilla voidaan vastaan ottaa viesti asiakkaan ja serverin v‰lisesti
	 */
	@Override
	public String getMessage() throws RemoteException {
		return checkWinner();
	}
	/**
	 * Metodilla voidaan testata onko peli viel‰ k‰ynniss‰.
	 */
	@Override
	public boolean isGameOn() throws RemoteException {
		if (isPlayersConnected()) return true;
		else return false;
	}
	/**
	 * Metodilla voidaan l‰hett‰‰ asiakkaalle toisen asiakkaan saama luku
	 */
	@Override
	public int giveEnemyNumber(String name) throws RemoteException {
		int enemyNumber = 0;
		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).getName().equals(name)) {
				enemyNumber = players.get(i).getNumber();
			}
		}
		return enemyNumber;
		
	}
	
	


}
