package progprak.gruppe53;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = -2238063440895511947L;
	
	/*
	 * frames per second
	 */
	private long fps = 0;
	
	/*
	 * All Sprites to draw on the panel
	 */
	Vector<Sprite> sprites;
	
	
	
	int speed=10;

	private int health;
	
	public GamePanel() {
		super();
		doInitializations();
	}

	private void doInitializations() {
		this.setPreferredSize(new Dimension(800,640));
		sprites = new Vector<Sprite>();
		
		
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.drawObjects(g);
		}
		g.setColor(Color.red);
		g.drawString("fps:" + Long.toString(fps), 10, 10);
		g.drawString("Health:" + Integer.toString(health), 600, 10);
	}

	public void render(long delta,int health, Vector<Sprite> sprites) {
		fps = ((long)1e9)/delta;
		this.sprites = sprites;
		this.health = health;
	}
	
}
