package progprak.gruppe53;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class InfoWindow extends JPanel {

	private static final long serialVersionUID = 8189052008771431526L;

	private Game game;

	private JProgressBar healthBar;
	
	public InfoWindow(Game game) {
		this.game = game;
		doInitalizations();
	}

	private void doInitalizations() {
		setBackground(Color.WHITE);
		healthBar = new JProgressBar();
		healthBar.setForeground(Color.RED);
		add(healthBar,BorderLayout.NORTH);
		Dimension ps = healthBar.getPreferredSize();
		ps.width = 800;
		healthBar.setPreferredSize(ps);
		
		setPreferredSize(getPreferredSize());
	}

	public void render() {
		healthBar.setMaximum(game.getGameLogic().getHero().getMaxHealth());
		healthBar.setValue(game.getGameLogic().getHero().getHealth());
	}

}
