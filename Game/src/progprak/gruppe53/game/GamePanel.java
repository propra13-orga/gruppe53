package progprak.gruppe53.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JPanel;

import progprak.gruppe53.sprites.Sprite;
import progprak.gruppe53.sprites.characters.Hero;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = -2238063440895511947L;
	
	/*
	 * frames per second
	 */
	private long fps = 0;
	
	/*
	 * All Sprites to draw on the panel
	 */
	private ArrayList<Sprite> sprites;
	

	
	public GamePanel() {
		super();
		doInitializations();
	}

	private void doInitializations() {
		this.setPreferredSize(new Dimension(800,640));
		sprites = new ArrayList<Sprite>();
		
		
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		ArrayList<Sprite> actors = (ArrayList<Sprite>) sprites.clone();
		
		for(ListIterator<Sprite> it = actors.listIterator();it.hasNext();){
			Sprite s = it.next();
			if(s.isDraw()){
				s.drawObjects(g);
			}
		}
		g.setColor(Color.red);
		g.drawString("fps:" + Long.toString(fps), 10, 10);
	}

	public void render(long delta,ArrayList<Sprite> actors, Hero hero) {
		fps = ((long)1e9)/delta;
		this.sprites = actors;
		this.sprites.add(hero);
		repaint();
	}

	public void render(long delta,ArrayList<Sprite> actors) {
		fps = 0;
		this.sprites = actors;
	}

	
}
