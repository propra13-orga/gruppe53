package progprak.gruppe53;

import java.util.ListIterator;
import java.util.Vector;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Game implements Runnable, KeyListener {

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
	
	//Directions
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	//Movementspeed
	private int speed = 2;
	
	
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
		frame.addKeyListener(this);
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
		sprites.add(hero = new Hero(10,10));
	}



	@Override
	public void run() {
		started = true;
		while(started){
			try {
				computeDelta();
				
				for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
					Sprite s = it.next();
					s.doLogic(delta);
				
				}
				
				for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
					Sprite s = it.next();
					s.move(delta);
				}
				gamePanel.render(delta,sprites);
				checkKeys();
				doLogic();
				gamePanel.render(delta,sprites);
				
				gamePanel.repaint();
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_UP)
			up=true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			down=true;
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			left=true;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			right=true;
	}
	
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_UP)
			up=false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			down=false;
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			left=false;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			right=false;
	}

	
	public void keyTyped(KeyEvent e) {
	
		
	}
	
	private void checkKeys()
	{
		if(up)
			hero.moveVertical(-speed);
		if(down)
			hero.moveVertical(speed);
		if(right)
			hero.moveHorizontal(speed);
		if(left)
			hero.moveHorizontal(-speed);
	}
	
	//Do Logics for every Sprite-Object
	private void doLogic()
	{
		for(ListIterator<Sprite> it = gamePanel.sprites.listIterator(); it.hasNext();)
		{
			Sprite r = it.next();
			r.doLogic();
		}
	}

}
