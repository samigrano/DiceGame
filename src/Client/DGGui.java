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

	private JFrame mainFrame;
	private JPanel diceRollInfo;
	private JButton playButton;
	private JButton connectButton;
	private JTextField playerNameInput;
	private JTextField playerIPInput;
	private JTextField yourNumber;
	private JTextField otherPlayerNumber;
	private String name;
	private String ip;
	private int number;
	private Random rnd = new Random();

	public DGGui() {
		initialize();
	}

	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("DiceGame");
		mainFrame.setSize(600, 400);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel ipLabel = new JLabel("IP:");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		mainFrame.add(ipLabel, c);
		
		playerIPInput = new JTextField();
		playerIPInput.setText("127.0.0.1");
		mainFrame.add(playerIPInput);
		playerIPInput.setColumns(10);
		
		c.gridx = 1;
		c.gridy = 0;
		mainFrame.add(playerIPInput, c);

		JLabel nameLabel = new JLabel("Name:");
	
		c.gridx = 0;
		c.gridy = 1;
		mainFrame.add(nameLabel, c);

		playerNameInput = new JTextField();
		playerNameInput.setColumns(10);
		
		c.gridx = 1;
		c.gridy = 1;
		mainFrame.add(playerNameInput, c);
		
		connectButton = new JButton("Connect");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		mainFrame.add(connectButton, c);
		
		diceRollInfo = new JPanel();
		diceRollInfo.setLayout(new GridLayout(2,2));
		
		yourNumber = new JTextField();
		yourNumber.setEditable(false);
		
		otherPlayerNumber = new JTextField();
		otherPlayerNumber.setEditable(false);
		
		JLabel showPlayer1 = new JLabel("YOU");		
		JLabel showPlayer2 = new JLabel("ENEMY");
		
		diceRollInfo.add(yourNumber);
		diceRollInfo.add(otherPlayerNumber);
		diceRollInfo.add(showPlayer1);
		diceRollInfo.add(showPlayer2);
		diceRollInfo.setVisible(false);

		c.gridx = 0;
		c.gridy = 2;
		mainFrame.add(diceRollInfo, c);
			
		playButton = new JButton("PLAY");
		playButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "You Win/Lose");
			}
		});
		
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
					diceRollInfo.setVisible(true);
				}
				catch (Exception e){	
				}
			}
		});		
	}
	
	/**
	 * To be removed, temporarily for testing
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
