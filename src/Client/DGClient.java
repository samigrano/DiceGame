package Client;

import java.rmi.Naming;
import Server.Dice;

public class DGClient {

    public static void main(String[] args) {

        if (args.length != 1){
        	System.out.println("Usage: DiceClient <serverhost>");
			System.exit(0);
        }
    	
    	String address = "rmi://127.0.0.1:1099/dice";
    	try {
			Dice n = (Dice) Naming.lookup(address);
			for (int i = 0; i < 20; i++){
				System.out.print(" " + n.sayHello());
			}
			System.out.println(" ");	
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
    }
}

