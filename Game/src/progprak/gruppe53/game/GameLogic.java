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


	private ArrayList<Hero> heros;
	private Player player;
	private Player player2;


	public GameLogic() {
		
		heros = new ArrayList<Hero>();
		sprites =  new ArrayList<Sprite>();
		doInitalizations();

	}
	public void addHero(Hero hero){
		//this.hero = hero;
		addHero(hero,0);
	}
	public void addHero(Hero hero,int playerId){
		heros.add(playerId,hero);
	}

	private void doInitalizations(){

	}

	//Do Logics for every Sprite-Object
	protected void doLogic(long delta){
		actors = (ArrayList<Sprite>) sprites.clone();
		heros.get(0).setPlayer(player);
		heros.get(0).doLogic(delta);
		heros.get(0).testForCollision();
		heros.get(0).resetHandleEvents();
		if(heros.size() > 1 && heros.get(1) != null){
			if(player2 != null){
				heros.get(1).setPlayer(player2);
				heros.get(1).doLogic(delta);
				heros.get(1).testForCollision();
				heros.get(1).resetHandleEvents();
			}
		}
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
		heros.get(0).move(delta);
		if(heros.size() > 1 && heros.get(1) != null){
			heros.get(1).move(delta);
		}
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
		if(heros.get(0).getWeapon() != null){
			sprites.add(heros.get(0).getWeapon());
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

	public Hero getHero(int i) {
		return heros.get(i);
	}
	public Hero getHero(){
		return heros.get(0);
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

	public void loose() {
		// TODO Auto-generated method stub

	}
	public void win() {
		// TODO Auto-generated method stub

	}
	public void tick(long delta, Player player, Player player2) {
		this.player = player;
		this.player2 = player2;
		doLogic(delta);				
		move(delta);
	}
	public ArrayList<Sprite> getActors(int playerId) {
		ArrayList<Sprite> tmp = (ArrayList<Sprite>) actors.clone();
		if(playerId == 0 && heros.size() > 1 && heros.get(1) != null){
			tmp.add(heros.get(1));
		}
		else {
			tmp.add(heros.get(0));
		}
		return tmp;
	}

}
