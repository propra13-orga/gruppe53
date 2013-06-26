package progprak.gruppe53.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import progprak.gruppe53.game.Player;

public class ClientConnection implements Runnable{
	
	private Socket clientSocket;
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private Player player;
	private ServerResponse serverResponse;
	
	
	public ClientConnection(Socket clientSocket) {
				
		this.clientSocket = clientSocket;
		
		try {
			objectIn = new ObjectInputStream(clientSocket.getInputStream());
			objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void run() {
		while(true){
			try {
				player = (Player) objectIn.readObject();
				if(serverResponse != null){
					objectOut.writeObject(serverResponse);
					objectOut.reset();
				}
			} 
			catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
