package progprak.gruppe53;

public class Game implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new Game());
		t.start();

	}

	
	private boolean started = false;
	
	public Game() {
		initalize();
	}



	private void initalize() {
		started = true;
		
	}


	@Override
	public void run() {
		while(started){
			System.out.println("-");
		}
		
	}
}