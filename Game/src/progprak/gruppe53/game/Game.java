package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.JFrame;

public class Game implements Runnable {
	
	
	public static void main(String[] args) {
		new Game();
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
	
	private String startLevel = "levels/TestLevel.xml";


	private Menu menu;

	private boolean alive = true;

	private InfoWindow infoWindow;

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
		menu = new Menu(this);
		infoWindow = new InfoWindow(this);
		frame = new JFrame("Game");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
		frame.add(menu,BorderLayout.NORTH);
		frame.add(infoWindow,BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyboardInput);
		last = System.nanoTime();
		gameLogic.switchLevel(startLevel);
	}

	@Override
	public void run() {
		started = true;
		while(started){
			try {
				computeDelta();
				
				if(alive){
					gameLogic.doLogic(delta);
				
				
					gameLogic.move(delta);
				
				}
				gamePanel.render(delta,gameLogic.getActors());
				menu.render();
				infoWindow.render();
				gamePanel.repaint();
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}
		

    public Vector<Sprite> testForCollision(double maxX,double minX,double maxY,double minY, double dx, double dy) {
    	Vector<Sprite> cs = new Vector<Sprite>();
    	for(ListIterator<Sprite> it = gameLogic.getActors().listIterator();it.hasNext();){
			Sprite s = it.next();
			if(s instanceof Collidable && collisionContains(s, maxX+dx, minX+dx, maxY, minY)){
				((Collidable)s).getCollisionEvent().setDirection(CollisionEvent.DIRECTION_HORIZONTAL);
				cs.add(s);
			}
			if(s instanceof Collidable && collisionContains(s, maxX, minX, maxY+dy, minY+dy)){
				((Collidable)s).getCollisionEvent().setDirection(CollisionEvent.DIRECTION_VERTICAL);
				cs.add(s);
			}
		}
		return cs;
	}
    private boolean collisionContains(Sprite s,double maxX,double minX,double maxY,double minY){
		if(
			s.contains(maxX,maxY)
			|| s.contains(maxX,minY)
			|| s.contains(minX,maxY)
			|| s.contains(minX,minY)
		){
			return true;
		}
		else return false;
    }

	public void restart() {
		gameLogic = new GameLogic(this);
		gameLogic.switchLevel(startLevel);
		alive = true;
	}



	public KeyboardInput getKeyboardInput() {
		return keyboardInput;
	}


	public void switchLevel(String newLevel) {
		gameLogic.switchLevel(newLevel);
	}


	public GameLogic getGameLogic() {
		return gameLogic;
	}


	public void startGame() {
		if(!started){
			Thread t = new Thread(this);
			t.setName("Gameloop");
			t.start();
		}
	}


	public boolean isStarted() {
		return started;
	}
	public boolean isAlive(){
		return alive;
	}


	public void loose() {
		alive = false;
	}
	
	public InfoWindow getInfoWindow(){
		return infoWindow;
	}

}
