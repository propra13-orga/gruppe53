package progprak.gruppe53;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Game implements Runnable {
	
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLocation(800,600);
		JButton start = new JButton("start");
		ActionListener alStart = new ActionListener(
				) {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread(new Game());
				t.setName("GameLoop");
				t.start();
			}
		};
		start.addActionListener(alStart);
		JButton end = new JButton("end");
		ActionListener alEnd = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		end.addActionListener(alEnd);
		f.setLayout(new FlowLayout());
		f.add(start);
		f.add(end);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.pack();
		f.setVisible(true);
		/*Thread t = new Thread(new Game());
		t.start();*/
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

	private Hero hero;

	private GameGui gameGui;

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
		gameGui = new GameGui();
		frame = new JFrame("Game");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 800, 760);
		gamePanel.setBounds(0, 0, 800, 640);
		gameGui.setBounds(0, 640, 800, 120);
		gameGui.setBackground(Color.red);
		frame.add(gamePanel);
		frame.add(gameGui);
		frame.addKeyListener(keyboardInput);
		frame.setVisible(true);
		last = System.nanoTime();
		gameLogic.switchLevel(startLevel);
	}

	@Override
	public void run() {
		started = true;
		while(started){
			try {
				computeDelta();
				
				gameLogic.doLogic(delta);
				
				
				gameLogic.move(delta);
				
				gamePanel.render(delta,gameLogic.getHero().getHealth(),gameLogic.getSprites());



				gamePanel.repaint();
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}
		

    public Vector<Sprite> testForCollision(double maxX,double minX,double maxY,double minY, int dx, int dy) {
    	Vector<Sprite> cs = new Vector<Sprite>();
    	for(ListIterator<Sprite> it = gameLogic.getSprites().listIterator();it.hasNext();){
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
}
