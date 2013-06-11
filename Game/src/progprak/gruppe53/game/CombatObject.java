package progprak.gruppe53.game;

import java.util.ListIterator;
import java.util.Vector;

abstract public class CombatObject extends Sprite implements Collidable{

	private static final long serialVersionUID = -6996362585746746561L;
	
	protected int faction = 0;

	protected int health;
	protected int maxHealth;
	protected long lastDamage = 0L;
	
	protected Item item;
	
	protected CollisionEvent collisionEvent;

	protected boolean handleEvents = true;
	
	protected Game game;
	
	public CombatObject(int x, int y, String imagePath,Game game) {
		super(x, y, imagePath);
		this.game = game;
	}

	protected void doInitalizations() {
		initCollisionEvent();
	}
	protected void initCollisionEvent() {
		collisionEvent = new DamageCollisionEvent(1,faction);
	}

	@Override
	public void doLogic(long delta) {

	}
	
	public void testForCollision(Game game){
		Vector<Sprite> cs = game.testForCollision(getMaxX(),getMinX(),getMaxY(),getMinY(),dx,dy);
		for (ListIterator<Sprite> it = cs.listIterator(); it.hasNext();) {
			Sprite s = it.next();
			CollisionEvent ce = ((Collidable)s).getCollisionEvent();
			handleColliosionEvent(ce);
		}
		
	}
	@Override
	public CollisionEvent getCollisionEvent(){
		return collisionEvent;
		
	}
	protected void handleDamageEvent(CollisionEvent ce){
		DamageCollisionEvent de = (DamageCollisionEvent) ce ;
		if(faction != de.getFaction()){
			long current = System.nanoTime();
			if((current - lastDamage)> 1e9){
				lastDamage = current;
				if((health -= de.getDamage()) <= 0){
					handleDie();
				}
			}
		}
	}
	
	protected void handlePickupEvent(CollisionEvent ce){
	}
	
	protected void handleDie() {
		game.getGameLogic().removeSprite(this);
	}

	protected void handleColliosionEvent(CollisionEvent ce) {
		if(handleEvents){
			if(ce.getEvent() == CollisionEvent.EVENT_MASSIVE){
				handleMassiveEvent(ce);
				
			}
			else if(ce.getEvent() == CollisionEvent.EVENT_DAMAGE){
				handleDamageEvent(ce);
			}
			else if(ce.getEvent() == CollisionEvent.EVENT_PICKUP){
				handlePickupEvent(ce);
			}
			else if (ce.getEvent() == CollisionEvent.EVENT_TELEPORT) {
				x = ce.getNewX();
				y = ce.getNewY();
			}
			else if (ce.getEvent() == CollisionEvent.EVENT_GOAL){
				//game.restart();
			}
			else if(ce.getEvent() == CollisionEvent.EVENT_SWITCH_LEVEL){
				//game.switchLevel(ce.getNewLevel());
			}
		}
		
	}

	protected void handleMassiveEvent(CollisionEvent ce) {
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL)dx = 0;
		else if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL)dy = 0;		
	}

	public void resetHandleEvents() {
		handleEvents = true;
	}
}
