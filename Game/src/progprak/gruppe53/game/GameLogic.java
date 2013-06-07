package progprak.gruppe53.game;

import java.util.ListIterator;
import java.util.Vector;

public class GameLogic {
	
	private Vector<Sprite> sprites;
	private Game game;
	
	private Sword sword;
	private Jacket jacket;
	
	
	public Hero hero;
	

	public GameLogic(Game game) {
		sprites =  new Vector<Sprite>();
		this.game = game;
		doInitalizations();
	}
	
	private void doInitalizations(){
		hero = new Hero(0, 0, game, sword, jacket);
		
	}
	
	//Do Logics for every Sprite-Object
	public void doLogic(long delta){
		for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.doLogic(delta);
		}
	}
	public void move(long delta){
		for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.move(delta);
		}
	}
	public Vector<Sprite> getSprites() {
		return sprites;
	}

	public void switchLevel(String newLevel) {
		Vector<Sprite> sp = new Vector<Sprite>();
		LevelLoaderSax.generateLevel(newLevel,sp, game);
		sprites = sp;
	}

	public Hero getHero() {
		return hero;
	}
}