package progprak.gruppe53.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Player;
import progprak.gruppe53.sprites.Hero;

public class Server implements Runnable {

	GameLogic gameLogic;
	ServerSocket serverSocket;
	private ClientConnection client1;
	private ClientConnection client2;
	private Player player;
	
	public Server() {
		gameLogic = new GameLogic();
		gameLogic.addHero(new Hero(0, 0, gameLogic),0);
		gameLogic.addHero(new Hero(200, 200, gameLogic),1);
		gameLogic.switchLevel("levels/TestLevel.xml");
		try {
			serverSocket = new ServerSocket(6116);
			Socket clientSocket = serverSocket.accept();
			client1 = new ClientConnection(clientSocket,1);
			Socket clientSocket2 = serverSocket.accept();
			client2 = new ClientConnection(clientSocket2,2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		Thread t = new Thread(new Server());
		t.start();
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
				client2.send(new ServerResponse(gameLogic.getActors(1),gameLogic.getHero(1)));
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
