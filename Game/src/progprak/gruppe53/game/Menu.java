package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel {

	private static final long serialVersionUID = -8905247101110071179L;

	
	private Game game;


	private JButton startBtn;

	private JButton restartBtn;
	
	private JButton talentBtn;
	
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
		
		/*talentBtn = new JButton("Talents");
		talentBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
				;
			}
		});
		talentBtn.setEnabled(false);
		add(talentBtn);*/
		
		setPreferredSize(getPreferredSize());
	}
	
	public void render(){
		restartBtn.setEnabled(!game.isAlive());
		//talentBtn.setEnabled(game.isAlive());
	}
}
