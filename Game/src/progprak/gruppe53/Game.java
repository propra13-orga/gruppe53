package progprak.gruppe53;

import java.util.ListIterator;

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
	
	private GameLogic gameLogic;
	
	

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
		keyboardInput = new KeyboardInput();
		gameLogic = new GameLogic(this);
		gamePanel = new GamePanel();
		frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
		frame.addKeyListener(keyboardInput);
		frame.pack();
		frame.setVisible(true);
		last = System.nanoTime();


		
	}

	@Override
	public void run() {
		started = true;
		while(started){
			try {
				computeDelta();
				
				gameLogic.doLogic(delta);
				
				
				gameLogic.move(delta);
				
				gamePanel.render(delta,gameLogic.getSprites());



				gamePanel.repaint();
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}
		

    public CollisionEvent testForCollision(double maxX,double minX,double maxY,double minY) {
    	for(ListIterator<Sprite> it = gameLogic.getSprites().listIterator();it.hasNext();){
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
		gameLogic = new GameLogic(this);
	}



	public KeyboardInput getKeyboardInput() {
		return keyboardInput;
	}
}
