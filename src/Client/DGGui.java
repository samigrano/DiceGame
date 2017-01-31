package Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * Graaffinen k�ytt�liittym� asiakkaalle, sis�lt�� my�s main metodin asiakkaan puolelta.
 * 
 *
 */
public class DGGui {

	private JFrame mainFrame;
	private JButton connectButton;
	private JTextField playerNameInput;
	private JTextField playerIPInput;
	private static JTextField yourNumber;
	private static JTextField otherPlayerNumber;
	private String name;
	private String ip;
	private int number;
	private Random rnd = new Random();

	/**
	 * Konstruktori
	 * @throws IOException
	 */
	public DGGui() throws IOException {
		initialize();
	}
	/**
	 * Metodissa alustetaan graaffinen k�ytt�littym� asiakkaalle
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(new Color(102, 204, 204));
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Client\\dice.png"));
		mainFrame.setResizable(false);
		mainFrame.setTitle("DiceGame");
		mainFrame.setSize(317, 231);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		
		//Luodaan Connect-nappi jota hy�dynnet��n serveriin yhdist�miseen
		
		connectButton = new JButton("Connect");
		connectButton.setBounds(36, 152, 203, 23);
		mainFrame.getContentPane().add(connectButton);
		
		//Luodaan k�ytt�liittym�n labelit, joilla esitet��n k�ytt�j�lle teksti� tai kuvia.
		
		JLabel ipLabel = new JLabel("IP:");
		ipLabel.setBounds(65, 59, 23, 14);
		mainFrame.getContentPane().add(ipLabel);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(48, 34, 40, 14);
		mainFrame.getContentPane().add(nameLabel);

		JLabel showPlayer1 = new JLabel("YOU");		
		showPlayer1.setBounds(36, 153, 40, 20);
		mainFrame.getContentPane().add(showPlayer1);
		
		JLabel showPlayer2 = new JLabel("ENEMY");
		showPlayer2.setBounds(132, 153, 50, 20);
		mainFrame.getContentPane().add(showPlayer2);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src\\Client\\dice.png"));
		lblNewLabel.setBounds(169, 11, 133, 141);
		mainFrame.getContentPane().add(lblNewLabel);
		
		//Luodaan tekstikent�t joihin asiakas kirjoittaa vastauksensa, sek�
		//joilla n�ytet��n serverin l�hett�m�t luvut k�ytt�j�lle
		
		playerIPInput = new JTextField();
		playerIPInput.setBounds(88, 56, 71, 20);
		playerIPInput.setText("127.0.0.1");
		mainFrame.getContentPane().add(playerIPInput);
		playerIPInput.setColumns(10);
		mainFrame.getContentPane().add(playerIPInput);
		
		playerNameInput = new JTextField();
		playerNameInput.setBounds(88, 31, 71, 20);
		playerNameInput.setColumns(10);
		mainFrame.getContentPane().add(playerNameInput);

		yourNumber = new JTextField();
		yourNumber.setBounds(79, 153, 40, 20);
		mainFrame.getContentPane().add(yourNumber);
		yourNumber.setEditable(false);
		
		otherPlayerNumber = new JTextField();
		otherPlayerNumber.setBounds(179, 153, 40, 20);
		mainFrame.getContentPane().add(otherPlayerNumber);
		otherPlayerNumber.setEditable(false);

		/**Luodaan napille Connect kuuntelija. Nappia painaessa 
		*  tallennetaan k�ytt�j�n nimi, antama IP(vakiona localhost) ja alustetaan 
		*  k�ytt�j�lle numero peli� varten.
		*  N�m� parametrit l�hetet��n clientImplementation luokalle.
		*  @param name
		*  @param ip
		*  @param number
		*/
		
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					name = playerNameInput.getText();
					ip = playerIPInput.getText();
					number = rnd.nextInt(6)+1;
					DGClientImplementation.init(name, ip, number);
	
					connectButton.setVisible(false);
					playerNameInput.setEditable(false);
					playerIPInput.setEditable(false);
				}
				catch (Exception e){	
				}
			}
		});		
	}
	/**
	 * Asetetaan toisen k�ytt�j�n pelinumero k�ytt�liittym��n
	 * @param number
	 */
	
	public static void appendEnemyNumber(int number) {
		otherPlayerNumber.setText(""+number);
	}
	/**
	 * Asetetaan oma pelinumero k�ytt�littym��n
	 * @param number
	 */
	public static void appenOwnNumber(int number) {
		yourNumber.setText(""+number);
	}
	
	/**
	 * Asiakkaan main-metodi
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DGGui window = new DGGui();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
