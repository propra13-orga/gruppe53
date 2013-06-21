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
	private Socket client;
	private ObjectInputStream clientObjectIn;
	private ObjectOutputStream clientObjectOut;
	
	public Server() {
		gameLogic = new GameLogic();
		gameLogic.addHero(new Hero(0, 0, gameLogic));
		gameLogic.switchLevel("levels/TestLevel.xml");
		try {
			serverSocket = new ServerSocket(6116);
			client = serverSocket.accept();
			clientObjectIn = new ObjectInputStream(client.getInputStream());
			clientObjectOut = new ObjectOutputStream(client.getOutputStream());
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
		Player player;
		while(true){
			try {
				player = (Player) clientObjectIn.readObject();
				gameLogic.tick(0, player);
				clientObjectOut.writeObject(new ServerResponse(gameLogic.getActors(),gameLogic.getHero()));
				clientObjectOut.reset();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
