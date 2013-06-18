package progprak.gruppe53.game;

import java.util.Vector;
import java.util.ListIterator;

import progprak.gruppe53.items.ClothArmor;
import progprak.gruppe53.items.HealthPotion;
import progprak.gruppe53.items.PinkGlitterWand;
import progprak.gruppe53.items.WoodenSword;

public class GameLogic {
	
	private Vector<Sprite> actors;
	private Vector<Sprite> sprites;
	private Game game;
	private String level;
	
	
	public Hero hero;
	

	public GameLogic(Game game) {
		sprites =  new Vector<Sprite>();
		this.game = game;
		doInitalizations();
	}
	
	private void doInitalizations(){
		hero = new Hero(0, 0, game,game.getGameFrame().getInfoWindow().getInventoryPanel());
		
	}
	
	//Do Logics for every Sprite-Object
	public void doLogic(long delta){
		if(game.getKeyboardInput().isShop()){
			game.showShop();
		}
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
		LevelLoaderSax.generateLevel(newLevel,sp, game);
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
}
