package progprak.gruppe53.game;

import java.util.ArrayList;
import java.util.Vector;
import java.util.ListIterator;

import progprak.gruppe53.sprites.CombatObject;
import progprak.gruppe53.sprites.Hero;
import progprak.gruppe53.sprites.Sprite;

public class GameLogic {
	
	private ArrayList<Sprite> actors;
	private ArrayList<Sprite> sprites;
	private String level;
	
	
	private Hero hero;
	private Player player;
	
	
	

	public GameLogic() {
		sprites =  new ArrayList<Sprite>();
		doInitalizations();
	}
	public void addHero(Hero hero){
		this.hero = hero;
	}
	
	private void doInitalizations(){
		
	}
	
	//Do Logics for every Sprite-Object
	protected void doLogic(long delta){
		actors = (ArrayList<Sprite>) sprites.clone();
		hero.doLogic(delta);
		hero.testForCollision();
		hero.resetHandleEvents();
		for(ListIterator<Sprite> it = actors.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.doLogic(delta);
			if (s instanceof CombatObject) {
				((CombatObject) s).testForCollision();
				((CombatObject) s).resetHandleEvents();
			}
		} 
	}
	protected void move(long delta){
		hero.move(delta);
		for(ListIterator<Sprite> it = actors.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.move(delta);
		}
	}
	public ArrayList<Sprite> getActors() {
		return actors;
	}

	public void switchLevel(String newLevel) {
		level = newLevel;
		ArrayList<Sprite> sp = new ArrayList<Sprite>();
		LevelLoaderSax.generateLevel(newLevel,sp, this);
		sprites = sp;
		if(hero.getWeapon() != null){
			sprites.add(hero.getWeapon());
		}
		actors = (ArrayList<Sprite>) sprites.clone();
	}
	

	public Vector<Sprite> testForCollision(Sprite a){
		Vector<Sprite> cs = new Vector<Sprite>();
		for(ListIterator<Sprite> it = actors.listIterator();it.hasNext();){
			Sprite s = it.next();
			if(a!=s){
				if(s instanceof Collidable && s.getRectangle().intersects(a.getHorizontalCollsionRect())){
					((Collidable)s).getCollisionEvent().setDirection(CollisionEvent.DIRECTION_HORIZONTAL);
					cs.add(s);
				}
				if(s instanceof Collidable && s.getRectangle().intersects(a.getVerticalCollsionRect())){
					((Collidable)s).getCollisionEvent().setDirection(CollisionEvent.DIRECTION_VERTICAL);
					cs.add(s);
				}
			}
		}
		return cs;
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
	public void tick(long delta, Player player) {
		this.player = player;
		doLogic(delta);				
		move(delta);
	}
}
