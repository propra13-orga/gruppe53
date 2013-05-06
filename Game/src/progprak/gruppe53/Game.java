package progprak.gruppe53;

import javax.swing.JFrame;

public class Game implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new Game());
		t.start();

	}

	
	private boolean started = false;
	private long last;
	private long delta;
	private GamePanel gamePanel;
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
				System.out.println(e);
			}
		}
		
	}
}