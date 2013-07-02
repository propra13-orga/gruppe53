package progprak.gruppe53.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import progprak.gruppe53.game.Player;

public class ClientConnection{

	private Socket clientSocket;
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private Player player;
	private ServerResponse serverResponse;
	private int id;

	public ClientConnection(Socket clientSocket,int id) {
		this.id = id;
		this.clientSocket = clientSocket;


		try {
			objectIn = new ObjectInputStream(clientSocket.getInputStream());
			objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
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
						//System.out.println("rec:" + id);
						player = (Player) objectIn.readObject();
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
					try{
						//System.out.println("sen:" + id);
						if(serverResponse != null){
							objectOut.writeObject(serverResponse);
							objectOut.reset();
						}
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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


	public void send(ServerResponse serverResponse) {
		this.serverResponse = serverResponse;
	}

}
