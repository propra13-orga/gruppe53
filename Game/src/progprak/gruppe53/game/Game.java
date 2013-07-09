package progprak.gruppe53.game;

import java.awt.KeyboardFocusManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import progprak.gruppe53.server.ServerResponse;
import progprak.gruppe53.sprites.Hero;
import progprak.gruppe53.sprites.Sprite;

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
	
	

	
	private String startLevel = "levels/Level1.xml";



	private boolean alive = true;

	private Player player;

	private ServerConnection server;


	
	private boolean client = false;



	
	public Game() {
		doInitalizations();
	}


	private void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		
	}


	private void doInitalizations() {
		player = new Player();
		gameLogic = new GameLogic();
		gameFrame = new GameFrame("Game", this);
		gameLogic.addHero(new Hero(0, 0, gameLogic));
		gameFrame.setVisible(true);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(player.getKeyboardInput());
		last = System.nanoTime();
		gameLogic.switchLevel(startLevel);
		if (client) {
			try {
				Socket serverSocket = new Socket("localhost", 6116);
				server = new ServerConnection(serverSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		started = true;
		ArrayList<Sprite> actors = null;
		Hero hero = null;
		ServerResponse sr;
		while(started){
			try {
				computeDelta();
				if(alive){
					if (client) {
						server.send(player);
						sr = server.getServerResponse();
						actors = sr.getActors();
						hero = sr.getHero();
					}
					else {
						gameLogic.tick(delta,player,null);
						actors = gameLogic.getActors();
						hero = gameLogic.getHero();
					}
				}
				gameFrame.render(delta,actors,hero);
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}
		

	public void restart() {
		gameLogic = new GameLogic();
		gameLogic.addHero(new Hero(0, 0, gameLogic));
		gameLogic.switchLevel(startLevel);
		//gameFrame.getInfoWindow().getInventoryPanel().resetInventory();
		alive = true;
	}

	public void switchLevel(String newLevel) {
		gameLogic.switchLevel(newLevel);
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
	




	public void win() {
		//showSpeechPane("Du hast gewonnen!");
		alive = false;
	}


	/**
	 * @return the gameFrame
	 */
	public GameFrame getGameFrame() {
		return gameFrame;
	}


	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	public GameLogic getGameLogic(){
		return gameLogic;
	}

}
