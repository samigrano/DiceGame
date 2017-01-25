package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class DGGui {

	private JFrame frmDicegame;
	private JButton playButton;
	private JButton connectButton;
	private JTextField playerName;
	private JTextField playerIP;
	private JTextField yourNumber;
	private JTextField OtherPlayerNumber;
	private String name;
	private String ip;
	private int number;
	private Random rnd = new Random();

	/**
	 * Ohjelman pää luokka
	 */
	public DGGui() {
		initialize();
	}

	/**
	 * initialize metodi
	 */
	private void initialize() {
		frmDicegame = new JFrame();
		frmDicegame.setResizable(false);
		frmDicegame.setTitle("DiceGame");
		frmDicegame.setBounds(100, 100, 349, 215);
		frmDicegame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDicegame.getContentPane().setLayout(null);
		
		
		////////////////////////////////////////////////////////////////////
		///         		Ohjelman napit
		///////////////////////////////////////////////////////////////////		
		
		playButton = new JButton("PLAY");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Tähän play napin toiminnalisuudet
				//Nyt näyttää erillisellä popup ikkunalla voititko vai hävisitkö
				//Syöte voitosta ja häviöstä tulee "You Win/Lose" tekstin paikalle
				JOptionPane.showMessageDialog(null, "You Win/Lose");
			}
		});
		
		connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					name = playerName.getText();
					ip = playerIP.getText();
					number = rnd.nextInt(6)+1;
					DGClientImplementation.init(name, ip, number);
					//Virheen kaappaus jos nimi kentässä tai ip kentässä virheitä syötteessä
					//Yhteys napin toiminnalisuudet tähän
				}
				catch (Exception e){
					
				}
				
			}
		});
		
		playButton.setBounds(120, 117, 89, 23);
		frmDicegame.getContentPane().add(playButton);
		
		connectButton.setBounds(234, 32, 89, 23);
		frmDicegame.getContentPane().add(connectButton);
		
		////////////////////////////////////////////////////////////////////
		///         		Tekstin syöttö kentät
		///////////////////////////////////////////////////////////////////
		
		playerName = new JTextField();
		playerName.setBounds(42, 33, 86, 20);
		frmDicegame.getContentPane().add(playerName);//Pelaajan nimi 
		playerName.setColumns(10);
		
		playerIP = new JTextField();
		playerIP.setHorizontalAlignment(SwingConstants.CENTER);
		playerIP.setText("127.0.0.1");
		playerIP.setBounds(138, 33, 86, 20);
		frmDicegame.getContentPane().add(playerIP);//Pelaajan ip (vakio localhost)
		playerIP.setColumns(10);
		
		yourNumber = new JTextField();
		yourNumber.setBounds(42, 118, 45, 20);
		frmDicegame.getContentPane().add(yourNumber);
		yourNumber.setColumns(10);
		
		OtherPlayerNumber = new JTextField();
		OtherPlayerNumber.setBounds(238, 118, 45, 20);
		frmDicegame.getContentPane().add(OtherPlayerNumber);
		OtherPlayerNumber.setColumns(10);
		
		////////////////////////////////////////////////////////////////////
		///         Näyttää labelit framessa missä mitäkin
		////////////////////////////////////////////////////////////////////
		
		JLabel showPlayer1 = new JLabel("YOU");
		showPlayer1.setFont(new Font("Ravie", Font.BOLD, 15));
		showPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		showPlayer1.setBounds(33, 92, 54, 14);
		frmDicegame.getContentPane().add(showPlayer1);
		
		JLabel showPlayer2 = new JLabel("ENEMY");
		showPlayer2.setFont(new Font("Ravie", Font.BOLD, 15));
		showPlayer2.setBounds(220, 88, 88, 23);
		frmDicegame.getContentPane().add(showPlayer2);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(42, 11, 46, 14);
		frmDicegame.getContentPane().add(nameLabel);
		
		JLabel ipLabel = new JLabel("IP:");
		ipLabel.setBounds(138, 8, 46, 14);
		frmDicegame.getContentPane().add(ipLabel);
		
	}
	
	/**
	 * Tää main on väliakainen testausta varten
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DGGui window = new DGGui();
					window.frmDicegame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
