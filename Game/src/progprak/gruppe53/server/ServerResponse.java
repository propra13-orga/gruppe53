package progprak.gruppe53.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Vector;

import progprak.gruppe53.sprites.EnemyGhost;
import progprak.gruppe53.sprites.Hero;
import progprak.gruppe53.sprites.Sprite;

public class ServerResponse implements Serializable {


	private static final long serialVersionUID = 7232893035781983031L;
	private ArrayList<Sprite> actors;
	private Hero hero;

	public ServerResponse(ArrayList<Sprite> arrayList, Hero hero) {
		this.actors = arrayList;
		this.hero	= hero;
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
}