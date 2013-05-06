package progprak.gruppe53;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2238063440895511947L;
	
	
	private long fps = 0;
	
	public GamePanel() {
		super();
		doInitializations();
	}

	private void doInitializations() {
		this.setPreferredSize(new Dimension(800,600));
		
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.drawString("fps:" + Long.toString(fps), 10, 10);
	}

	public void render(long delta) {
		fps = ((long)1e9)/delta;
	}
	

}
