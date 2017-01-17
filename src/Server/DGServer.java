package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DGServer {

	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
			DiceImplementation dice = new DiceImplementation();
			reg.rebind("dice", dice);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	} // main

} 

