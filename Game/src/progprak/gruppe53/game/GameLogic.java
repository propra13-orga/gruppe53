package progprak.gruppe53.game;

import java.util.Vector;
import java.util.ListIterator;

public class GameLogic {
	
	private Vector<Sprite> actors;
	private Vector<Sprite> sprites;
	private Game game;
	
	
	public Hero hero;
	

	public GameLogic(Game game) {
		sprites =  new Vector<Sprite>();
		this.game = game;
		doInitalizations();
	}
	
	private void doInitalizations(){
		hero = new Hero(0, 0, game, new Sword() , null);
		
	}
	
	//Do Logics for every Sprite-Object
	public void doLogic(long delta){
		actors = (Vector<Sprite>) sprites.clone();
		for(ListIterator<Sprite> it = actors.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.doLogic(delta);
			if (s instanceof CombatObject) {
				((CombatObject) s).testForCollision(game);
				((CombatObject) s).resetHandleEvents();
			}
		} 
	}
	public void move(long delta){
		for(ListIterator<Sprite> it = actors.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.move(delta);
		}
	}
	public Vector<Sprite> getActors() {
		return actors;
	}

	public void switchLevel(String newLevel) {
		Vector<Sprite> sp = new Vector<Sprite>();
		LevelLoaderSax.generateLevel(newLevel,sp, game);
		sprites = sp;
		sp.add(hero.getWeapon());
		sp.add(new Sword(60,60));
	}

	public Hero getHero() {
		return hero;
	}

	public void removeSprite(Sprite s) {
		sprites.remove(s);
	}
}