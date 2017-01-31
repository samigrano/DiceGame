package Client;

import java.io.Serializable;

/**
 * Luokasta luodaan pelaaja olio, joka l‰hetet‰‰n RMI-yhteydell‰ serverille
 * 
 */

public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int number;
	private String name;
	private String ip;

	/**
	 * Konstruktori
	 * @param name
	 * @param ip
	 * @param number
	 */
	
	public Player(String name, String ip, int number) {
		this.name = name;
		this.ip = ip;
		this.number = number;
		
	}
	/**
	 * Havaintometodi numerolle
	 * @return
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * Asetus metodi numerolle 
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * Havaintometodi nimelle
	 * @return
	 */

	public String getName() {
		return name;
	}
	/**
	 * Asetus metodi nimelle
	 * @param name
	 */

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Havaintometodi ip-osoitteelle
	 * @return
	 */

	public String getIp() {
		return ip;
	}
	/**
	 * Asetus metodi ip-osoitteelle
	 * @param ip
	 */

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	

}
