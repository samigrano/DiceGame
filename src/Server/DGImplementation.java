package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import Client.Client;
import Client.Player;

public class DGImplementation extends UnicastRemoteObject implements DiceGame {
	Random rnd = new Random();
	static ArrayList<Player> players = new ArrayList<>();
	protected DGImplementation() throws RemoteException {
	}

	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("dice", new DGImplementation());
			
			while (!isPlayersConnected()) {
				Thread.sleep(2000);
				System.out.println("Waiting for players...");
			}
			
			System.out.println("Player: "+checkWinner()+" wins!");
			System.exit(0);
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	@Override
	public void initPlayer(Player player) throws RemoteException {
		players.add(player);
		System.out.println("hepp "+player.getName());
	}
	
	public static boolean isPlayersConnected() {
		if (players.size() > 1) return true;
		else return false;
	}
	
	public static String checkWinner() {
		int winnerValue = 0;
		String winnerName = null;
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player "+players.get(i).getName() +": "+players.get(i).getNumber());
			if ((players.get(i).getNumber() == winnerValue)){
				System.out.println("draw");
				System.exit(0);
			}
			else if(players.get(i).getNumber() > winnerValue) {
				winnerValue = players.get(i).getNumber();
				winnerName =  players.get(i).getName();
			}
		}
		return winnerName;
	}


}
