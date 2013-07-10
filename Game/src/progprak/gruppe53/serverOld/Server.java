package progprak.gruppe53.serverOld;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Player;
import progprak.gruppe53.sprites.Hero;

public class Server implements Runnable {


	ServerWindow serverWindow;
	JFrame frame;
	GameLogic gameLogic;
	ServerSocket serverSocket;
	private ClientConnection client1;
	private ClientConnection client2;
	private Player player;
	private boolean started = false;

	public Server() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverWindow = new ServerWindow(this);
		frame.add(serverWindow);
		frame.pack();
		frame.setVisible(true);

		/*gameLogic = new GameLogic();
		gameLogic.addHero(new Hero(0, 0, gameLogic),0);
		gameLogic.addHero(new Hero(400, 400, gameLogic),1);
		gameLogic.switchLevel("levels/TestLevel.xml");*/

	}
	public void startServer(){
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					serverSocket = new ServerSocket(6116);
					Socket clientSocket = serverSocket.accept();
					client1 = new ClientConnection(clientSocket,1, Server.this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.start();
		started = true;
	}
	public void stopServer(){
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serverSocket = null;
		if(client1 != null){
			client1.close();
			client1 = null;
		}
		if(client2 != null){
			client2.close();
			client2 = null;
		}
		started = false;
	}


	public static void main(String[] args) {
		Thread t = new Thread(new Server());
		//t.start();
	}

	public void log(String logStr){
		serverWindow.log(logStr);
	}

	@Override
	public void run() {
		long last =  System.nanoTime();
		while(true){
			try {
				//System.out.println((long)1e9/(System.nanoTime() - last));
				//last = System.nanoTime();
				player = client1.getPlayer();
				if(player != null){
					//System.out.println(client2.getPlayer());
					gameLogic.tick(0, player,client2.getPlayer());
				}
				client1.send(new ServerResponse(gameLogic.getActors(0),gameLogic.getHero(0)));
				//client2.send(new ServerResponse(gameLogic.getActors(1),gameLogic.getHero(1)));
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
