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
	

	PortalEntrance portal;
	Hero hero;
	Trap trap;
	Enemy enemy;
	public Game() {
		doInitalizations();
	}

	public KeyboardInput getKeyboardInput(){
		return keyboardInput;
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
		new LevelLoader("levels/Level1.xml").generateLevel(sprites, this);
		/*
		 * add walls and hero to the panel
		 * !!! for testing !!!
		 */
		
		//Horizontal Walls
		/* no longer needed
		for(int i=0;i<21;i++)
			sprites.add(new Wall(100 +i*16 ,100));
		for(int i=0;i<21;i++)
			sprites.add(new Wall(100 +i*16 ,244));
		//for(int i=0;i<7;i++)
		//	sprites.add(new Wall(132 +i*16 ,132));		
		//Vertical Walls
		for(int i=0;i<8;i++)
			sprites.add(new Wall(100, 116 +i*16));
		for(int i=0;i<6;i++)
			sprites.add(new Wall(260, 116 +i*16));
		for(int i=0;i<8;i++)
			sprites.add(new Wall(420, 116 +i*16));
		*/
		
		sprites.add(portal = new PortalEntrance(244,116));
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
				return ((Collidable)s).getCollisionEvent();
			}
		}
		return null;
	}



	public void restart() {
		System.exit(0);	
	}
}
