package progprak.gruppe53.game;

import java.util.Vector;
import java.util.ListIterator;

import progprak.gruppe53.sprites.CombatObject;
import progprak.gruppe53.sprites.Hero;
import progprak.gruppe53.sprites.Sprite;

public class GameLogic {
	
	private Vector<Sprite> actors;
	private Vector<Sprite> sprites;
	private Game game;
	private String level;
	
	
	private Hero hero;
	private Player player;
	
	
	

	public GameLogic(Game game) {
		sprites =  new Vector<Sprite>();
		this.game = game;
		doInitalizations();
	}
	public void addHero(Hero hero){
		this.hero = hero;
	}
	
	private void doInitalizations(){
		
	}
	
	//Do Logics for every Sprite-Object
	public void doLogic(long delta, Player player){
		this.player = player;
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
		level = newLevel;
		Vector<Sprite> sp = new Vector<Sprite>();
		LevelLoaderSax.generateLevel(newLevel,sp, this);
		sprites = sp;
		if(hero.getWeapon() != null){
			sprites.add(hero.getWeapon());
		}

	}

	public Hero getHero() {
		return hero;
	}

	public void removeSprite(Sprite s) {
		sprites.remove(s);
	}
	public void addSprite(Sprite s){
		if(s!=null){
			sprites.add(s);
		}
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	public void loose() {
		// TODO Auto-generated method stub
		
	}
	public void win() {
		// TODO Auto-generated method stub
		
	}
	public Game getGame(){
		return game;
	}
}
