package progprak.gruppe53.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import progprak.gruppe53.game.Player;
import progprak.gruppe53.serverOld.ServerResponse;

public class ClientConnection implements Runnable {



	private int id;
	private Server server;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Player player;
	private ServerResponse serverResponse;

	public ClientConnection(Socket client, int id, Server server) {
		this.socket = client;
		this.id = id;
		this.server = server;
		doInitializations();
	}

	private void doInitializations(){
		server.getServerWindow().log("Client" + id + ": init");
		try {
			server.getServerWindow().log("Client" + id + ": Opening OOS");
			oos = new ObjectOutputStream(socket.getOutputStream());
			server.getServerWindow().log("Client" + id + ": Opened OOS");
			server.getServerWindow().log("Client" + id + ": Opening OIS");
			ois = new ObjectInputStream(socket.getInputStream());
			server.getServerWindow().log("Client" + id + ": Opened OIS");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(socket.isConnected()){
			try {
				server.getServerWindow().log("Client" + id + ": wait for Data");
				player = (Player) ois.readObject();
				server.getServerWindow().log("Client" + id + ": recieved: " + player);
				server.getServerWindow().log("Client" + id + ": send Data");
				oos.writeObject(serverResponse);
				oos.reset();
				server.getServerWindow().log("Client" + id + ": finished Sending");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void send(ServerResponse serverResponse) {
		this.serverResponse = serverResponse;
	}

}
