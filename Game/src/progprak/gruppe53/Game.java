package progprak.gruppe53;

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
	
	public Game() {
		doInitalizations();
	}



	private void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		
	}


	private void doInitalizations() {
		gamePanel = new GamePanel();
		frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
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
				
				gamePanel.render(delta);
				
				gamePanel.repaint();
				Thread.sleep(10);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
}