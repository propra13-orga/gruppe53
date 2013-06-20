package progprak.gruppe53.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import progprak.gruppe53.game.GameLogic;

public class Server implements Runnable {

	GameLogic gameLogic;
	ServerSocket serverSocket;
	private Socket client;
	private ObjectInputStream clientObjectIn;
	
	public Server() {
		gameLogic = new GameLogic();
		try {
			serverSocket = new ServerSocket(6116);
			client = serverSocket.accept();
			clientObjectIn = new ObjectInputStream(client.getInputStream());
			System.out.println(client);
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
		while(true){
			try {
				System.out.println(clientObjectIn.readObject());
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
