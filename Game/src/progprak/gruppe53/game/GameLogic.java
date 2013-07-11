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


	private Hero heros[];
	private Player player;
	private Player player2;


	public GameLogic() {
		heros = new Hero[2];
		sprites =  new ArrayList<Sprite>();
		doInitalizations();

	}
	public void addHero(Hero hero){
		//this.hero = hero;
		addHero(hero,0);
	}
	public void addHero(Hero hero,int playerId){
		heros[playerId] = hero;
	}

	private void doInitalizations(){

	}

	//Do Logics for every Sprite-Object
	protected void doLogic(long delta){
		actors = (ArrayList<Sprite>) sprites.clone();
		heros[0].setPlayer(player);
		heros[0].doLogic(delta);
		heros[0].testForCollision();
		heros[0].resetHandleEvents();
		if(heros[1] != null){
			if(player2 != null){
				heros[1].setPlayer(player2);
				heros[1].doLogic(delta);
				heros[1].testForCollision();
				heros[1].resetHandleEvents();
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
		heros[0].move(delta);
		if(heros[1] != null){
			heros[1].move(delta);
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
		if(heros[0].getWeapon() != null){
			sprites.add(heros[0].getWeapon());
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
		return heros[i];
	}
	public Hero getHero(){
		return heros[0];
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
		if(playerId == 0 && heros[1] != null){
			tmp.add(heros[1]);
		}
		else {
			tmp.add(heros[0]);
		}
		return tmp;
	}

}
