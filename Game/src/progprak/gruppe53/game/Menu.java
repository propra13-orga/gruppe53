package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JPanel {

	private static final long serialVersionUID = -8905247101110071179L;

	
	private Game game;


	private JButton startBtn;

	private JButton restartBtn;
	
	private JButton clientBtn;
	
	private JTextField server;
	
	
	public Menu(Game game) {
		super();
		this.game = game;
		doInitializations();
	}

	private void doInitializations() {
		//setPreferredSize(new Dimension(800,40));
		setBackground(Color.WHITE);
		startBtn = new JButton("Start Game");
		startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.startGame();
				startBtn.setEnabled(false);
			}
		});
		add(startBtn,BorderLayout.NORTH);
		
		restartBtn = new JButton("Restart Game");
		restartBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.restart();
			}
		});
		restartBtn.setEnabled(false);
		add(restartBtn);
		
		clientBtn = new JButton("client");
		clientBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.setClient(server.getText());
				clientBtn.setEnabled(false);
			}
		});
		add(clientBtn);
		server = new JTextField(10);
		add(server);
		
		setPreferredSize(getPreferredSize());
	}
	
	public void render(){
		restartBtn.setEnabled(!game.isAlive());
	}
}
