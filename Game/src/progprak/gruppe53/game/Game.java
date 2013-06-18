package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

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
	 * The main Frame
	 */
	private GameFrame gameFrame;
	
	private GameLogic gameLogic;
	
	

	private KeyboardInput keyboardInput;
	
	private String startLevel = "levels/Level1.xml";



	private boolean alive = true;


	
	public Game() {
		doInitalizations();
	}


	private void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		
	}


	private void doInitalizations() {
		keyboardInput = new KeyboardInput();
		gameFrame = new GameFrame("Game", this);
		gameLogic = new GameLogic(this);
		gameFrame.setVisible(true);
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
				gameFrame.render(delta,gameLogic.getActors());
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}
		

	public Vector<Sprite> testForCollision(Sprite a){
		Vector<Sprite> cs = new Vector<Sprite>();
		for(ListIterator<Sprite> it = gameLogic.getActors().listIterator();it.hasNext();){
			Sprite s = it.next();
			if(a!=s){
				if(s instanceof Collidable && s.intersects(a.getHorizontalCollsionRect())){
					((Collidable)s).getCollisionEvent().setDirection(CollisionEvent.DIRECTION_HORIZONTAL);
					cs.add(s);
				}
				if(s instanceof Collidable && s.intersects(a.getVerticalCollsionRect())){
					((Collidable)s).getCollisionEvent().setDirection(CollisionEvent.DIRECTION_VERTICAL);
					cs.add(s);
				}
			}
		}
		return cs;
	}


	public void restart() {
		gameLogic = new GameLogic(this);
		gameLogic.switchLevel(startLevel);
		gameFrame.getInfoWindow().getInventoryPanel().resetInventory();
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
	

	public void showShop() {
		gameFrame.getInfoWindow().getInventoryPanel().slotsSell();
		gameFrame.getMainPane().setLayer(gameFrame.getShop(), 2);
	}


	public void hideShop() {
		gameFrame.getInfoWindow().getInventoryPanel().slotsUse();
		gameFrame.getMainPane().setLayer(gameFrame.getShop(), 0);
	}
	public void showSpeechPane(String text){
		gameFrame.getSpeechPane().setText(text);
		gameFrame.getSpeechPane().setShow(true);
	}


	public void hideSpeechPane() {
		gameFrame.getSpeechPane().setShow(false);
	}


	public void win() {
		showSpeechPane("Du hast gewonnen!");
		alive = false;
	}


	/**
	 * @return the gameFrame
	 */
	public GameFrame getGameFrame() {
		return gameFrame;
	}

}
