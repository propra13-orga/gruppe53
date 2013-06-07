package progprak.gruppe53.game;

import java.util.ArrayList;
import java.util.ListIterator;

public class GameLogic {
	
	private ArrayList<Sprite> actors;
	private ArrayList<Sprite> sprites;
	private Game game;
	
	
	public Hero hero;
	

	public GameLogic(Game game) {
		sprites =  new ArrayList<Sprite>();
		this.game = game;
		doInitalizations();
	}
	
	private void doInitalizations(){
		hero = new Hero(0, 0, game, new Sword() , null);
		
	}
	
	//Do Logics for every Sprite-Object
	public void doLogic(long delta){
		actors = (ArrayList<Sprite>) sprites.clone();
		for(ListIterator<Sprite> it = actors.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.doLogic(delta);
			if (s instanceof CombatObject) {
				((CombatObject) s).testForCollision(game);
				
			}
		}
	}
	public void move(long delta){
		for(ListIterator<Sprite> it = actors.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.move(delta);
		}
	}
	public ArrayList<Sprite> getActors() {
		return actors;
	}

	public void switchLevel(String newLevel) {
		ArrayList<Sprite> sp = new ArrayList<Sprite>();
		LevelLoaderSax.generateLevel(newLevel,sp, game);
		sprites = sp;
		sp.add(hero.getWeapon());
	}

	public Hero getHero() {
		return hero;
	}

	public void removeSprite(Sprite s) {
		sprites.remove(s);
	}
}