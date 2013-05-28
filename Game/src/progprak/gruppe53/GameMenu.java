package progprak.gruppe53;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameMenu extends JPanel {

	private static final long serialVersionUID = -8905247101110071179L;

	
	private Game game;


	private JButton startBtn;


	private JButton restartBtn;
	
	public GameMenu(Game game) {
		super();
		this.game = game;
		doInitializations();
	}

	private void doInitializations() {
		setPreferredSize(new Dimension(800,40));
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
		add(restartBtn);
	}
	
	public void render(){
		restartBtn.setEnabled(!game.isAlive());
	}
}
