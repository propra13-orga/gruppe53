package progprak.gruppe53.server;

import java.io.Serializable;
import java.util.ArrayList;
import progprak.gruppe53.sprites.Sprite;
import progprak.gruppe53.sprites.characters.Hero;

public class ServerResponse implements Serializable {


	private static final long serialVersionUID = 7232893035781983031L;
	private ArrayList<Sprite> actors;
	private Hero hero;
	private ArrayList<String> chatMessages;

	public ServerResponse(ArrayList<Sprite> arrayList, Hero hero, ArrayList<String> chatMessages) {
		this.actors = arrayList;
		this.hero	= hero;
		this.chatMessages = chatMessages;
	}

	/**
	 * @return the actors
	 */
	public ArrayList<Sprite> getActors() {
		return actors;
	}

	/**
	 * @return the hero
	 */
	public Hero getHero() {
		return hero;
	}
	
	public ArrayList<String> getChatMessages() {
		return chatMessages;
	}
}
