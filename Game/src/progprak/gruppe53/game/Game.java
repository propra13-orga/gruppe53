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

	
	private boolean client = false;

	private Socket clientSocket;

	private ObjectOutputStream oos;

	private ObjectInputStream ois;

	private ArrayList<String> chatMessages;



	/****
	 * Main Constructor
	 */
	public Game() {
		doInitalizations();
	}


	private void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		
	}


	private void doInitalizations() {
		player = new Player();
		chatMessages = new ArrayList<String>();
		player.addChat(chatMessages);
		gameLogic = new GameLogic();
		gameFrame = new GameFrame("Game", this);
		gameLogic.addHero(new Hero(0, 0, gameLogic));
		gameFrame.setVisible(true);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(player.getKeyboardInput());
		last = System.nanoTime();
		gameLogic.switchLevel(startLevel);

	}

	@Override
	/***
	 * The GameLoop, almost everything happens in here
	 */
	public void run() {
		started = true;
		ArrayList<Sprite> actors = null;
		Hero hero = null;
		ServerResponse sr = null;
		while(started){
			try {
				computeDelta();
				if(alive){
					if(client){
						try {							
							oos.writeObject(player);
							chatMessages.clear();
							oos.reset();
							sr = (ServerResponse) ois.readObject();
						} catch (IOException | ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					gameLogic.tick(delta,player,null);
					actors = gameLogic.getActors();
					hero = gameLogic.getHero();
					if(sr!=null){
						actors = sr.getActors();
						hero = sr.getHero();
						gameFrame.setChat(sr.getChatMessages());
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
		

	/**
	 * Restart the Game
	 */
	public void restart() {
		gameLogic = new GameLogic();
		gameLogic.addHero(new Hero(0, 0, gameLogic));
		gameLogic.switchLevel(startLevel);
		//gameFrame.getInfoWindow().getInventoryPanel().resetInventory();
		alive = true;
	}

	/**
	 * Load another Level
	 * @param newLevel Path to the new Level
	 */
	public void switchLevel(String newLevel) {
		gameLogic.switchLevel(newLevel);
	}

	/**
	 * Starts the Game(what a surprise)
	 */
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
	
	/**
	 * 
	 * @return the gameLogic
	 */
	public GameLogic getGameLogic(){
		return gameLogic;
	}

	/**
	 * Sets the Chat
	 */
	public void chat(String text) {
		chatMessages.add(text);		
	}


	/**
	 * Run as Client
	 * @param server URI of the Server
	 */
	public void setClient(String server) {
		client = true;
		try {
			clientSocket = new Socket(server,12108);
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
