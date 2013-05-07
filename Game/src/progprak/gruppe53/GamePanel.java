package progprak.gruppe53;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -2238063440895511947L;
	
	/*
	 * frames per second
	 */
	private long fps = 0;
	
	/*
	 * All Sprites to draw on the panel
	 */
	private Vector<Sprite> sprites;
	
	public GamePanel() {
		super();
		doInitializations();
	}

	private void doInitializations() {
		this.setPreferredSize(new Dimension(800,600));
		sprites = new Vector<Sprite>();
		for(int i=0;i<10;i++){
			sprites.add(new Wall(100 + i*16,100));
		}
		for(int i=0;i<9;i++){
			sprites.add(new Wall(100,116 + i*16));
		}
		for(int i=0;i<9;i++){
			sprites.add(new Wall(116 +i*16,244));
		}
		
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.drawString("fps:" + Long.toString(fps), 10, 10);
		
		for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.drawObjects(g);
		}
	}

	public void render(long delta) {
		fps = ((long)1e9)/delta;
	}
	

}
