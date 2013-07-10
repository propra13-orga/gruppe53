package progprak.gruppe53.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import progprak.gruppe53.serverOld.ServerResponse;

public class ServerConnectionOld {

	private Socket serverSocket;
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private Player player;
	private ServerResponse serverResponse;

	public ServerConnectionOld(Socket serverSocket) {
		this.serverSocket = serverSocket;

		try {
			objectOut = new ObjectOutputStream(serverSocket.getOutputStream());
			objectIn = new ObjectInputStream(serverSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doInitializations();

	}


	private void doInitializations() {
		Thread rec = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
					try {
						serverResponse = (ServerResponse) objectIn.readObject();
						System.out.println(serverResponse);
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		Thread sen = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
					if(player != null){
						try {
							objectOut.writeObject(player);
							objectOut.reset();
							System.out.println("test");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		rec.start();
		sen.start();		
	}



	/**
	 * @return the player
	 */
	public synchronized Player getPlayer() {
		return player;
	}


	public void send(Player player) {
		this.player = player;
	}


	public ServerResponse getServerResponse() {
		return serverResponse;
	}

}
