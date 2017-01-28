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

public class DGGui {

	private JFrame mainFrame;
	private JButton connectButton;
	private JTextField playerNameInput;
	private JTextField playerIPInput;
	private JTextField yourNumber;
	private JTextField otherPlayerNumber;
	private String name;
	private String ip;
	private int number;
	private Random rnd = new Random();

	public DGGui() throws IOException {
		initialize();
	}

	private void initialize() throws IOException {
		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(new Color(102, 204, 204));
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Client\\kuva.png"));
		mainFrame.setResizable(false);
		mainFrame.setTitle("DiceGame");
		mainFrame.setSize(317, 231);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		
		//Buttons
		
		connectButton = new JButton("Connect");
		connectButton.setBounds(36, 152, 203, 23);
		mainFrame.getContentPane().add(connectButton);
		
		//Labels
		
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
		
		//Text Fields
		
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

		//Action listeners
		
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
	 * Main
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
