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
	int playerId = 1;
	protected DGImplementation() throws RemoteException {
	}

	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("dice", new DGImplementation());
			
			while (!isPlayersConnected()) {
				Thread.sleep(500);
				System.out.println("Waiting for players...");
			}
			
			System.out.println("Player: "+checkWinner()+" wins!");
			System.exit(0);
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	@Override
	public String sayHello() throws RemoteException {
		String s = "Hello";
		return s;

	}


	@Override
	public int rollNumber() throws RemoteException {
		int nmbr = rnd.nextInt(6)+1;
		System.out.println(nmbr);
		return nmbr;

	}

	@Override
	public void initPlayer(Player player) throws RemoteException {
		players.add(player);
		player.setId(playerId);
		player.setNumber(rollNumber());
		System.out.println("hepp "+playerId);
		playerId++;
	}
	
	public static boolean isPlayersConnected() {
		if (players.size() > 1) return true;
		else return false;
	}
	
	public static int checkWinner() {
		int winnerValue = 0;
		int winnerId = 0;
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player "+players.get(i).getId()+": "+players.get(i).getNumber());
			if ((players.get(i).getNumber() == winnerValue)){
				System.out.println("draw");
				System.exit(0);
			}
			else if(players.get(i).getNumber() > winnerValue) {
				winnerValue = players.get(i).getNumber();
				winnerId =  players.get(i).getId();
			}
		}
		return winnerId;
	}


}
