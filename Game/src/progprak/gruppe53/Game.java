package progprak.gruppe53;

import java.util.ListIterator;
import java.util.Vector;
import javax.swing.JFrame;

public class Game implements Runnable {

	public static void main(String[] args) {
		Thread t = new Thread(new Game());
		t.start();
	}

	
	/*
	 * Gameloop Started
	 */
	private boolean started = false;
	
	/*
	 * last Gameloop starttime in nanoseconds
	 */
	private long last;
	
	/*
	 * last Gameloop execution time in nanoseconds
	 */
	private long delta;
	
	/*
	 * The Gamepanel
	 */
	private GamePanel gamePanel;
	
	/*
	 * The main Frame
	 */
	private JFrame frame;
	
	
	private Vector<Sprite> sprites;
	
	private KeyboardInput keyboardInput;
	

	
	Hero hero;

	public Game() {
		doInitalizations();
	}



	private void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		
	}


	private void doInitalizations() {
		sprites =  new Vector<Sprite>();
		gamePanel = new GamePanel();
		frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
		keyboardInput = new KeyboardInput();
		frame.addKeyListener(keyboardInput);
		frame.pack();
		frame.setVisible(true);
		last = System.nanoTime();
		
		/*
		 * add walls and hero to the panel
		 * !!! for testing !!!
		 */
		for(int i=0;i<10;i++){
			sprites.add(new Wall(100 + i*16,100));
		}
		for(int i=0;i<9;i++){
			sprites.add(new Wall(100,116 + i*16));
		}
		for(int i=0;i<9;i++){
			sprites.add(new Wall(116 +i*16,244));
		}
		sprites.add(hero = new Hero(180,180,this,keyboardInput));
	}



	@Override
	public void run() {
		started = true;
		while(started){
			try {
				computeDelta();
				
				doLogic();
				
				for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
					Sprite s = it.next();
					s.move(delta);
				}
				gamePanel.render(delta,sprites);
				
				gamePanel.render(delta,sprites);
				
				gamePanel.repaint();
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
	//Do Logics for every Sprite-Object
	private void doLogic()
	{
		for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.doLogic(delta);
		
		}
	}



	public CollisionEvent testForCollision(double maxX,double minX,double maxY,double minY) {
		for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
			Sprite s = it.next();
			if(
				(
				s.contains(maxX,maxY)
				|| s.contains(maxX,minY)
				|| s.contains(minX,maxY)
				|| s.contains(minX,minY)
				)
				&& s instanceof Collidable){
				return ((Collidable)s).getCollisonEvent();
			}
		}
		return null;
	}



	public void restart() {
		System.exit(0);	
	}
}
