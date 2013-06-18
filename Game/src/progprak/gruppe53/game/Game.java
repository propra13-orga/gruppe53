package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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
	
	private String startLevel = "levels/Level10.xml";


	private Menu menu;

	private boolean alive = true;

	private InfoWindow infoWindow;
	
	private ShopPanel shop;

	private JLayeredPane mainPane;

	private SpeechPane speechPane;
	
	public Game() {
		doInitalizations();
	}


	private void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		
	}


	private void doInitalizations() {
		keyboardInput = new KeyboardInput();
		mainPane = new JLayeredPane();
		gamePanel = new GamePanel();
		menu = new Menu(this);
		infoWindow = new InfoWindow(this);		
		gameLogic = new GameLogic(this);
		mainPane.setPreferredSize(gamePanel.getPreferredSize());
		gamePanel.setLocation(0, 0);
		gamePanel.setSize(gamePanel.getPreferredSize());
		mainPane.add(gamePanel,new Integer(1));
		shop = new ShopPanel(this);
		shop.setLocation(mainPane.getPreferredSize().width/2 - shop.getPreferredSize().width/2,mainPane.getPreferredSize().height/2 - shop.getPreferredSize().height/2);
		shop.setSize(shop.getPreferredSize());
		mainPane.add(shop,new Integer(0));
		speechPane = new SpeechPane();
		speechPane.setPreferredSize(new Dimension(400,100));
		speechPane.setLocation(mainPane.getPreferredSize().width/2-speechPane.getPreferredSize().width/2,100);
		speechPane.setSize(speechPane.getPreferredSize());
		speechPane.setBackground(Color.BLACK);
		speechPane.setVisible(false);
		mainPane.add(speechPane,new Integer(9));
		frame = new JFrame("Game");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainPane);
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
					//gameLogic.getHero().recoverMana();
				}
				gamePanel.render(delta,gameLogic.getActors());
				menu.render();
				infoWindow.render();
				gamePanel.repaint();
				speechPane.render();
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
		infoWindow.getInventoryPanel().resetInventory();
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


	/**
	 * @return the shop
	 */
	public ShopPanel getShop() {
		return shop;
	}


	public void showShop() {
		infoWindow.getInventoryPanel().slotsSell();
		mainPane.setLayer(shop, 2);
	}


	public void hideShop() {
		infoWindow.getInventoryPanel().slotsUse();
		mainPane.setLayer(shop, 0);
	}
	public void showSpeechPane(String text){
		speechPane.setText(text);
		speechPane.setShow(true);
	}


	public void hideSpeechPane() {
		speechPane.setShow(false);
	}

}
