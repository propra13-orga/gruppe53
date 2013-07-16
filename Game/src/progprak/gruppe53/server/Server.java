package progprak.gruppe53.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Player;
import progprak.gruppe53.sprites.Hero;

public class Server implements Runnable {

	/**
	 * @param args
	 */

	private JFrame frame;
	private ServerWindow serverWindow;
	private boolean started = false;
	private ArrayList<ClientConnection> clients;
	private volatile int clientCount = 0;
	private GameLogic gameLogic;

	public Server() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(1200, 0);
		serverWindow = new ServerWindow(this);
		frame.add(serverWindow);
		frame.pack();
		frame.setVisible(true);
		doInitializations();
	}

	public void doInitializations(){
		serverWindow.log("Startup");
		started = true;
		gameLogic = new GameLogic();
		gameLogic.addHero(new Hero(0, 0, gameLogic));
		gameLogic.switchLevel("levels/TestLevel.xml");
		clients = new ArrayList<ClientConnection>();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				ServerSocket servSock = null;
				try {
					serverWindow.log("Opening ServerSocket");
					servSock = new ServerSocket(12108);
					serverWindow.log("Socket open on:" + servSock.getInetAddress());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(started){
					try {
						serverWindow.log("Waiting for Incoming Connection");
						Socket client = servSock.accept();
						serverWindow.log("Incoming Connection from:" + client.getInetAddress());
						clients.add(new ClientConnection(client,clientCount,Server.this));
						Thread t = new Thread(clients.get(clientCount));
						t.start();
						if(clientCount > 0){
							gameLogic.addHero(new Hero((int)gameLogic.getHero().getX(), (int)gameLogic.getHero().getY(), gameLogic), clientCount);
						}
						clientCount++;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		Thread t2 = new Thread(this);
		t2.start();
	}

	public static void main(String[] args) {
		new Server();
	}

	public ServerWindow getServerWindow() {
		return serverWindow;
	}

	@Override
	public void run() {
		Player player1 = null;
		Player player2 = null;
		while(true){
			if(clientCount > 0){
				player1 = clients.get(0).getPlayer();
				if(clients.size() > 1 && clients.get(1) != null){
					player2 = clients.get(1).getPlayer();
				}
				if(player1 != null){
					gameLogic.tick(0, player1, player2);
				}
				clients.get(0).send(new ServerResponse(gameLogic.getActors(0), gameLogic.getHero(0),serverWindow.getChatMessages()));
				if(clients.size() > 1 && clients.get(1) != null){
					clients.get(1).send(new ServerResponse(gameLogic.getActors(1), gameLogic.getHero(1),serverWindow.getChatMessages()));
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
