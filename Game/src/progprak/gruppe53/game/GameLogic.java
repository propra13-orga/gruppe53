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
		//hero = new Hero(0, 0, game, new WoodenSword() , new ClothArmor());
		hero = new Hero(0, 0, game,game.getInfoWindow().getInventoryPanel());
		
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
		//sp.add(hero.getWeapon());
		sp.add(new WoodenSword(60,60, game));
		sp.add(new PinkGlitterWand(80,80, game));
		sp.add(new HealthPotion(100, 80,game));
		sp.add(new ClothArmor(60, 40, game));
		//sp.add(new ChargingBoss(400,320,game));
		//sp.add(new NecromancerBoss(400,320,game));
		//sp.add(new WizardBoss(736,40, game));
		//sp.add(new WallLevelSwitch(768, 576, WallLevelSwitch.DIRECTION_VERTICAL, "levels/Level1.xml"));
		sp.add(new FireballWaveTrap(200,200,game,8,8));
		sp.add(new OldManNPC(300,300,game,"Möchtest du dass ich dir beibringe\nwie man Pokemon fängt?"));
	}

	public Hero getHero() {
		return hero;
	}

	public void removeSprite(Sprite s) {
		sprites.remove(s);
	}
	public void addSprite(Sprite s){
		sprites.add(s);
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
}
