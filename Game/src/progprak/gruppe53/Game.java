package progprak.gruppe53;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

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
		gameLogic.switchLevel("levels/Level2.xml");
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
		gameLogic.switchLevel("levels/Level1.xml");
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
