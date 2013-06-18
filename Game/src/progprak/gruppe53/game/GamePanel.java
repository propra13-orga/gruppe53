package progprak.gruppe53.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;
import java.util.ListIterator;
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
			if(s.isDraw()){
				s.drawObjects(g);
			}
		}
		g.setColor(Color.red);
		g.drawString("fps:" + Long.toString(fps), 10, 10);
	}

	public void render(long delta,Vector<Sprite> Vector) {
		fps = ((long)1e9)/delta;
		this.sprites = Vector;
	}
	
}
