package progprak.gruppe53.serverOld;

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
	private Server server;
	private boolean started;

	public ClientConnection(Socket clientSocket,int id,Server server) {
		this.id = id;
		this.clientSocket = clientSocket;
		this.server = server;

		try {
			objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
			server.log("client" + id + ": ObjectInputStream");
			objectIn = new ObjectInputStream(clientSocket.getInputStream());
			server.log("client" + id + ": ObjectOutputStream");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doInitializations();

	}


	private void doInitializations() {
		started = true;
		Thread rec = new Thread(new Runnable() {

			@Override
			public void run() {
				while(started){
					try {
						//System.out.println("rec:" + id);
						server.log("client" + id + ": start Rec");
						player = (Player) objectIn.readObject();
						server.log("client" + id + ": end Rec");
						Thread.sleep(1);
					} catch (ClassNotFoundException | IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		Thread sen = new Thread(new Runnable() {

			@Override
			public void run() {
				while(started){
					try{
						//System.out.println("sen:" + id);
						if(serverResponse != null){
							server.log("client" + id + ": start Send");
							objectOut.writeObject(serverResponse);
							objectOut.reset();
							server.log("client" + id + ": end Send");
							Thread.sleep(1);
						}
					}
					catch (IOException | InterruptedException e) {
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


	public void close() {
		started = false;
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
