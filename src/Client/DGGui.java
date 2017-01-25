package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class DGGui {

	private JFrame frmDicegame;
	private JPanel diceRollInfo;
	private JButton playButton;
	private JButton connectButton;
	private JTextField playerName;
	private JTextField playerIP;
	private JTextField yourNumber;
	private JTextField otherPlayerNumber;
	private String name;
	private String ip;
	private int number;
	private Random rnd = new Random();

	/**
	 * Ohjelman p�� luokka
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
		
		frmDicegame.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel ipLabel = new JLabel("IP:");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		frmDicegame.add(ipLabel, c);
		
		
		playerIP = new JTextField();
		playerIP.setHorizontalAlignment(SwingConstants.CENTER);
		playerIP.setText("127.0.0.1");
		frmDicegame.getContentPane().add(playerIP);//Pelaajan ip (vakio localhost)
		playerIP.setColumns(10);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		frmDicegame.add(playerIP, c);

		JLabel nameLabel = new JLabel("Name:");
	
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		frmDicegame.add(nameLabel, c);

		playerName = new JTextField();
		playerName.setBounds(42, 33, 86, 20);
		playerName.setColumns(10);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		frmDicegame.add(playerName, c);
		
		connectButton = new JButton("Connect");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		frmDicegame.add(connectButton, c);
		
		diceRollInfo = new JPanel();
		diceRollInfo.setLayout(new GridLayout(2,2));
		
		yourNumber = new JTextField();
		yourNumber.setEditable(false);
		
		otherPlayerNumber = new JTextField();
		otherPlayerNumber.setEditable(false);
		
		
		JLabel showPlayer1 = new JLabel("YOU");
		showPlayer1.setFont(new Font("Ravie", Font.BOLD, 15));
		
		JLabel showPlayer2 = new JLabel("ENEMY");
		showPlayer2.setFont(new Font("Ravie", Font.BOLD, 15));
		
		diceRollInfo.add(yourNumber);
		diceRollInfo.add(otherPlayerNumber);
		diceRollInfo.add(showPlayer1);
		diceRollInfo.add(showPlayer2);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		frmDicegame.add(diceRollInfo, c);
		
		playButton = new JButton("PLAY");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//T�h�n play napin toiminnalisuudet
				//Nyt n�ytt�� erillisell� popup ikkunalla voititko vai h�visitk�
				//Sy�te voitosta ja h�vi�st� tulee "You Win/Lose" tekstin paikalle
				JOptionPane.showMessageDialog(null, "You Win/Lose");
			}
		});
		
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					name = playerName.getText();
					ip = playerIP.getText();
					number = rnd.nextInt(6)+1;
					DGClientImplementation.init(name, ip, number);
					//Virheen kaappaus jos nimi kent�ss� tai ip kent�ss� virheit� sy�tteess�
					//Yhteys napin toiminnalisuudet t�h�n
					connectButton.setVisible(false);
					playerName.setEditable(false);
					playerIP.setEditable(false);
				}
				catch (Exception e){
					
				}
			}
		});		
	}
	
	/**
	 * T�� main on v�liakainen testausta varten
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
